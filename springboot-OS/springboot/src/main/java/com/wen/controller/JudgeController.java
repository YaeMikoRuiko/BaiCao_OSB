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
import com.wen.entity.Judge;
import com.wen.mapper.JudgeMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/judge")
public class JudgeController {
    @Resource
    JudgeMapper JudgeMapper;

    //添加选择题
    @PostMapping
    public Result<?> save(@RequestBody Judge judge) {
//        if(article.getPicture()==null){
//            article.setPicture("/img/pic1.png");
//        }
        JudgeMapper.insert(judge);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Judge judge) {
        JudgeMapper.updateById(judge);
        return Result.success();
    }

    //    删除
    @DeleteMapping("/{Id}")
    public Result<?> delete(@PathVariable Integer Id) {
        JudgeMapper.deleteById(Id);
        return Result.success();
    }

    //查询选择题
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Judge> wrapper = Wrappers.<Judge>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Judge::getQuestion, search);
        }
        wrapper.orderByAsc(Judge::getId);
        Page<Judge> judgePage = JudgeMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(judgePage);
    }

    @GetMapping("/tiku_judge")
    public Result<?> findJudge() {
        // 查询第1页，每页查询5条记录
        Page<Judge> page = new Page<>(1, 5);

        // 查询计算机操作系统概述章节的随机五条记录
        QueryWrapper<Judge> osQueryWrapper = new QueryWrapper<>();
        osQueryWrapper.eq("chapter", "并发程序设计")
                .orderByAsc("RAND()");
        IPage<Judge> osResult = JudgeMapper.selectPage(page, osQueryWrapper);
        List<Judge> osRecords = osResult.getRecords();

        // 查询处理器管理章节的随机五条记录
        QueryWrapper<Judge> cpuQueryWrapper = new QueryWrapper<>();
        cpuQueryWrapper.eq("chapter", "网络环境下的操作系统")
                .orderByAsc("RAND()");
        IPage<Judge> cpuResult = JudgeMapper.selectPage(page, cpuQueryWrapper);
        List<Judge> cpuRecords = cpuResult.getRecords();


        // 构建返回结果
        Map<String, List<Judge>> resultMap = new HashMap<>();
        resultMap.put("program", osRecords);
        resultMap.put("net", cpuRecords);

        return Result.success(resultMap);
    }

    @GetMapping("/comment")
    public Result<?> findChapter(@RequestParam String chapter) {
        LambdaQueryWrapper<Judge> wrapper = Wrappers.<Judge>lambdaQuery();
        if (StrUtil.isNotBlank(chapter)) {
            wrapper.like(Judge::getChapter, chapter);
        }
        wrapper.orderByAsc(Judge::getId);
        List<Judge> judges = JudgeMapper.selectList(wrapper);
        return Result.success(judges);
    }

    @GetMapping("/twoJudge")
    public Result<?> afterAndBefore(@RequestParam Integer id) {
        // 查询上一条记录
        Judge prevJudge = JudgeMapper.selectOne(
                Wrappers.<Judge>lambdaQuery()
                        .lt(Judge::getId, id)
                        .orderByDesc(Judge::getId)
                        .last("limit 1")
        );

        // 查询下一条记录
        Judge nextJudge = JudgeMapper.selectOne(
                Wrappers.<Judge>lambdaQuery()
                        .gt(Judge::getId, id)
                        .orderByAsc(Judge::getId)
                        .last("limit 1")
        );

        Map<String, Object> result = new HashMap<>();
        result.put("prevJudge", prevJudge);
        result.put("nextJudge", nextJudge);
        return Result.success(result);
    }

    @GetMapping("/selectOne")
    public Result<?> selectById(@RequestParam int id) {
       Judge judge = JudgeMapper.selectById(id);
        if (judge != null) {
            return Result.success(judge);
        }
        return Result.error("-1","未找到对应记录");
    }

    @GetMapping("/list")
    public Result<?> getList() {
        LambdaQueryWrapper<Judge> wrapper = Wrappers.<Judge>lambdaQuery();
        return Result.success(JudgeMapper.selectList(wrapper));
    }

}