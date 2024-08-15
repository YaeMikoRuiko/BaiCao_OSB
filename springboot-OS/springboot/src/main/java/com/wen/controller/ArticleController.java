package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wen.common.Result;
import com.wen.entity.Article;
import com.wen.mapper.ArticleMapper;
import com.wen.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
    public class ArticleController {
    @Resource
    ArticleMapper ArticleMapper;
    @Resource
    ArticleService articleService;
    //添加文章
    @PostMapping
    public Result<?> save(@RequestBody Article article){
        ArticleMapper.insert(article);
        return Result.success();
    }
    @PutMapping
    public Result<?> update(@RequestBody Article article) {
        ArticleMapper.updateById(article);
        return Result.success();
    }
    //    删除
    @DeleteMapping("/{articleId}")
    public Result<?> delete(@PathVariable Integer articleId){
        ArticleMapper.deleteById(articleId);
        return Result.success();
    }
    //查询文章
    @GetMapping("/news")
    public Result<?> findArticle(){
        Map<String, Object> results = articleService.findArticle();
        return Result.success(results);
    }

    //文章管理
    @GetMapping("/list")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search
                              ){
        LambdaQueryWrapper<Article> wrapper = Wrappers.<Article>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Article::getTitle,search);
        }
        Page<Article> ArticlePage = ArticleMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(ArticlePage);
    }
    @GetMapping("/all")
    public Result<?> selectAll(@RequestParam(required = false) String search) {
        LambdaQueryWrapper<Article> wrapper = Wrappers.<Article>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            if(search.equals("undefined")){
                wrapper.orderByDesc(Article::getCreateTime); // 按照 create_time 字段倒序排序
                List<Article> articleList = ArticleMapper.selectList(wrapper);
                return Result.success(articleList);
            }
            wrapper.and(qw -> qw.like(Article::getTitle, search)
                    .or().like(Article::getContent, search)
                    .or().like(Article::getAuthorId, search)
                    .or().like(Article::getType, search)
                    .or().like(Article::getArticleId, search));
        }
        wrapper.orderByDesc(Article::getCreateTime); // 按照 create_time 字段倒序排序
        List<Article> articleList = ArticleMapper.selectList(wrapper);
        return Result.success(articleList);
    }

    @GetMapping("/{articleId}")
    public Result<?> selectByArticleId(@PathVariable Long articleId) {
        LambdaQueryWrapper<Article> wrapper = Wrappers.<Article>lambdaQuery();
        wrapper.eq(Article::getArticleId, articleId);
        Article article = ArticleMapper.selectOne(wrapper);

        if (article != null) {
            return Result.success(article);
        } else {
            return Result.error("-1","未找到对应的文章记录");
        }
    }
}
