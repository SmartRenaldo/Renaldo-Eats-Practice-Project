package com.renaldo.service.impl;

import com.renaldo.repositories.ComboRepository;
import com.renaldo.service.ComboService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    private ComboRepository comboRepository;
}
