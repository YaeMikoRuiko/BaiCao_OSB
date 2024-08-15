package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Result;
import com.wen.entity.Choice;
import com.wen.entity.Fill;
import com.wen.entity.Short;
import com.wen.mapper.ShortMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/short")
public class ShortController {
    @Resource
    ShortMapper ShortMapper;

    //添加选择题
    @PostMapping
    public Result<?> save(@RequestBody Short s) {
//        if(article.getPicture()==null){
//            article.setPicture("/img/pic1.png");
//        }
        ShortMapper.insert(s);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Short s) {
        ShortMapper.updateById(s);
        return Result.success();
    }

    //    删除
    @DeleteMapping("/{Id}")
    public Result<?> delete(@PathVariable Integer Id) {
        ShortMapper.deleteById(Id);
        return Result.success();
    }

    //查询选择题
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Short> wrapper = Wrappers.<Short>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Short::getQuestion, search);
        }
        wrapper.orderByAsc(Short::getId);
        Page<Short> shortPage = ShortMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(shortPage);
    }

//    @GetMapping("/tiku_short")
//    public Result<?> findShort() {
//        // 查询第1页，每页查询5条记录
//        Page<Short> page = new Page<>(1, 5);
//
//        // 查询计算机操作系统概述章节的随机五条记录
//        QueryWrapper<Short> osQueryWrapper = new QueryWrapper<>();
//        osQueryWrapper.eq("chapter", "并发程序设计")
//                .orderByAsc("RAND()");
//        IPage<Short> osResult = ShortMapper.selectPage(page, osQueryWrapper);
//        List<Short> osRecords = osResult.getRecords();
//
//        // 查询处理器管理章节的随机五条记录
//        QueryWrapper<Short> cpuQueryWrapper = new QueryWrapper<>();
//        cpuQueryWrapper.eq("chapter", "网络环境下的操作系统")
//                .orderByAsc("RAND()");
//        IPage<Short> cpuResult = ShortMapper.selectPage(page, cpuQueryWrapper);
//        List<Short> cpuRecords = cpuResult.getRecords();
//
//
//        // 构建返回结果
//        Map<String, List<Short>> resultMap = new HashMap<>();
//        resultMap.put("program", osRecords);
//        resultMap.put("net", cpuRecords);
//
//        return Result.success(resultMap);
//    }

    @GetMapping("/comment")
    public Result<?> findChapter(@RequestParam String chapter) {
        LambdaQueryWrapper<Short> wrapper = Wrappers.<Short>lambdaQuery();
        if (StrUtil.isNotBlank(chapter)) {
            wrapper.like(Short::getChapter, chapter);
        }
        wrapper.orderByAsc(Short::getId);
        List<Short> shorts = ShortMapper.selectList(wrapper);
        return Result.success(shorts);
    }

    @GetMapping("/twoShort")
    public Result<?> afterAndBefore(@RequestParam Integer id) {
        // 查询上一条记录
        Short prevShort = ShortMapper.selectOne(
                Wrappers.<Short>lambdaQuery()
                        .lt(Short::getId, id)
                        .orderByDesc(Short::getId)
                        .last("limit 1")
        );

        // 查询下一条记录
        Short nextShort = ShortMapper.selectOne(
                Wrappers.<Short>lambdaQuery()
                        .gt(Short::getId, id)
                        .orderByAsc(Short::getId)
                        .last("limit 1")
        );

        Map<String, Object> result = new HashMap<>();
        result.put("prevShort", prevShort);
        result.put("nextShort", nextShort);
        return Result.success(result);
    }
    @GetMapping("/selectOne")
    public Result<?> selectById(@RequestParam int id) {
       Short s = ShortMapper.selectById(id);
        if (s!= null) {
            return Result.success(s);
        }
        return Result.error("-1","未找到对应记录");
    }
    @GetMapping("/list")
    public Result<?> getList() {
        LambdaQueryWrapper<Short> wrapper = Wrappers.<Short>lambdaQuery();
        return Result.success(ShortMapper.selectList(wrapper));
    }

}