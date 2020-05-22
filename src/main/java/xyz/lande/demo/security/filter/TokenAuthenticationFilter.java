package xyz.lande.demo.security.filter;


import io.jsonwebtoken.Claims;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import xyz.lande.demo.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: lande
 * @date: 2020/5/22
 * @description: 过滤器告诉spring-security是否登录，是什么角色，拥有什么权限
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
    private RedisTemplate redisTemplate;

    //之所以redisTemplate能生效，是因为该RedisTemplate是在MyWebSecurityConfig传入的
    public TokenAuthenticationFilter(AuthenticationManager authManager, RedisTemplate redisTemplate) {
        super(authManager);
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = request.getHeader("token");
        String jwtToken = (String) redisTemplate.opsForValue().get("token");
        if (token == null || !token.equals(jwtToken) ){
            //token为空时，直接放行到下一条过滤器（此时SecurityContext中没有任何权限，放行后会被最终的过滤器检测到无权限，然后禁止访问）
            chain.doFilter(request, response);
            return;
        }
        //根据token获得authenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
        //将authenticationToken存入SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        chain.doFilter(request, response);
    }
    /**
     * 这里从token中获取用户信息并新建一个UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        if (token == null || !token.equals(token) ){

        }

        Claims claims = JwtUtils.getClaims(token);
        String userPhone = (String) claims.get("userPhone");
        String userName = (String) claims.get("userName");
        if (userPhone != null && userName != null) {
            /**
             * 1、从redis中取出用户拥有的角色
             * 2、将其转化为SimpleGrantedAuthority
             * 3、封装至UsernamePasswordAuthenticationToken，方便后面鉴权时取出
             *   UsernamePasswordAuthenticationToken是接口Authentication的一个实现类
             */
            List<String> roleCodes = (List<String>)redisTemplate.opsForValue().get(userPhone);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for(String roleCode : roleCodes) {
                if(StringUtils.isEmpty(roleCode)) continue;
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleCode);
                authorities.add(authority);
            }
            return new UsernamePasswordAuthenticationToken(userPhone, userName, authorities);
        }
        return null;
    }
}