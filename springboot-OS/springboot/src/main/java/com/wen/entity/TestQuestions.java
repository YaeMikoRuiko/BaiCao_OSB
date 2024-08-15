package com.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

//@TableName("application")//mybatis-plus
@Data
public class TestQuestions {
    @TableId(type = IdType.AUTO)
    private Integer testId;
    private List<Choice> choice;
    private List<Fill> fill;
    private List<Judge> judge;
    private List<Short> shorts;
    private List<Application> application;

}
