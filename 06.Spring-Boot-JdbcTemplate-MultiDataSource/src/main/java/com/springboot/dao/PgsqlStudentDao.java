package com.springboot.dao;

import java.util.List;
import java.util.Map;

public interface PgsqlStudentDao {
    List<Map<String, Object>> getAllStudents();
}
