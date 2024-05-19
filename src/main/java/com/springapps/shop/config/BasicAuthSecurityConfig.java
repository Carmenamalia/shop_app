//package com.springapps.shop.config;
//
//import com.springapps.shop.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class BasicAuthSecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    UserDetailsServiceImpl userDetailsService;
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
////Această metodă face parte din configurarea securității într-o aplicație web Java utilizând Spring Security.
//// Această configurație specifică este construită cu ajutorul metodei configure dintr-o clasă care extinde WebSecurityConfigurerAdapter.
//        return http // Această metodă configure primește un obiect HttpSecurity ca parametru și returnează un obiect SecurityFilterChain.
//                // HttpSecurity este folosit pentru a configura setările de securitate pentru HTTP.
//                .csrf(csrf -> csrf.disable())//Dezactivează suportul pentru protecția împotriva atacurilor CSRF (Cross-Site Request Forgery).
//                // CSRF este o vulnerabilitate de securitate care poate apărea atunci când un atacator induce un utilizator să efectueze
//                // acțiuni nedorite pe care le poate prezenta un site web.
//                .authorizeHttpRequests(auth ->
//                        /*Această metodă este utilizată pentru a configura regulele de autorizare.
//                       În cadrul blocului auth -> ..., se specifică regulile de autorizare pentru diferite tipuri de cereri HTTP.*/
//                        auth.requestMatchers("/category/**").permitAll()
//                                /*".requestMatchers("/category/**").permitAll():" Permite toate cererile care încep cu "/category/".
//                                 Cu alte cuvinte, aceste cereri nu necesită autentificare.*/
//                                .requestMatchers(HttpMethod.POST, "/product/**").hasRole("ADMIN")
//                                /* Cererile de tip POST către "/product/"
//                                 sunt permise doar pentru utilizatorii cu rolul "ADMIN".
//                                 Acesta este un exemplu de restricție bazată pe rol.*/
//                                .anyRequest().authenticated())
//                                /*Orice altă cerere trebuie să fie autentificată.
//                                Aceasta specifică că toate celelalte cereri necesită autentificare.*/
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                /*Configurează managementul sesiunii.
//                În acest caz, politica de creare a sesiunii este stabilită ca STATELESS, ceea ce înseamnă că nu se va crea sau utiliza o sesiune (fără stare).
//                 Acest lucru este folosit adesea în aplicațiile cu autentificare bazată pe token-uri (de exemplu, JSON Web Tokens - JWT).*/
//                .httpBasic(Customizer.withDefaults())
//                    /*configurează autentificarea HTTP basic.
//                     Autentificarea HTTP basic implică transmiterea credențialelor (nume de utilizator și parolă) în antetul HTTP.
//                     Metoda .httpBasic(Customizer.withDefaults()) configurează un filtru de autentificare HTTP basic cu opțiunile implicite
//                     furnizate de Spring Security.
//                "Customizer.withDefaults()" furnizează un set de valori implicite pentru configurarea autentificării HTTP basic.
//                 Aceste valori includ utilizatorul și parola implicite, care pot fi personalizate mai departe după nevoie.*/
//                .build();//Finalizează configurarea și construiește obiectul SecurityFilterChain care va fi returnat.
//
//        //Această metodă configurează detaliile specifice ale securității pentru aplicația noastra Spring,
//        // inclusiv gestionarea autorizării și configurarea politicii de sesiune.
//    }
//}
