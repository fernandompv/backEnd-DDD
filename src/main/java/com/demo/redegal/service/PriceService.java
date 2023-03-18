package com.demo.redegal.service;
import com.demo.redegal.DataBase.H2DatabaseConnection;
import com.demo.redegal.mapper.PriceResponseMapper;
import lombok.SneakyThrows;
import org.openapitools.model.PriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PriceService {

    private H2DatabaseConnection database;

    @Autowired
    private PriceResponseMapper priceResponseMapper;

    @SneakyThrows
    public PriceResponse getPrice(Integer productId, Integer brandId, String rateStartDate){
        String query = "SELECT * FROM PRICES WHERE product_id=? AND brand_id=? AND ? BETWEEN start_date AND end_date";

        database = new H2DatabaseConnection();

        PreparedStatement statement = createStatementByQuery(query,productId,brandId,rateStartDate);

        ResultSet resultSet = database.execQuery(statement);

        List<PriceResponse> priceResponses = new ArrayList<>();
        while (resultSet.next()){
            PriceResponse priceResponse = mapResultToPriceResponse(resultSet);
            priceResponses.add(priceResponse);
        }

        return returnPriceWithPriority(priceResponses);
    }

    private PriceResponse returnPriceWithPriority(List<PriceResponse> priceResponses) {
        PriceResponse priceResponse =  priceResponses
                .stream()
                .filter(PriceResponse::getPriority)
                .findFirst()
                .orElse(null);

        if(Objects.isNull(priceResponse) && !priceResponses.isEmpty()){
            priceResponse = priceResponses.get(0);
        }

        return priceResponse;
    }

    private PreparedStatement createStatementByQuery(String query, Integer productId,
                                                     Integer brandId, String rateStartDate) throws SQLException, ParseException {
        PreparedStatement statement = database.getConnection().prepareStatement(query);
        statement.setInt(1,productId);
        statement.setInt(2,brandId);
        statement.setTimestamp(3,parseRequestStringToDate(rateStartDate));
        return statement;
    }

    private Timestamp parseRequestStringToDate(String rateStartDate) throws ParseException {
        DateTimeFormatter isoFormat = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(rateStartDate, isoFormat);
        return Timestamp.valueOf(dateTime);
    }

    private PriceResponse mapResultToPriceResponse(ResultSet resultSet) throws SQLException {
        Integer brandIdResponse = resultSet.getInt("brand_id");
        Integer productIdResponse = resultSet.getInt("product_id");
        Integer priceList = resultSet.getInt("price_list");
        BigDecimal price = resultSet.getBigDecimal("price");
        Boolean priority = resultSet.getBoolean("priority");
        String currency = resultSet.getString("currency");
        Timestamp startDate = resultSet.getTimestamp("start_date");
        return priceResponseMapper.mapResultSetToPriceResponse(brandIdResponse,productIdResponse,
                priceList,price,priority,currency,startDate);
    }
}
