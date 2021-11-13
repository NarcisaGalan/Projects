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


    public static final String ADMIN_AUTHORITY = "admin";
    public static final String DIRECTOR_AUTHORITY = "director";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //securitatea web cu permisiuni
        httpSecurity
                .cors().and()
                .csrf().disable()
                .headers()
                .frameOptions().sameOrigin().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/contractTerti/cursValutar").permitAll()
                .antMatchers(HttpMethod.POST,"/contractTerti/add").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/contractTerti/all/byMonth/{data}").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET,"/contractTerti/all/{idUser}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.PUT, "/contractTerti/update").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/act-aditional/getAllByContractTerti/{idContractTerti}").permitAll()
                .antMatchers(HttpMethod.POST, "/act-aditional/add").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/act-aditional/getActAditional/{idActAditional}").permitAll()
                .antMatchers(HttpMethod.PUT, "/act-aditional/edit").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/admin/allDepartamente").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/admin/addMembru").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/admin/sendEmail").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/angajat/searchAngajati/byContracteDirector/{idDirector}/{numeAngajat}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/angajat/addAngajatAndStatFunctii").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET,"/angajat/{idContract}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET,"/angajat/getByStatFunctii/{idStatFunctii}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/angajat/getByName").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/beneficiar/saveBeneficiar").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/beneficiar/{idContract}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/beneficiar/searchByDenumireContaining/{denumire}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/cerereImprumut/add").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.PUT, "/cerereImprumut/update/{id}/{aprobare}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/cerereImprumut/all").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/cerereImprumut/allAprobate/{idContract}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/cerereImprumut/titluProiecte/{idAngajat}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/cheltuiala/add").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/cheltuiala/allTable/{idContract}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/fisiere/incarcare/{idContractTerti}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/fisiere/{idContractTerti}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/incasare/add").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/incasare/all/{idContract}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/raport/cursValutar/{moneda}/{data}").permitAll()
                .antMatchers(HttpMethod.GET, "/raport/buget/{idContract}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/raport/raportLunar/{startDate}/{endDate}/{idContract}").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/statFunctii/add").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.PUT, "/statFunctii/update").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.PUT, "/statFunctii/update/incetareActivitate/{incetareActivitate}").hasAuthority(DIRECTOR_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/user/add").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.PUT, "/user/changePassword/{idUser}").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //adaugare jwt filter
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
