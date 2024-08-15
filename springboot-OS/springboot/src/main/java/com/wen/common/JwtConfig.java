package com.wen.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtConfig {

    private static String signKey = "BaicaoMiko";
    private static Long expite = 43200000L;


    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)//签名算法
                .setExpiration(new Date(System.currentTimeMillis() + expite))//设置令牌有效期为一小时
                .compact();
        return jwt;
    }
    /*
     * 解析Jwt
     * */

    public static Claims ParseJwt(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
