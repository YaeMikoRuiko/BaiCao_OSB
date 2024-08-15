package com.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("application")//mybatis-plus
@Data
public class Application {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String chapter;
    private String question;
    private String answer;
    private String explanation;
    private Integer correctCount;
    private Integer wrongCount;
    private double difficulty;
}
