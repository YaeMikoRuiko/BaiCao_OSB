package com.wen;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    /*
    * 生成JWT
    * */
    @Test
    public void testGenJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","Tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "yaemiko")//签名算法
                .setClaims(claims)//自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置令牌有效期为一小时
                .compact();
        System.out.println(jwt);
    }
    /*
    * 解析Jwt
    * */
    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("yaemiko")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTcwODQxMjMyOX0.FycbQv3s6UDkB8UTCwqYmOzHClyZXDRL66aKAgp-GAc")
                .getBody();
        System.out.println(claims);
    }
}
