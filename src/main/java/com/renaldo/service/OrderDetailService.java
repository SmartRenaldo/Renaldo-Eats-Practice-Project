package com.renaldo.service;

import com.renaldo.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    void savaAll(List<OrderDetail> orderDetails);

    List<OrderDetail> findAllByOrderId(Long orderId);
}
