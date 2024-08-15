package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Result;
import com.wen.entity.Choice;
import com.wen.entity.Comment;
import com.wen.mapper.CommentMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/comment")
    public class CommentController {
    @Resource
    CommentMapper CommentMapper;

    //添加评论
    @PostMapping
    public Result<?> save(@RequestBody Comment Comment){
        CommentMapper.insert(Comment);
        return Result.success();
    }
    @PutMapping
    public Result<?> update(@RequestBody Comment Comment) {
        CommentMapper.updateById(Comment);
        return Result.success();
    }
    //    删除
    @DeleteMapping("/{Id}")
    public Result<?> delete(@PathVariable Integer Id){
        CommentMapper.deleteById(Id);
        return Result.success();
    }
    //查询评论
    @GetMapping
    public Result<?> findComment(){
//        LambdaQueryWrapper<Comment> wrapper = Wrappers.<Comment>lambdaQuery();
//
//        List<Comment> Comments = CommentMapper.selectList(wrapper);
        Page<Comment> page = new Page<>(1, 8); // 查询第1页，每页查询8条记录

// 构建查询条件
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("RAND()"); // 使用MySQL的RAND()函数进行随机排序

// 执行查询
        IPage<Comment> result = CommentMapper.selectPage(page, queryWrapper);

        List<Comment> records = result.getRecords(); // 获取查询结果集

        return Result.success(records);
    }


    @GetMapping("/list")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Comment> wrapper = Wrappers.<Comment>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Comment::getCommentText,search);
        }
        Page<Comment> CommentPage = CommentMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(CommentPage);
    }

    @GetMapping("/{questionType}")
    public Result<?> findChoiceComment(@PathVariable String questionType,
                                       @RequestParam Integer questionId ){
        LambdaQueryWrapper<Comment> wrapper = Wrappers.<Comment>lambdaQuery();
            wrapper.like(Comment::getQuestionId,questionId);
           wrapper.like(Comment::getQuestionType,questionType);
        wrapper.orderByDesc(Comment::getCreateTime); // 按照 createTime 字段降序排序
        List<Comment> comments = CommentMapper.selectList(wrapper);
        return Result.success(comments);
    }

}
