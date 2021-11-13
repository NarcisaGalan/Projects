package utcn.ti.proiect_licenta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import utcn.ti.proiect_licenta.jwt.JwtAuthenticationEntryPoint;
import utcn.ti.proiect_licenta.jwt.JwtRequestFilter;
import utcn.ti.proiect_licenta.jwt.JwtUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPointBean() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/configuration/**",
                "/webjars/**");
    }


    public static final String ANGAJAT_AUTHORITY = "angajat";
    public static final String ADMIN_AUTHORITY = "admin";
    public static final String DIRECTOR_AUTHORITY = "director";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and()
                .csrf().disable()
                .headers()
                .frameOptions().sameOrigin().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/reservations/needPartner").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers("/testWebsocket/{idAngajat}").permitAll()
                .antMatchers("/app/**", "/ws/**", "/queue/**").permitAll()
                .antMatchers("/locations").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers("/reservations/availability").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers("/subscriptions/availability").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers("/client/changePassword").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers("/tennis-courts/getImage/{fileId}").permitAll()
                .antMatchers(HttpMethod.GET, "/tennis-courts/{id}").permitAll()
                .antMatchers("/tennis-courts/{id}/bookings").permitAll()
                .antMatchers(HttpMethod.POST, "/{idAngajat}/reservations/{idReservation}/partner/accept").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/{idAngajat}/subscriptions").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/{clientId}/subscriptions").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/{idAngajat}/reservations/{idReservation}/partner/{numberOfPartners}").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/{idAngajat}/reservations/{idReservation}/replace").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/{idAngajat}/reservations/{idReservation}/replacement").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/{clientId}/subscriptions").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/clients/{idAngajat}/profilePicture").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers("/reservations/price").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers("/subscriptions/price").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/reservations/requestReplacement").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers("/reservations/price").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/{clientId}/subscriptions").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/clients/{idAngajat}/profilePicture").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers("/reservations/price").hasAuthority("client")
                .antMatchers(HttpMethod.GET, "/tennis-courts/available").permitAll()
                .antMatchers("/{idAngajat}/reservations").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.PUT, "/tennis-courts/**").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/tennis-courts").permitAll()
                .antMatchers("/register", "login", "/tennis-courts/{id}/bookings").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.GET, "/tennis-courts/all").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/tennis-courts").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers("/tennis-courts/{id}/prices").permitAll()
                .antMatchers(HttpMethod.DELETE, "/tennis-courts/{courtId}").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers("/tennis-courts").hasAuthority(ANGAJAT_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/counties").permitAll()
                .antMatchers(HttpMethod.GET, "/cities").permitAll()
                .and()
                .authorizeRequests().antMatchers("/stomp").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
