package com.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("choice")//mybatis-plus
@Data
public class Choice {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String chapter;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private String explanation;
    private Integer correctCount;
    private Integer wrongCount;
    private double difficulty;
}
