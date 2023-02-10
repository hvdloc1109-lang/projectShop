package hvdloc.vn.doan_t3h.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // cách 1 pq controller
public class SecurityConfiguration{

    @Bean
     public PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // cấu hình phạm vị quyền hạng của các đường dẫn
                .authorizeHttpRequests((requests) -> requests
                        //.antMatchers("/backend/product/list").hasAnyRole("ROLE_ADMIN") // cách 2 của phân quyền
                        .antMatchers("/backend/**").authenticated() // có backend thì bắt buộc phải đăng nhập
                        .anyRequest().permitAll()// còn lại không cần phải đăng nhập
                ).anonymous().disable()

                // cấu hình tham số trang login
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("email") // userName ????? name="email" login?????
                        .passwordParameter("password")
                        .loginProcessingUrl("/doLogin")
                        .defaultSuccessUrl("/backend/product/list")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .permitAll());

        return http.build();
    }

}
