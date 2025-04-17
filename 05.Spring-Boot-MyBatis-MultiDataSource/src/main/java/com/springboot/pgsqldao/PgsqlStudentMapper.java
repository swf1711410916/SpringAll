package com.springboot.pgsqldao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PgsqlStudentMapper {
    List<Map<String, Object>> getAllStudents();
}
