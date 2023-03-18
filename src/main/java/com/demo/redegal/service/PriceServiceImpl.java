package com.demo.redegal.service;
import com.demo.redegal.DataBase.H2DatabaseConnection;
import com.demo.redegal.mapper.PriceResponseMapper;
import lombok.SneakyThrows;
import org.openapitools.model.PriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Service
public class PriceServiceImpl  {

    @Autowired
    private H2DatabaseConnection database;

    @Autowired
    private PriceResponseMapper priceResponseMapper;

    private String ISO8601FORMAT = "yyyy-MM-dd'T'HH:mm'Z'";

    @SneakyThrows
    public PriceResponse getPrice(Integer productId, Integer brandId, String rateStartDate){
        String query = "SELECT * FROM PRICES WHERE product_id=? AND brand_id=? AND ? BETWEEN start_date AND end_date";

        PreparedStatement statement = createStatementByQuery(query,productId,brandId,rateStartDate);

        ResultSet resultSet = database.execQuery(statement);

        List<PriceResponse> priceResponses = new ArrayList<>();
        while (resultSet.next()){
            PriceResponse priceResponse = mapResultToPriceResponse(resultSet);
            priceResponses.add(priceResponse);
        }

        return priceResponses
                .stream()
                .filter(PriceResponse::getPriority)
                .findAny()
                .orElse(null);
    }

    private PreparedStatement createStatementByQuery(String query, Integer productId,
                                                     Integer brandId, String rateStartDate) throws SQLException, ParseException {
        PreparedStatement statement = database.getConnection().prepareStatement(query);
        statement.setInt(1,productId);
        statement.setInt(2,brandId);
        statement.setDate(3, parseRequestStringToDate(rateStartDate));
        return statement;
    }

    private java.sql.Date parseRequestStringToDate(String rateStartDate) throws ParseException {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(ISO8601FORMAT); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return new java.sql.Date(df.parse(rateStartDate).getTime());
    }

    private PriceResponse mapResultToPriceResponse(ResultSet resultSet) throws SQLException {
        Integer brandIdResponse = resultSet.getInt("brand_id");
        Integer productIdResponse = resultSet.getInt("product_id");
        Integer priceList = resultSet.getInt("price_list");
        BigDecimal price = resultSet.getBigDecimal("price");
        Boolean priority = resultSet.getBoolean("priority");
        String currency = resultSet.getString("currency");
        Date starDate = resultSet.getDate("start_date");
        return priceResponseMapper.mapResultSetToPriceResponse(brandIdResponse,productIdResponse,
                priceList,price,priority,currency,starDate);
    }
}
