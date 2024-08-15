package com.wen.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.entity.Article;
import com.wen.mapper.ArticleMapper;
import com.wen.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleMapper articleMapper;

    @Override
    public Map<String, Object> findArticle() {
        Page<Article> page = new Page<>(1, 5); // 查询第1页，每页查询6条记录，并按照 createTime 倒序排序
        //最新
        // 构建查询条件
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("article_id", "create_time", "title"); // 指定要查询的字段
        queryWrapper.orderByDesc("create_time"); // 按照 create_time 字段倒序排序
        // 执行查询
        IPage<Article> result = articleMapper.selectPage(page, queryWrapper);
        List<Article> records = result.getRecords(); // 获取查询结果集
        //新闻
        QueryWrapper<Article> newsWrapper = new QueryWrapper<>();
        newsWrapper.select("article_id", "create_time", "title"); // 指定要查询的字段
        newsWrapper.eq("type", "新闻"); // 添加筛选条件，假设type字段为新闻
        newsWrapper.orderByDesc("create_time"); // 按照 create_time 字段倒序排序
        IPage<Article> news = articleMapper.selectPage(page, newsWrapper);
        List<Article> newsList = news.getRecords(); // 获取查询结果集
        //公告
        QueryWrapper<Article> noticesWrapper = new QueryWrapper<>();
        noticesWrapper.select("article_id", "create_time", "title"); // 指定要查询的字段
        noticesWrapper.eq("type", "公告"); // 添加筛选条件，假设type字段为新闻
        noticesWrapper.orderByDesc("create_time"); // 按照 create_time 字段倒序排序
        IPage<Article> notices = articleMapper.selectPage(page, noticesWrapper);
        List<Article> noticesList = notices.getRecords(); // 获取查询结果
        Map<String, Object> results = new HashMap<>();
        results.put("result", records);
        results.put("news", newsList);
        results.put("notice", noticesList);
        return results;
    }
}
