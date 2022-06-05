package com.renaldo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9:30")
    private String beginTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9:30")
    private String endTime;

    private int pageSize;

    private int page;

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
