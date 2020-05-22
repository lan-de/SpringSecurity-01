package xyz.lande.demo.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import xyz.lande.demo.security.filter.TokenAuthenticationFilter;
import xyz.lande.demo.security.filter.TokenLoginFilter;
import xyz.lande.demo.security.handler.MyAccessDeniedHandler;
import xyz.lande.demo.security.handler.MyAuthorizedEntryPoint;
import xyz.lande.demo.security.handler.MyLogoutHandler;
import xyz.lande.demo.security.handler.MyUserDetailsService;

/**
 * @author: lande
 * @date: 2020/5/22
 * @description: SpringSecurity配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启注解模式！！
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    //未授权
    @Autowired
    private MyAuthorizedEntryPoint myAuthorizedEntryPoint;

    //访问拒绝
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    //在WebSecurityConfig中注入，为了后面传入其他的组件
    @Autowired
    private RedisTemplate redisTemplate;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式等,数据库中存储的是加密后的密码
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http相关的配置，包括登入登出、异常处理、会话管理等
        http.cors().and().csrf().disable();//关闭了csrf拦截的过滤器
        http.authorizeRequests().
//                antMatchers("/getUser").hasAuthority("query_user").
                //所有请求都需要被认证
                anyRequest().authenticated().
                and().formLogin().usernameParameter("userphone").permitAll().//允许所有用户
                and().logout().logoutUrl("/user/logout").addLogoutHandler(new MyLogoutHandler(redisTemplate)).
                //未认证和未授权时的处理
                and().exceptionHandling().authenticationEntryPoint(myAuthorizedEntryPoint).accessDeniedHandler(myAccessDeniedHandler).
                and()
                //关闭session  用token验证，所以关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and()
                    //不能用自动装配方式，因为authenticationManager不能自动装配
                    //登录过滤器，同时成功后创建token，该过滤器因为没有注入到spring容器中，所以创建一个构造方法，在配置中将redisTemplate传入该过滤器中
                    .addFilter(new TokenLoginFilter(authenticationManager(), redisTemplate))
                   //Token，同时成功后创建token，该过滤器因为没有注入到spring容器中，所以创建一个构造方法，在配置中将redisTemplate传入该过滤器中
                    .addFilter(new TokenAuthenticationFilter(authenticationManager(), redisTemplate)).httpBasic();

    }
}
