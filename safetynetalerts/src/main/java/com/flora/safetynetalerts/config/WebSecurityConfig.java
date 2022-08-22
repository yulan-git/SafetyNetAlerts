package com.flora.safetynetalerts.config;

import com.flora.safetynetalerts.security.jwt.AuthEntryPointJwt;
import com.flora.safetynetalerts.security.jwt.AuthTokenFilter;
import com.flora.safetynetalerts.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)

// La WebSecurityConfigclasse est annotée avec @EnableWebSecuritypour activer la prise en charge de la sécurité Web de Spring Security et fournir l'intégration Spring MVC. Il étend WebSecurityConfigurerAdapteret remplace également quelques-unes de ses méthodes pour définir certaines spécificités de la configuration de la sécurité Web
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    //AuthenticationManagerBuilder --> permet de fournir un parent AuthenticationManager qui sera testé si AuthenticationManager n'a pas réussi à authentifier le fichier Authentification ==> ajouter des utilisateurs avec des rôles prédéfinis
    @Override
    // Pour assigner les rôles d'administrateurs et d'utilisateurs. Ce filtre permet non seulement de créer des identifiants encodés et de les assigner à des rôles
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // permet de configurer la sécurité basée sur le Web pour les requêtes http, il définit un schéma d'authentification en mémoire pour un utilisateur
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                // Pas besoin car utilisation de token
                //Il faut la définir, Si rien ne correspond alors 403
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                //Ne crée pas de session HTTP
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // Pour définir les rôles
                .authorizeRequests()
                // définir l'association des rôles avec les pages
                .antMatchers("/auth/**", "/address/**").permitAll()
                .anyRequest().authenticated();
                //.anyRequest().permitAll();

        //Personnalisation du token à partir du security filter
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
