package com.edu.cundi.cinema.config.security;

import com.edu.cundi.cinema.exception.AuthExceptionOwn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer

public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private String roles[] = { "cliente", "superadmin" };
    // Trae todo lo que configuramos en el SecurityConfig
    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    // De donde se van a crear los tokens y la configuración del resourcesIds
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }

    // Url que vamos a proteger y como
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(new AuthExceptionOwn())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/autores/**").authenticated()
                .antMatchers("/editoriales/**").hasAuthority("superadmin")
                .antMatchers("/libros/**").hasAnyAuthority(roles)
                .antMatchers("/account/signOut/**").authenticated()
                .antMatchers("/account/crear-rol").anonymous()
                .antMatchers("/token/**").permitAll();
    }
}