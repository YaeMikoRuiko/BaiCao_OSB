package com.wen.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.entity.Article;
import com.wen.entity.TestPaper;
import com.wen.mapper.TestPaperMapper;
import com.wen.service.TestPaperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TestPaperServiceImpl extends ServiceImpl<TestPaperMapper, TestPaper> implements TestPaperService {

    @Resource
    TestPaperMapper testPaperMapper;


    @Override
    public void insert(TestPaper testPaper) {
        testPaperMapper.insert(testPaper);
    }

    @Override
    public void update(TestPaper testPaper) {
        testPaperMapper.updateById(testPaper);
    }

    @Override
    public void deleteById(Integer Id) {
        testPaperMapper.deleteById(Id);
    }

    @Override
    public Page<TestPaper> getTestPaperList(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<TestPaper> wrapper = Wrappers.<TestPaper>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(TestPaper::getTestName,search);
        }
        Page<TestPaper> TestPage = testPaperMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return TestPage;
    }
}
