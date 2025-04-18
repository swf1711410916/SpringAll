package com.springboot.dao.impl;

import com.springboot.dao.PgsqlStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
@Repository
public class PgsqlStudentDaoImp implements PgsqlStudentDao {
    @Autowired
    @Qualifier("pgsqlJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Map<String, Object>> getAllStudents() {
        return jdbcTemplate.queryForList("select * from student");
    }
}
