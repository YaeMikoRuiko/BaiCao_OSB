package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Result;
import com.wen.entity.Lunbo;
import com.wen.mapper.LunboMapper;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lunbo")
    public class LunboController {
    @Resource
    LunboMapper lunboMapper;

   //修改轮播图
    @PutMapping
    public Result<?> update(@RequestBody Lunbo lunbo) {
        lunboMapper.updateById(lunbo);
        return Result.success();
    }

//    //查询文章
//    @GetMapping("/findLunbo")
//    public Result<?> findLunbo(){
//        Map<String, Object> results = articleService.findArticle();
//        return Result.success(results);
//    }

    //文章管理
    @GetMapping("/list")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "12") Integer pageSize
                              ){
        LambdaQueryWrapper<Lunbo> wrapper = Wrappers.<Lunbo>lambdaQuery();
        Page<Lunbo> lunboPage = lunboMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(lunboPage);
    }

}
