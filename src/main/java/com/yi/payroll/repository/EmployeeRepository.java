package com.yi.payroll.repository;

import com.yi.payroll.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EmployeeRepository(final NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAll(){
        final String sql = "select id, name, role from employee";

        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    public Employee get(Long id){
        final String sql = "select id, name, role from employee where id = :id";
        final MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, params, new EmployeeRowMapper());
    }

    private class EmployeeRowMapper implements RowMapper<Employee>{

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(rs.getLong("id"), rs.getString("name"), rs.getString("role"));
        }
    }
}
