package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Result;
import com.wen.entity.*;
import com.wen.entity.Short;
import com.wen.mapper.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
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
    @GetMapping("/count")
    public Result<?> findCount(@RequestParam String chapter) {
        LambdaQueryWrapper<Choice> cwrapper = Wrappers.<Choice>lambdaQuery();
        cwrapper.like(Choice::getChapter, chapter);
        int cCount = ChoiceMapper.selectCount(cwrapper);

        LambdaQueryWrapper<Judge> jwrapper = Wrappers.<Judge>lambdaQuery();
        jwrapper.like(Judge::getChapter, chapter);
        int jCount = JudgeMapper.selectCount(jwrapper);

        LambdaQueryWrapper<Fill> fwrapper = Wrappers.<Fill>lambdaQuery();
        fwrapper.like(Fill::getChapter, chapter);
        int fCount = FillMapper.selectCount(fwrapper);

        LambdaQueryWrapper<Short> swrapper = Wrappers.<Short>lambdaQuery();
        swrapper.like(Short::getChapter, chapter);
        int sCount = ShortMapper.selectCount(swrapper);

        LambdaQueryWrapper<Application> awrapper = Wrappers.<Application>lambdaQuery();
        awrapper.like(Application::getChapter, chapter);
        int aCount = ApplicationMapper.selectCount(awrapper);
        int allCount=cCount+jCount+fCount+sCount+aCount;
        Map<String, Integer> recordCounts = new HashMap<>();
        recordCounts.put("choiceCount", cCount);
        recordCounts.put("judgeCount", jCount);
        recordCounts.put("fillCount", fCount);
        recordCounts.put("shortCount", sCount);
        recordCounts.put("applicationCount", aCount);
        recordCounts.put("allCount", allCount);
        return Result.success(recordCounts);
    }

    //查询各个章节题目数量
    @GetMapping("/allcount")
    public Result<?> findAllCount() {
        List<String> chapters = Arrays.asList("计算机操作系统概述", "处理器管理", "存储管理", "设备管理", "文件管理", "并发程序设计", "网络环境下的操作系统");
        List<String> chapterStr = Arrays.asList("os", "cpu", "space", "io", "file", "program", "net");
        Map<String, Integer> chapterCounts = new HashMap<>();
        int i=0;
        for (String chapter : chapters) {
            int cCount = ChoiceMapper.selectCount(Wrappers.<Choice>lambdaQuery().like(Choice::getChapter, chapter));
            int jCount = JudgeMapper.selectCount(Wrappers.<Judge>lambdaQuery().like(Judge::getChapter, chapter));
            int fCount = FillMapper.selectCount(Wrappers.<Fill>lambdaQuery().like(Fill::getChapter, chapter));
            int sCount = ShortMapper.selectCount(Wrappers.<Short>lambdaQuery().like(Short::getChapter, chapter));
            int aCount = ApplicationMapper.selectCount(Wrappers.<Application>lambdaQuery().like(Application::getChapter, chapter));

            int allCount = cCount + jCount + fCount + sCount + aCount;
            chapterCounts.put(chapterStr.get(i), allCount);
            i=i+1;
        }

        return Result.success(chapterCounts);
    }


}