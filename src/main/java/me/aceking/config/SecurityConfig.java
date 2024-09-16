package me.aceking.config;

public class SecurityConfig {

    private static final String[] WHITELISTS = {
            "/login", "/register", "swagger-ui/**", "/v1/api-docs",
            "/v1/api-docs/**", "/swagger-ui.html"
    };

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
//            authorizationManagerRequestMatcherRegistry
//                    .requestMatchers(WHITELISTS).permitAll()
//                    .requestMatchers("/v1/admins").hasRole("ADMIN")
//                    .requestMatchers("/v1/users/").hasRole("USER")
//                    .anyRequest().authenticated();
//        });
//        http.csrf(Customizer.withDefaults());
//        return http. build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
