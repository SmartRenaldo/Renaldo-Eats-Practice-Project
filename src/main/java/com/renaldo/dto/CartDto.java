package com.renaldo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renaldo.pojo.Combo;
import com.renaldo.pojo.Customer;
import com.renaldo.pojo.Dish;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tb_cart")
@Data
public class CartDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Dish dish;

    @ManyToOne(cascade = CascadeType.ALL)
    private Combo combo;

    private String dishOption;

    //number
    private Integer number;

    private BigDecimal amount;

    private String image;

    private LocalDateTime createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9:30")
    protected Date dateCreated = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9:30")
    protected Date dateModified = new Date();

    private Integer dishId;

    private Integer comboId;
    
    private Integer customerId;
}
