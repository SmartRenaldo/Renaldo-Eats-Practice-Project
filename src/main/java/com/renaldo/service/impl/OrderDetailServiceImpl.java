package com.renaldo.service.impl;

import com.renaldo.pojo.OrderDetail;
import com.renaldo.repositories.OrderDetailRepository;
import com.renaldo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void savaAll(List<OrderDetail> orderDetails) {
        orderDetailRepository.saveAll(orderDetails);
    }

    @Override
    public List<OrderDetail> findAllByOrderId(Long orderId) {
        return orderDetailRepository.findAllByOrderId(orderId);
    }
}
