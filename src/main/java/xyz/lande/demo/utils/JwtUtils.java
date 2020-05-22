package xyz.lande.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


public class JwtUtils {

    /**
     * 两个常量
     *      EXPIRE:token过期时间
     *      APP_SECRET:秘钥
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";


    //生成token字符串的方法
    public static String getJwtToken(String userPhone, String userName){

        String JwtToken = Jwts.builder()

                //token的第一部分(JWT头):头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //token的过期时间
                .setSubject("sysuser")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //token的第二部分(有效载荷):存储用户信息
                .claim("userPhone", userPhone)
                .claim("userName",userName)
                //token的第三部分(签名哈希):加密规则
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @param request
     * @return
     */
    public static String getUserPhoneByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userPhone");
    }

    /**
     * 根据token获取会员名
     * @param request
     * @return
     */
    public static String getUserNameByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userName");
    }

    /**
     * 根据token获取claim
     * @param jwt
     * @return
     */
    public static Claims getClaims(String jwt){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(APP_SECRET)
                    .parseClaimsJws(jwt).getBody();
            return claims;
        }catch (Exception exception){
            return null;
        }
    }

}
