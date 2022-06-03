package com.renaldo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Order class
 * @author Yiqi Li
 */
@Entity
@Table(name = "tb_order")
@Data
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 1. pending payment,
     * 2. pending delivery,
     * 3. delivered,
     * 4. completed,
     * 5. cancelled
     */
    private Integer status;

    private Long customerId;

    private Long addressId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9:30")
    private Date orderTime = new Date();

    /**
     * 1. Credit or Debit Card
     * 2. Bank card
     * 3. Unionpay
     * 4. Paypal
     */
    private Integer payMethod;

    /**
     * paid money
     */
    private BigDecimal amount;

    private String remark;

    private String customerName;

    private String phone;

    private String address;

    private String name;
}
