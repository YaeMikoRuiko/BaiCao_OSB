package com.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("comment")//mybatis-plus
@Data
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String questionId;
    private String questionType;
    private String account;
    private String username;
    private String commentText;
    private String createTime;
}
