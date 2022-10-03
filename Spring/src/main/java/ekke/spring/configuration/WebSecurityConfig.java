package ekke.spring.configuration;

import ekke.spring.common.Security.CustomMethodSecurityExpressionHandler;
import ekke.spring.configuration.Filter.JwtFilterFactory;
import ekke.spring.service.authentication.AuthManager;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends GlobalMethodSecurityConfiguration{

    @Autowired
    private JwtFilterFactory jwtFilterFactory;

    @Autowired
    private UnauthorizedExceptionHandler unauthorizedExceptionHandler;

    @Autowired
    private AccessDeniedExceptionHandler accessDeniedExceptionHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        CustomMethodSecurityExpressionHandler expressionHandler = new CustomMethodSecurityExpressionHandler();
        return expressionHandler;
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new AuthManager();
    }

    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedExceptionHandler)
                .accessDeniedHandler(accessDeniedExceptionHandler)
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtFilterFactory.create(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().frameOptions().sameOrigin().cacheControl();
        return httpSecurity.build();
    }
}
