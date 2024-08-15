package com.wen.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Result;
import com.wen.entity.Choice;
import com.wen.entity.Comment;
import com.wen.entity.Judge;
import com.wen.mapper.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    ApplicationMapper applicationMapper;
    @Resource
    ChoiceMapper choiceMapper;
    @Resource
    JudgeMapper judgeMapper;
    @Resource
    FillMapper fillMapper;
    @Resource
    ShortMapper shortMapper;


    //搜索相关内容
    @GetMapping("/search")
    public Result<?> searchQuestion(@RequestParam String search) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList.addAll(choiceMapper.searchQuestion(search));
        resultList.addAll(fillMapper.searchQuestion(search));
        resultList.addAll(judgeMapper.searchQuestion(search));
        resultList.addAll(shortMapper.searchQuestion(search));
        resultList.addAll(applicationMapper.searchQuestion(search));
        return Result.success(resultList);
    }





}