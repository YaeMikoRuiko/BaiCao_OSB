package com.wen.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Result;
import com.wen.entity.Short;
import com.wen.entity.*;
import com.wen.mapper.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    ApplicationMapper ApplicationMapper;
    @Resource
    ChoiceMapper ChoiceMapper;
    @Resource
    JudgeMapper JudgeMapper;
    @Resource
    FillMapper FillMapper;
    @Resource
    ShortMapper ShortMapper;


    //查询各个章节各个题型数量
    @GetMapping("/random")
    public Result<?> randomQuestion(@RequestParam String questionType,@RequestParam int count) {
        if(questionType.equals("choice")){
            Page<Choice> page = new Page<>(1, count);
            // 查询
            QueryWrapper<Choice> osQueryWrapper = new QueryWrapper<>();
            osQueryWrapper.orderByAsc("RAND()");
            IPage<Choice> result = ChoiceMapper.selectPage(page, osQueryWrapper);
            // 将查询结果存储在一个列表中
            List<Choice> resultList = result.getRecords();
            // 对列表进行按 id 排序
            Collections.sort(resultList, Comparator.comparing(Choice::getId));
            return Result.success(result);
        }else if(questionType.equals("judge")){
            Page<Judge> page = new Page<>(1, count);
            // 查询计算机操作系统概述章节的随机五条记录
            QueryWrapper<Judge> osQueryWrapper = new QueryWrapper<>();
            osQueryWrapper.orderByAsc("RAND()");
            IPage<Judge> result = JudgeMapper.selectPage(page, osQueryWrapper);
            // 将查询结果存储在一个列表中
            List<Judge> resultList = result.getRecords();
            // 对列表进行按 id 排序
            Collections.sort(resultList, Comparator.comparing(Judge::getId));
            return Result.success(result);
        }
        return Result.error("-1","错误");
    }


}