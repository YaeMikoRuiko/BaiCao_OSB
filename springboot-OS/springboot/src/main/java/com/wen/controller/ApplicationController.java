package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Result;
import com.wen.entity.Application;
import com.wen.entity.Choice;
import com.wen.entity.Fill;
import com.wen.mapper.ApplicationMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Resource
    ApplicationMapper ApplicationMapper;

    //添加选择题
    @PostMapping
    public Result<?> save(@RequestBody Application application) {
//        if(article.getPicture()==null){
//            article.setPicture("/img/pic1.png");
//        }
        ApplicationMapper.insert(application);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Application application) {
        ApplicationMapper.updateById(application);
        return Result.success();
    }

    //    删除
    @DeleteMapping("/{Id}")
    public Result<?> delete(@PathVariable Integer Id) {
        ApplicationMapper.deleteById(Id);
        return Result.success();
    }

    //查询选择题
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Application> wrapper = Wrappers.<Application>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Application::getQuestion, search);
        }
        wrapper.orderByAsc(Application::getId);
        Page<Application> applicationPage = ApplicationMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(applicationPage);
    }

//    @GetMapping("/tiku_application")
//    public Result<?> findApplication() {
//        // 查询第1页，每页查询5条记录
//        Page<Application> page = new Page<>(1, 5);
//
//        // 查询计算机操作系统概述章节的随机五条记录
//        QueryWrapper<Application> osQueryWrapper = new QueryWrapper<>();
//        osQueryWrapper.eq("chapter", "并发程序设计")
//                .orderByAsc("RAND()");
//        IPage<Application> osResult = ApplicationMapper.selectPage(page, osQueryWrapper);
//        List<Application> osRecords = osResult.getRecords();
//
//        // 查询处理器管理章节的随机五条记录
//        QueryWrapper<Application> cpuQueryWrapper = new QueryWrapper<>();
//        cpuQueryWrapper.eq("chapter", "网络环境下的操作系统")
//                .orderByAsc("RAND()");
//        IPage<Application> cpuResult = ApplicationMapper.selectPage(page, cpuQueryWrapper);
//        List<Application> cpuRecords = cpuResult.getRecords();
//
//
//        // 构建返回结果
//        Map<String, List<Application>> resultMap = new HashMap<>();
//        resultMap.put("program", osRecords);
//        resultMap.put("net", cpuRecords);
//
//        return Result.success(resultMap);
//    }

    @GetMapping("/comment")
    public Result<?> findChapter(@RequestParam String chapter) {
        LambdaQueryWrapper<Application> wrapper = Wrappers.<Application>lambdaQuery();
        if (StrUtil.isNotBlank(chapter)) {
            wrapper.like(Application::getChapter, chapter);
        }
        wrapper.orderByAsc(Application::getId);
        List<Application> applications = ApplicationMapper.selectList(wrapper);
        return Result.success(applications);
    }

    @GetMapping("/twoApplication")
    public Result<?> afterAndBefore(@RequestParam Integer id) {
        // 查询上一条记录
        Application prevApplication = ApplicationMapper.selectOne(
                Wrappers.<Application>lambdaQuery()
                        .lt(Application::getId, id)
                        .orderByDesc(Application::getId)
                        .last("limit 1")
        );

        // 查询下一条记录
        Application nextApplication = ApplicationMapper.selectOne(
                Wrappers.<Application>lambdaQuery()
                        .gt(Application::getId, id)
                        .orderByAsc(Application::getId)
                        .last("limit 1")
        );

        Map<String, Object> result = new HashMap<>();
        result.put("prevApplication", prevApplication);
        result.put("nextApplication", nextApplication);
        return Result.success(result);
    }

    @GetMapping("/selectOne")
    public Result<?> selectById(@RequestParam int id) {
        Application a = ApplicationMapper.selectById(id);
        if (a != null) {
            return Result.success(a);
        }
        return Result.error("-1","未找到对应记录");
    }
    @GetMapping("/list")
    public Result<?> getList() {
        LambdaQueryWrapper<Application> wrapper = Wrappers.<Application>lambdaQuery();
        return Result.success(ApplicationMapper.selectList(wrapper));
    }
}