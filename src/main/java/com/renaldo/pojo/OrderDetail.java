package com.renaldo.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Order detail
 */
@Entity
@Table(name = "tb_order_detail")
@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long orderId;

    private Long dishId;

    private Long comboId;

    private String dishOption;

    private Integer number;

    private BigDecimal amount;

    private String image;
}
