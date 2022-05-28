package com.renaldo.controller;

import com.renaldo.common.BaseContextUtils;
import com.renaldo.common.R;
import com.renaldo.dto.ComboDto;
import com.renaldo.pojo.Combo;
import com.renaldo.service.ComboService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/combos")
public class ComboController {

    @Autowired
    private ComboService comboService;

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody ComboDto comboDto) {
        String empUsername = (String) request.getSession().getAttribute("employee");
        BaseContextUtils.setCurrentUsername(empUsername);
        comboService.save(comboDto);

        return R.success("Save successfully!");
    }

    /**
     * paging by page and pageSize, querying by name (%name%) (priority), and sorting by `dateModified` desc
     * @param page
     * @param pageSize
     * @param name
     * @return page object
     */
    @GetMapping("/page")
    public R<Page<Combo>> page(int page, int pageSize, String name) {
        return R.success(comboService.findAllByNameContains(page, pageSize, name));
    }

}
