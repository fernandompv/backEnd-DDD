package com.demo.inditex.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Data
public class Prices {
    @Id
    @Column(name = "id", unique = true,nullable = false)
    private Long id;

    @Column(name = "brand_id", unique = true,nullable = false)
    private Integer brandId;

    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @Column(name = "end_date")
    private OffsetDateTime endDate;

    @Column(name = "price_list")
    private Integer priceList;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "priority")
    private Boolean priority;

    @Column(name = "currency")
    private String currency;

}
