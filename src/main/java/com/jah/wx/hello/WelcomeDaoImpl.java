package com.jah.wx.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.beans.ConstructorProperties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by JimmyandHurry on 2015/11/28.
 */
@Repository("welcomeDao")
public class WelcomeDaoImpl implements WelcomeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String QUERY_USER_BY_ID = "SELECT * FROM welcome_users WHERE id = ?";
    private static final String INSERT_USER = "INSERT INTO welcome_users(name, message) VALUES(?,?)";
    public WelcomeMessage getUserById(int id) {
        try {
            WelcomeMessage messages = (WelcomeMessage)jdbcTemplate.queryForObject(
                    QUERY_USER_BY_ID,
                    new Object[]{id},
                    new RowMapper() {
                        public WelcomeMessage mapRow(ResultSet resultSet, int i) throws SQLException {
                            WelcomeMessage item = new WelcomeMessage();
                            item.setName(resultSet.getString("name"));
                            item.setId(resultSet.getInt("id"));
                            item.setMessage(resultSet.getString("message"));
                            return item;
                        }
                    });
            return messages;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int addUser(final WelcomeMessage welcomeMessage) {
        return jdbcTemplate.update(INSERT_USER, new Object[]{welcomeMessage.getName(), welcomeMessage.getMessage()});
    }

}
