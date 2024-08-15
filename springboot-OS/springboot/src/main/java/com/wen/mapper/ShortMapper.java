package com.wen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wen.entity.Short;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
public interface ShortMapper extends BaseMapper<Short> {
    @Select("SELECT id, question, 'short' as tableName FROM short WHERE question LIKE CONCAT('%',#{search},'%')")
    List<Map<String, Object>> searchQuestion(@Param("search") String search);
}
