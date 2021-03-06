package fx.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fx.model.entity.hr.Hr;
import fx.model.param.resp.RespBean;
import fx.service.local.hr.impl.HrSeriviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    HrSeriviceImpl hrService;

    @Autowired
    CustomFilterInvocationSecurityMetadataSource myFilterConfig;

    @Autowired
    CustomUrlDecisionManager myDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder() {
        //SHA-256 +随机盐+密钥对密码进行加密
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);//不能是接口
    }

    /**
     * 如果访问login页面，不用经过spring security拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login","/js/**", "/css/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //所有请求认证之后才可以访问
                .anyRequest().authenticated()
                //ObjectPostProcessor 的主要作用就是手动注册实例到 Spring 容器中去（并且让实例走一遍 Bean 的生命周期）
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(myDecisionManager);
                        object.setSecurityMetadataSource(myFilterConfig);
                        return object;
                    }
                })
                .and()
                //表单登录
                .formLogin()
                //设置登录字段名
                .usernameParameter("username")
                .passwordParameter("password")
                //设置登录处理的url
                .loginProcessingUrl("/doLogin")
                //设置登录界面，实际上没有登录界面，返回一个json
                .loginPage("/login")
                //登陆成功处理
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        //设置返回json
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        //登录成功后返回的对象
                        Hr hr = (Hr) authentication.getPrincipal();
                        //把用户密码返回设置为空
                        hr.setPassword(null);
                        RespBean ok = RespBean.ok("登陆成功了哟", hr);
                        //将传入的对象序列化为json，返回给调用者
                        String s = new ObjectMapper().writeValueAsString(ok);
                        out.write(s);
                        out.close();
                    }
                })
                //登录失败处理
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                        //设置返回json
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        //登录失败后返回的对象
                        RespBean error = RespBean.error("登录失败");
                        if (exception instanceof LockedException) {
                            error.setMsg("账户被锁定，请联系管理员");
                        } else if (exception instanceof CredentialsExpiredException) {
                            error.setMsg("密码过期，请联系管理员");
                        } else if (exception instanceof AccountExpiredException) {
                            error.setMsg("账户过期，请联系管理员");
                        } else if (exception instanceof DisabledException) {
                            error.setMsg("账户被禁用，请联系管理员");
                        } else if (exception instanceof BadCredentialsException) {
                            error.setMsg("用户名或密码输入错误，请重新输入");
                        }
                        //将传入的对象序列化为json，返回给调用者
                        out.write(new ObjectMapper().writeValueAsString(error));
                        out.close();
                    }
                })
                //跟登录相关的接口直接返回
                .permitAll()
                .and()
                //设置注销登录
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        //设置返回json
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        //将传入的对象序列化为json，返回给调用者
                        out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功")));
                        out.close();
                    }
                })
                .permitAll()
                .and()
                //关闭scrf攻击
                .csrf().disable().exceptionHandling()
                //没有认证时，在这里处理结果，不要重定向，默认是重定向
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
            /**
             *此方法决定请求失败后是要重定向还是去转发，还是想直接在这返回结果
             * @param req
             * @param resp
             * @param authException
             * @throws IOException
             * @throws ServletException
             */
            @Override
            public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                //返回响应码401（没有认证）
                resp.setStatus(401);
                PrintWriter out = resp.getWriter();
                RespBean respBean = RespBean.error("访问失败");
                if (authException instanceof InsufficientAuthenticationException){
                    respBean.setMsg("请求失败请联系管理员");
                }
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
    }
}
