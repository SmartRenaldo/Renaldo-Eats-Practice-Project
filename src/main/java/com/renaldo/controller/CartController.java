package com.renaldo.controller;

import com.renaldo.common.R;
import com.renaldo.dto.CartDto;
import com.renaldo.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * add item into cart
     * @param cartDto
     * @return
     */
    @PostMapping("/add")
    public R<CartDto> add(@RequestBody CartDto cartDto) {
        return R.success(cartService.add(cartDto));
    }

    /**
     * remove item from cart
     * @param cartDto
     * @return
     */
    @PostMapping("/sub")
    public R<CartDto> sub(@RequestBody CartDto cartDto) {
        return R.success(cartService.sub(cartDto));
    }

    /**
     * clear cart
     * @return
     */
    @GetMapping("/list")
    public R<List<CartDto>> list() {
        log.info("list cart...");
        return R.success(cartService.list());
    }

    @Transactional
    @DeleteMapping("/clean")
    public R<String> clean() {
        cartService.clean();

        return R.success("Clean successfully!");
    }
}
