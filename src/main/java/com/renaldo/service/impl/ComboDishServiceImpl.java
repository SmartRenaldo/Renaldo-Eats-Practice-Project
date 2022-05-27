package com.renaldo.service.impl;

import com.renaldo.repositories.ComboDishRepository;
import com.renaldo.service.ComboDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ComboDishServiceImpl implements ComboDishService {

    @Autowired
    private ComboDishRepository comboDishRepository;
}
