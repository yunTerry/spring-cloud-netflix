package com.teryy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

/***
 * *
 * 名称：     TokenController.
 * 作者：     Terry Tan
 * 创建时间：  on 2018/2/23.
 * 说明：     
 * *
 ***/

@RestController
public class TokenController {

    private Algorithm algorithm;

    public TokenController() {
        if (algorithm == null) {
            try {
                String key = "hd%34#$soe";
                algorithm = Algorithm.HMAC512(key);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/token/generate/{uid}")
    String getToken(@PathVariable String uid) {
        String token = JWT.create()
                .withSubject(uid)
                .withExpiresAt(getExdate(3))
                .sign(algorithm);
        return token;
    }

    @GetMapping("/token/verify")
    boolean verify(@RequestParam String token) {
        if (token == null) {
            throw new JWTVerificationException("token is missing");
        }
        JWTVerifier verifier = JWT.require(algorithm)
                .build(); //Reusable verifier instance
        verifier.verify(token);
        return true;
    }

    private Date getExdate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

}