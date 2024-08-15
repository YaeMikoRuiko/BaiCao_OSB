package com.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("test_paper")//mybatis-plus
@Data
public class TestPaper {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String testName;
    private Integer authorId;
    private String authorName;
    private String createTime;
    private Integer questionNum;
    private Integer status;

}
