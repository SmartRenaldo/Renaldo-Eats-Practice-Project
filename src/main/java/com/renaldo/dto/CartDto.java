package com.renaldo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renaldo.pojo.Cart;
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

/**
 * @author Yiqi Li
 */
@Data
public class CartDto extends Cart {

    private Long dishId;

    private Long comboId;
    
    private Long customerId;
}
