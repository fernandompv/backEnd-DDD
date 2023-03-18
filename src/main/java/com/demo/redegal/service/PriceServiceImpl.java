package com.demo.redegal.service;
import com.demo.redegal.DataBase.H2DatabaseConnection;
import lombok.SneakyThrows;
import org.openapitools.model.PriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Service
public class PriceServiceImpl  {

    @Autowired
    private H2DatabaseConnection database;

    private String ISO8601FORMAT = "yyyy-MM-dd'T'HH:mm'Z'";

    @SneakyThrows
    public PriceResponse getPrice(Integer productId, Integer brandId, String rateStartDate){
        String query = "SELECT * FROM PRICES WHERE PRODUCT_ID=? AND BRAND_ID=? AND START_DATE=?";
        PreparedStatement statement = database.getConnection().prepareStatement(query);
        statement.setInt(1,productId);
        statement.setInt(2,brandId);
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(ISO8601FORMAT); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        statement.setDate(3, new java.sql.Date(df.parse(rateStartDate).getTime()));
        ResultSet resultSet = database.execQuery(statement);
        Integer brandIdResponse = resultSet.getInt(1);
        Integer priceList = resultSet.getInt("PRICE_LIST");
        Integer productIdResponse = resultSet.getInt("PRODUCT_ID");
        BigDecimal price = resultSet.getBigDecimal("PRICE");
        String currency = resultSet.getString("CURRENCY");
        return new PriceResponse();
    }
}
