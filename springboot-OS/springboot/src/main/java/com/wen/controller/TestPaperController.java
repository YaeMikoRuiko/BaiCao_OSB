package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Result;
import com.wen.entity.*;
import com.wen.entity.Short;
import com.wen.mapper.*;
import com.wen.service.TestPaperQuestionService;
import com.wen.service.TestPaperService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testPaper")
    public class TestPaperController {
    @Resource
    TestPaperMapper testPaperMapper;
    @Resource
    TestPaperService testPaperService;
    @Resource
    TestPaperQuestionMapper testPaperQuestionMapper;
    @Resource
    TestPaperQuestionService testPaperQuestionService;
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


    //添加试卷
    @PostMapping
    public Result<?> save(@RequestBody TestPaper testPaper){
        testPaperService.insert(testPaper);
        return Result.success();
    }
    @PutMapping
    public Result<?> update(@RequestBody TestPaper testPaper) {
        testPaperService.update(testPaper);
        return Result.success();
    }
    //    删除
    @DeleteMapping("/{Id}")
    public Result<?> delete(@PathVariable Integer Id){
        testPaperService.deleteById(Id);
        return Result.success();
    }
    //获取试卷
    @GetMapping("/list")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        Page<TestPaper> TestPage = testPaperService.getTestPaperList(pageNum,pageSize,search);
        return Result.success(TestPage);
    }

      //获取试卷题目后，将题目保存
      @PutMapping("/questions")
      public Result<?> getQusetions(@RequestBody TestQuestions testQuestions){
          List<Choice> choices=testQuestions.getChoice();
          List<Fill> fills=testQuestions.getFill();
          List<Judge> judges=testQuestions.getJudge();
          List<Short> shorts=testQuestions.getShorts();
          List<Application> applications=testQuestions.getApplication();
          List<TestPaperQuestion> testPaperQuestions = new ArrayList<>();
          if(choices!=null)
          for (Choice question : choices) {
              TestPaperQuestion t = new TestPaperQuestion();
              t.setTestId(testQuestions.getTestId());
              t.setQuestionId(question.getId());
              t.setQuestionType("choice");
              testPaperQuestions.add(t);
          }
          if(judges!=null)
          for (Judge question : judges) {
              TestPaperQuestion t = new TestPaperQuestion();
              t.setTestId(testQuestions.getTestId());
              t.setQuestionId(question.getId());
              t.setQuestionType("judge");
              testPaperQuestions.add(t);
          }
          if(fills!=null)
          for (Fill question : fills) {
              TestPaperQuestion t = new TestPaperQuestion();
              t.setTestId(testQuestions.getTestId());
              t.setQuestionId(question.getId());
              t.setQuestionType("fill");
              testPaperQuestions.add(t);
          }
          if(shorts!=null)
          for (Short question : shorts) {
              TestPaperQuestion t = new TestPaperQuestion();
              t.setTestId(testQuestions.getTestId());
              t.setQuestionId(question.getId());
              t.setQuestionType("short");
              testPaperQuestions.add(t);
          }
          if(applications!=null)
          for (Application question : applications) {
              TestPaperQuestion t = new TestPaperQuestion();
              t.setTestId(testQuestions.getTestId());
              t.setQuestionId(question.getId());
              t.setQuestionType("application");
              testPaperQuestions.add(t);
          }
          testPaperQuestionService.saveBatch(testPaperQuestions);
          return Result.success();
          }
          //查询试卷的试题信息
          //获取试卷
          @GetMapping("/questionList")
          public Result<?> getQuestionList(@RequestParam String testId){
              LambdaQueryWrapper<TestPaperQuestion> wrapper = Wrappers.<TestPaperQuestion>lambdaQuery();
                  wrapper.like(TestPaperQuestion::getTestId,testId);
              List<TestPaperQuestion> testQuestionList = testPaperQuestionMapper.selectList(wrapper);
              List<Choice> choices = new ArrayList<>(); // 创建一个列表来存储选择题
              List<Fill> fills=new ArrayList<>();
              List<Judge> judges=new ArrayList<>();
              List<Short> shorts=new ArrayList<>();
              List<Application> applications=new ArrayList<>();
              for(TestPaperQuestion question : testQuestionList){
                  String type=question.getQuestionType();
                  if("choice".equals(type)){
                      Choice choice = choiceMapper.selectById(question.getQuestionId());
                      if(choice != null){
                          choices.add(choice); // 将选择题记录加入到列表中
                      }
                  }
                  if("fill".equals(type)){
                      Choice choice = choiceMapper.selectById(question.getQuestionId());
                      if(choice != null){
                          choices.add(choice); // 将填空题记录加入到列表中
                      }
                  }
                  if("short".equals(type)){
                      Short s = shortMapper.selectById(question.getQuestionId());
                      if(s != null){
                          shorts.add(s); // 将简答题记录加入到列表中
                      }
                  }
                  if("judge".equals(type)){
                      Judge judge = judgeMapper.selectById(question.getQuestionId());
                      if(judge != null){
                          judges.add(judge); // 将选择题记录加入到列表中
                      }
                  }
                  if("application".equals(type)){
                      Application application = applicationMapper.selectById(question.getQuestionId());
                      if(application != null){
                          applications.add(application); // 将选择题记录加入到列表中
                      }
                  }
              }
              Map<String, List<?>> questionMap = new HashMap<>();
              // 将不同类型的题目列表放入HashMap中
              questionMap.put("choice", choices);
              questionMap.put("fill", fills);
              questionMap.put("judge", judges);
              questionMap.put("short", shorts);
              questionMap.put("application", applications);
              return Result.success(questionMap);
          }
}
