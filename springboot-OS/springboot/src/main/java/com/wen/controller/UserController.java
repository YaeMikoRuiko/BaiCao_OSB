package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.JwtConfig;
import com.wen.common.Result;
import com.wen.entity.User;
import com.wen.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserMapper userMapper;

//    添加用户的
    @PostMapping
    public Result<?> save(@RequestBody User user){
        if (user.getPassword()==null){
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }
//    更新
    @PutMapping
    public Result<?> update(@RequestBody User user){
        userMapper.updateById(user);
        return Result.success();
    }
    //    删除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        userMapper.deleteById(id);
        return Result.success();
    }
    //    根据id查询
    @GetMapping ("/{id}")
    public Result<?> getById(@PathVariable Long id){
        User user = userMapper.selectById(id);
        return Result.success(user);
    }
//    查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.and(w->w.like(User::getAccount,search)
                    .or().like(User::getId,search)
                    .or().like(User::getUsername,search)
                    .or().like(User::getEmail,search)
                    .or().like(User::getJoinDate,search)
                    .or().like(User::getGender,search)
                    .or().like(User::getPassword,search)
            );
        }
        wrapper.orderByAsc(User::getId);
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
    //    登录
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){//这只是传进来的user，并没有在数据库中进行查找
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAccount,
                user.getAccount()).eq(User::getPassword, user.getPassword()));
        if (res==null){
            return Result.error("-1","用户名或密码错误");
        }
        //生成Jwt
        Map<String,Object> claims=new HashMap<>();
        claims.put("account",user.getAccount());
        claims.put("password",user.getPassword());
        String jwt= JwtConfig.generateJwt(claims);
        //获取用户名基本信息并返回
        String username = res.getUsername();
        String account = res.getAccount();
        Integer role = res.getRole();
        Integer id = res.getId();
        Map<String, Object> result = new HashMap<>();
        result.put("token", jwt);
        result.put("username", username);
        result.put("account", account);
        result.put("role", role);
        result.put("id", id);
        return Result.success(result);
    }


    //注册
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAccount,user.getAccount()));
        if (res!=null){
            return Result.error("-1","用户名重复");
        }
        if (user.getPassword()==null){
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();

    }
}
