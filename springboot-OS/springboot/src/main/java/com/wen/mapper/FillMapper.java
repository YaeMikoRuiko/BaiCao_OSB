package com.wen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wen.entity.Fill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
public interface FillMapper extends BaseMapper<Fill> {
    @Select("SELECT id, question, 'fill' as tableName FROM fill WHERE question LIKE CONCAT('%',#{search},'%')")
    List<Map<String, Object>> searchQuestion(@Param("search") String search);
}
