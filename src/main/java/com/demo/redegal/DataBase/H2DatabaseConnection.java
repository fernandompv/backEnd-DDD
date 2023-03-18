package com.demo.redegal.DataBase;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class H2DatabaseConnection{

    private Connection connection;

    public H2DatabaseConnection( ) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        this.connection = DriverManager.getConnection("jdbc:h2:mem:redegalDB", "sa", "sa");
    }


    public ResultSet execQuery(PreparedStatement statement) throws SQLException {
        ResultSet rs;
        rs = statement.executeQuery();
        return rs;
    }

    public Connection getConnection( ) {
        return connection;
    }
}
