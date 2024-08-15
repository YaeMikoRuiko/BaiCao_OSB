package com.wen.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("article")//mybatis-plus
@Data
public class Article {
    @TableId(type = IdType.AUTO)
    private Integer articleId;
    private String title;
    private String content;
    private Integer authorId;
    private String createTime;
    private Integer status;
    private String type;
}
