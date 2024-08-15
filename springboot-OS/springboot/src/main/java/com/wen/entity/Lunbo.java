package com.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("lunbo")//mybatis-plus
@Data
public class Lunbo {
    @TableId(type = IdType.AUTO)
    private Integer imgId;
    private String imgName;
    private String imgUrl;
}
