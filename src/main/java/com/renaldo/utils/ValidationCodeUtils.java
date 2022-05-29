package com.renaldo.utils;

import java.util.Random;

/**
 * Randomly generate verification code utils
 */
public class ValidationCodeUtils {
    /**
     * Randomly generate verification code
     * @param length 4, 5 or 6 digits
     * @return
     */
    public static Integer generateValidateCode(int length){
        Integer code =null;
        if(length == 4){
            //Generate random numbers up to 9999
            code = new Random().nextInt(9999);
            if(code < 1000){
                //Guaranteed random number is 4 digits
                code = code + 1000;
            }
        }else if(length == 5){
            //Generate random numbers up to 99999
            code = new Random().nextInt(99999);
            if(code < 10000){
                //Guaranteed random number is 5 digits
                code = code + 10000;
            }
        }else if(length == 6){
            //Generate random numbers up to 999999
            code = new Random().nextInt(999999);
            if(code < 100000){
                //Guaranteed random number is 6 digits
                code = code + 100000;
            }
        }else{
            throw new RuntimeException("Only 4, 5, and 6 digit verification codes can be generated");
        }
        return code;
    }

    /**
     * Randomly generate a verification code with a specified length of string
     * @param length
     * @return
     */
    public static String generateValidateCode4String(int length){
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        String capstr = hash1.substring(0, length);
        return capstr;
    }
}
