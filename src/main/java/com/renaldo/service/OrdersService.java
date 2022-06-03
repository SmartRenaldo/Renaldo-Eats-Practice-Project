package com.renaldo.service;

import com.renaldo.common.R;
import com.renaldo.pojo.Orders;

public interface OrdersService {
    R<String> submit(Orders orders);
}
