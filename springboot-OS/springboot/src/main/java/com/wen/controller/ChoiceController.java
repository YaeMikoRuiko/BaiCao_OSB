package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wen.common.Result;
import com.wen.entity.Choice;

import com.wen.entity.Comment;
import com.wen.entity.Fill;
import com.wen.mapper.ChoiceMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/choice")
public class ChoiceController {
    @Resource
    ChoiceMapper ChoiceMapper;

    //添加选择题
    @PostMapping
    public Result<?> save(@RequestBody Choice choice) {
//        if(article.getPicture()==null){
//            article.setPicture("/img/pic1.png");
//        }
        ChoiceMapper.insert(choice);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Choice choice) {
        ChoiceMapper.updateById(choice);
        return Result.success();
    }

    //    删除
    @DeleteMapping("/{Id}")
    public Result<?> delete(@PathVariable Integer Id) {
        ChoiceMapper.deleteById(Id);
        return Result.success();
    }

    //查询选择题
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Choice> wrapper = Wrappers.<Choice>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Choice::getQuestion, search);
        }
        wrapper.orderByAsc(Choice::getId);
        Page<Choice> choicePage = ChoiceMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(choicePage);
    }

    @GetMapping("/tiku_choice")
    public Result<?> findChoice() {
        // 查询第1页，每页查询5条记录
        Page<Choice> page = new Page<>(1, 5);

        // 查询计算机操作系统概述章节的随机五条记录
        QueryWrapper<Choice> osQueryWrapper = new QueryWrapper<>();
        osQueryWrapper.eq("chapter", "计算机操作系统概述")
                .orderByAsc("RAND()");
        IPage<Choice> osResult = ChoiceMapper.selectPage(page, osQueryWrapper);
        List<Choice> osRecords = osResult.getRecords();

        // 查询处理器管理章节的随机五条记录
        QueryWrapper<Choice> cpuQueryWrapper = new QueryWrapper<>();
        cpuQueryWrapper.eq("chapter", "处理器管理")
                .orderByAsc("RAND()");
        IPage<Choice> cpuResult = ChoiceMapper.selectPage(page, cpuQueryWrapper);
        List<Choice> cpuRecords = cpuResult.getRecords();

        // 查询存储管理章节的随机五条记录
        QueryWrapper<Choice> spaceQueryWrapper = new QueryWrapper<>();
        spaceQueryWrapper.eq("chapter", "存储管理")
                .orderByAsc("RAND()");
        IPage<Choice> spaceResult = ChoiceMapper.selectPage(page, spaceQueryWrapper);
        List<Choice> spaceRecords = spaceResult.getRecords();

        // 查询设备管理章节的随机五条记录
        QueryWrapper<Choice> ioQueryWrapper = new QueryWrapper<>();
        ioQueryWrapper.eq("chapter", "设备管理")
                .orderByAsc("RAND()");
        IPage<Choice> ioResult = ChoiceMapper.selectPage(page, ioQueryWrapper);
        List<Choice> ioRecords = ioResult.getRecords();

        // 查询文件管理章节的随机五条记录
        QueryWrapper<Choice> fileQueryWrapper = new QueryWrapper<>();
        fileQueryWrapper.eq("chapter", "文件管理")
                .orderByAsc("RAND()");
        IPage<Choice> fileResult = ChoiceMapper.selectPage(page, fileQueryWrapper);
        List<Choice> fileRecords = fileResult.getRecords();

        // 构建返回结果
        Map<String, List<Choice>> resultMap = new HashMap<>();
        resultMap.put("os", osRecords);
        resultMap.put("cpu", cpuRecords);
        resultMap.put("space", spaceRecords);
        resultMap.put("io", ioRecords);
        resultMap.put("file", fileRecords);

        return Result.success(resultMap);
    }

    @GetMapping("/comment")
    public Result<?> findChapter(@RequestParam String chapter) {
        LambdaQueryWrapper<Choice> wrapper = Wrappers.<Choice>lambdaQuery();
        if (StrUtil.isNotBlank(chapter)) {
            wrapper.like(Choice::getChapter, chapter);
        }
        wrapper.orderByAsc(Choice::getId);
        List<Choice> choices = ChoiceMapper.selectList(wrapper);
        return Result.success(choices);
    }

    @GetMapping("/twoChoice")
    public Result<?> afterAndBefore(@RequestParam Integer id) {
        // 查询上一条记录
        Choice prevChoice = ChoiceMapper.selectOne(
                Wrappers.<Choice>lambdaQuery()
                        .lt(Choice::getId, id)
                        .orderByDesc(Choice::getId)
                        .last("limit 1")
        );

        // 查询下一条记录
        Choice nextChoice = ChoiceMapper.selectOne(
                Wrappers.<Choice>lambdaQuery()
                        .gt(Choice::getId, id)
                        .orderByAsc(Choice::getId)
                        .last("limit 1")
        );

        Map<String, Object> result = new HashMap<>();
        result.put("prevChoice", prevChoice);
        result.put("nextChoice", nextChoice);
        return Result.success(result);
    }

    @GetMapping("/selectOne")
    public Result<?> selectById(@RequestParam int id) {
        Choice choice = ChoiceMapper.selectById(id);
        if (choice != null) {
            return Result.success(choice);
        }
        return Result.error("-1","未找到对应记录");
    }

    @GetMapping("/list")
    public Result<?> getList() {
        LambdaQueryWrapper<Choice> wrapper = Wrappers.<Choice>lambdaQuery();
        return Result.success(ChoiceMapper.selectList(wrapper));
    }

}