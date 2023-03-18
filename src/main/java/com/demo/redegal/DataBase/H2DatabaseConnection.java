package com.demo.redegal.DataBase;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class H2DatabaseConnection{

    private final Connection connection;

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
