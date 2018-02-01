package cn.edu.lixin.financialinfomation.config;


import cn.edu.lixin.financialinfomation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Bean
    public PasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }


    @Autowired
    protected void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(bcryptPasswordEncoder());
    }

    @Override
    public void init(WebSecurity web) throws Exception {
        super.init(web);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
        web.ignoring().antMatchers("/signup");
        web.ignoring().antMatchers("/signupSuccess");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/admin**").hasRole("ADMIN")
//                .anyRequest().hasRole("USER")//准许拥被验证角色的用户的请求
                .antMatchers("/edit-profile").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login")
                .and()
                .csrf();



    }
    //登录失败操作
    private AuthenticationFailureHandler loginFailureHandler() {
        return (req,res,exec) ->{
            //添加登录失败的FlashMessage 信息
            res.sendRedirect("/login");
        };

    }

    //登陆成功操作
    private AuthenticationSuccessHandler loginSuccessHandler() {
        return (req, res, auth) ->{
            res.sendRedirect("/");
        } ;
    }

    @Bean
    public EvaluationContextExtension securityExtension() {
        return new EvaluationContextExtensionSupport() {
            @Override
            public String getExtensionId() {
                return "security";
            }

            @Override
            public Object getRootObject() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return new SecurityExpressionRoot(authentication) {};
            }
        };
    }

}
