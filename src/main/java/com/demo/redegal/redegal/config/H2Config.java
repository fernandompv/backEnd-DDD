package com.demo.redegal.redegal.config;

import java.sql.*;
import java.util.List;

public class H2Config {
    public class H2DatabaseConnection{

        private Connection connection;

        public H2DatabaseConnection( ) throws SQLException, ClassNotFoundException {
            Class.forName("org.h2.Driver");
            this.connection = DriverManager.getConnection("jdbc:h2:mem:StarwarsDB", "sa", "sa");
        }


        public ResultSet execQuery(PreparedStatement statement) throws SQLException {
            ResultSet rs;
            rs = statement.executeQuery();
            return rs;
        }

        public PreparedStatement createStatementForQuery(String query, List<String> parameters) throws SQLException {
            PreparedStatement statement = connection.prepareStatement(query);
            for(int i = 0; i < parameters.size(); i++){
                statement.setString(i,parameters.get(1));
            }
            return statement;

        }

        public Connection getConnection( ) {
            return connection;
        }
    }
}
