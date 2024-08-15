package com.wen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wen.entity.Application;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
public interface ApplicationMapper extends BaseMapper<Application> {
    @Select("SELECT id, question, 'application' as tableName FROM application WHERE question LIKE CONCAT('%',#{search},'%')")
    List<Map<String, Object>> searchQuestion(@Param("search") String search);
}
