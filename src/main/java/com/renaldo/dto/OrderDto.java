package com.renaldo.dto;

import com.renaldo.pojo.OrderDetail;
import com.renaldo.pojo.Orders;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class OrderDto extends Orders {

    private List<OrderDetail> orderDetails;

    public OrderDto(Orders o) {
        this.setAddress(o.getAddress());
        this.setAddressId(o.getAddressId());
        this.setAmount(o.getAmount());
        this.setCustomerId(o.getCustomerId());
        this.setCustomerName(o.getCustomerName());
        this.setId(o.getId());
        this.setName(o.getName());
        this.setOrderTime(o.getOrderTime());
        this.setPayMethod(o.getPayMethod());
        this.setPhone(o.getPhone());
        this.setRemark(o.getRemark());
        this.setStatus(o.getStatus());
    }
}
