package com.renaldo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice(annotations = {RestController.class})
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> globalExceptionHandle(SQLIntegrityConstraintViolationException exception) {
        if (exception.getMessage().contains("Duplicate entry")) {
            Pattern p = Pattern.compile("'(.*?)'");
            Matcher m = p.matcher(exception.getMessage());
            String group = "";

            if (m.find()) {
                group = m.group();
            }

            String msg = group + "exists!";

            return R.error(msg);
        }

        if (exception.getMessage()
                .contains("FOREIGN KEY (`category_id`)")) {
            if (exception.getMessage().contains("a foreign key constraint fails (`renaldo_eats`.`tb_dish`")) {
                return R.error("This category is associated with food. Delete failed!");
            } else if (exception.getMessage().contains("a foreign key constraint fails (`renaldo_eats`.`tb_combo`")) {
                return R.error("This category is associated with combo. Delete failed!");
            }

            return R.error("Unknown error");
        }

        return R.error("Unknown error");
    }
}
