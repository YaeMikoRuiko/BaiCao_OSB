package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Result;
import com.wen.entity.Choice;
import com.wen.entity.Fill;
import com.wen.mapper.FillMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fill")
public class FillController {
    @Resource
    FillMapper FillMapper;

    //添加选择题
    @PostMapping
    public Result<?> save(@RequestBody Fill fill) {
//        if(article.getPicture()==null){
//            article.setPicture("/img/pic1.png");
//        }
        FillMapper.insert(fill);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Fill fill) {
        FillMapper.updateById(fill);
        return Result.success();
    }

    //    删除
    @DeleteMapping("/{Id}")
    public Result<?> delete(@PathVariable Integer Id) {
        FillMapper.deleteById(Id);
        return Result.success();
    }

    //查询选择题
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Fill> wrapper = Wrappers.<Fill>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Fill::getQuestion, search);
        }
        wrapper.orderByAsc(Fill::getId);
        Page<Fill> fillPage = FillMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(fillPage);
    }

    @GetMapping("/list")
    public Result<?> getList() {
        LambdaQueryWrapper<Fill> wrapper = Wrappers.<Fill>lambdaQuery();
        return Result.success(FillMapper.selectList(wrapper));
    }
    @GetMapping("/comment")
    public Result<?> findChapter(@RequestParam String chapter) {
        LambdaQueryWrapper<Fill> wrapper = Wrappers.<Fill>lambdaQuery();
        if (StrUtil.isNotBlank(chapter)) {
            wrapper.like(Fill::getChapter, chapter);
        }
        wrapper.orderByAsc(Fill::getId);
        List<Fill> fills = FillMapper.selectList(wrapper);
        return Result.success(fills);
    }


    @GetMapping("/twoFill")
    public Result<?> afterAndBefore(@RequestParam Integer id) {
        // 查询上一条记录
        Fill prevFill = FillMapper.selectOne(
                Wrappers.<Fill>lambdaQuery()
                        .lt(Fill::getId, id)
                        .orderByDesc(Fill::getId)
                        .last("limit 1")
        );

        // 查询下一条记录
        Fill nextFill = FillMapper.selectOne(
                Wrappers.<Fill>lambdaQuery()
                        .gt(Fill::getId, id)
                        .orderByAsc(Fill::getId)
                        .last("limit 1")
        );

        Map<String, Object> result = new HashMap<>();
        result.put("prevFill", prevFill);
        result.put("nextFill", nextFill);
        return Result.success(result);
    }

    @GetMapping("/selectOne")
    public Result<?> selectById(@RequestParam int id) {
            Fill fill = FillMapper.selectById(id);
            if (fill != null) {
                return Result.success(fill);
            }
            return Result.error("-1","未找到对应记录");
    }
}