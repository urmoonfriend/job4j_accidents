package kz.job4j.accidents.mapper;

import kz.job4j.accidents.model.AccidentType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccidentTypeMapper implements RowMapper<AccidentType> {
    public AccidentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccidentType type = new AccidentType();
        type.setId(rs.getInt("id"));
        type.setName(rs.getString("name"));
        return type;
    }
}
