package com.renaldo.service;

import com.renaldo.common.R;
import com.renaldo.dto.OrderDto;
import com.renaldo.pojo.Orders;
import org.springframework.data.domain.Page;

public interface OrdersService {
    R<String> submit(Orders orders);

    Page<OrderDto> findAllByOrderDto(OrderDto orderDto);

    void update(OrderDto orderDto);
}
