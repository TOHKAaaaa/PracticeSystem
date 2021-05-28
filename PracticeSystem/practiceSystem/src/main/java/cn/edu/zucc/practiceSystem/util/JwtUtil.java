package cn.edu.zucc.practiceSystem.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    //私钥
    private static final String TOKEN_SECRET = "xchyyds";
    //过期时间
    private static final int EXPIRE_TIME = 12000000;
    //生成token
    public static String sign(int userId,int role) throws UnsupportedEncodingException {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //生成签名
        return JWT.create().withHeader(header)
                .withClaim("userid",userId)
                .withClaim("role",role)
                .withExpiresAt(date).sign(algorithm);
    }

    //解析token
    public static boolean verity(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    public static Map<String, Claim> verifyToken(String token)throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
        if(token == null)
            return null;
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return jwt.getClaims();
    }

    //获取数据
    public static int getValuefromToken(String token,String key) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        int value = jwt.getClaim(key).asInt();
        return value;
    }
}
