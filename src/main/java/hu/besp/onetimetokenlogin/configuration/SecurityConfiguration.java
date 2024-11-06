package hu.besp.onetimetokenlogin.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ott.OneTimeTokenLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Autowired
    EmailingOneTimeTokenHandler emailingOneTimeTokenHandler;

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final var user1 = User.withDefaultPasswordEncoder()
                              .username("user1")
                              .password("pw")
                              .roles("ADMIN", "USER")
                              .build();
        final var user2 = User.withDefaultPasswordEncoder()
                              .username("user2")
                              .password("pw")
                              .roles("USER")
                              .build();

        return new InMemoryUserDetailsManager(
                user1,
                user2
        );
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            final HttpSecurity httpSecurity
    ) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .anyRequest()
                                        .authenticated())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .oneTimeTokenLogin(
                        new Customizer<OneTimeTokenLoginConfigurer<HttpSecurity>>() {
                            @Override
                            public void customize(
                                    OneTimeTokenLoginConfigurer<HttpSecurity> configurer
                            ) {
                                configurer
                                        .tokenGenerationSuccessHandler(emailingOneTimeTokenHandler);
                            }
                        })
                .build();
    }

}
