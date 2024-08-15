package com.wen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wen.entity.Judge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
public interface JudgeMapper extends BaseMapper<Judge> {
    @Select("SELECT id, question, 'judge' as tableName FROM judge WHERE question LIKE CONCAT('%',#{search},'%')")
    List<Map<String, Object>> searchQuestion(@Param("search") String search);
}
