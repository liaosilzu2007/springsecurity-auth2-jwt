package com.lzumetal.springsecurity.util;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @author liaosi
 * @date 2022-08-06
 */
public class JWTUtils {

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsenVtZW50YWwiLCJpYXQiOjE2NTk4NTg1NzcsImV4cCI6MTY1OTk0NDk3NywidXNlcklkIjoiMSJ9.hGCkS5XNEaQvtfTnztL4rTJtQ0vMLjVvn4DDxdyqrsU";
        System.out.println(getUserIdByJwtToken(token));
    }


    // token时效：24小时
    private static final long EXPIRE = 1000 * 60 * 60 * 24;


    // 签名哈希的密钥，对于不同的加密算法来说含义不同
    private static final String SECRET = "5%$k3isp#";


    /**
     * 传入用户生成token
     *
     * @param userId 用户id
     * @return 生成的token
     */
    public static String generateJwtToken(String userId) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("lzumental")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("userId", userId)
                //HmacSHA256进行加密，盐值用一个保存在服务端的密钥
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }




    /**
     * 根据token获取用户id
     *
     * @param jwtToken token字符串
     * @return 解析token后获得的用户id
     */
    public static String getUserIdByJwtToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return null;
        try {
            //如果token过期，解析token会抛出 ExpiredJwtException
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("userId");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
