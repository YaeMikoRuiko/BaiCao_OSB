package com.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("test_paper_question")//mybatis-plus
@Data
public class TestPaperQuestion {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer testId;
    private Integer questionId;
    private String questionType;
}
