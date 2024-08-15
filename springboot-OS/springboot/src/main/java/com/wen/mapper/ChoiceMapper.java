package com.wen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wen.entity.Choice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ChoiceMapper extends BaseMapper<Choice> {
    @Select("SELECT id, question, 'choice' as tableName FROM choice WHERE question LIKE CONCAT('%',#{search},'%')")
    List<Map<String, Object>> searchQuestion(@Param("search") String search);
}
