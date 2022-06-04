package com.renaldo.controller;

import com.renaldo.common.R;
import com.renaldo.dto.OrderDto;
import com.renaldo.pojo.Orders;
import com.renaldo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * customer submit order
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        ordersService.submit(orders);
        return R.success("Order successfully!");
    }

    @GetMapping("/page")
    public R<Page<OrderDto>> page(int page, int pageSize, String name) {

        return R.success(ordersService.findAllByNameContains(page, pageSize, name));
    }
}
