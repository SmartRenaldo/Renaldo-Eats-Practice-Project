package com.renaldo.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RenaldoEatsApplicationTests {

    @Test
    void getSuffixFileTest() {
        String fileName = "qefqe.qe223fqe.qe";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
    }

}
