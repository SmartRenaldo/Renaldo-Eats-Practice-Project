package com.renaldo.controller;

import com.renaldo.service.ComboService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/combos")
public class ComboController {

    @Autowired
    private ComboService comboService;


}
