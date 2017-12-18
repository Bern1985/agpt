package com.ancs.agpt.security.config;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ancs.agpt.security.access.AncsFilterSecurityInterceptor;
import com.ancs.agpt.security.access.RestAuthenticationAccessDeniedHandler;

@EnableWebSecurity
public class MultiHttpSecurityConfig {
	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
		auth.inMemoryAuthentication().withUser("ancsAdmin").password("123456").roles("USER", "SUPER_ADMIN");
	}*/
	/*@Bean                                                             1
	public UserDetailsService userDetailsService() throws Exception {
		// ensure the passwords are encoded properly
		UserBuilder users = User.withDefaultPasswordEncoder();
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(users.username("user").password("password").roles("USER").build());
		manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build());
		return manager;
	}*/
	@Configuration
	@Order(1)                                                        
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		@Autowired
	    private JwtAuthenticationEntryPoint unauthorizedHandler;

	    @Autowired
	    private JwtAuthenticationProvider authenticationProvider;
	    
	    @Autowired
	    private AccessDecisionManager accessDecisionManager;
	    
	    @Autowired
	    private FilterInvocationSecurityMetadataSource securityMetadataSource;
	    /*@Autowired
	    private AncsFilterSecurityInterceptor ancsFilterSecurityInterceptor;*/
	   
	    
	   @Override
		public void configure(WebSecurity web) throws Exception {
		    web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
		    web.ignoring().antMatchers("/**/*.css");
	        web.ignoring().antMatchers("/**/*.js");
	        web.ignoring().antMatchers("/**/*.html");
	       
		}

	    
	    @Bean
	    @Override
	    public AuthenticationManager authenticationManager() throws Exception {
	        return new ProviderManager(newArrayList(authenticationProvider));
	    }
	    
	 
	    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
	        JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter("/api/**",authenticationManager());
	        //authenticationTokenFilter.setAuthenticationManager(authenticationManager());
	        authenticationTokenFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
	        return authenticationTokenFilter;
	    }
	    
	    public AncsFilterSecurityInterceptor filterSecurityInterceptorBean() throws Exception {
	    	AncsFilterSecurityInterceptor intercepter = new AncsFilterSecurityInterceptor(securityMetadataSource);
	    	intercepter.setAccessDecisionManager(accessDecisionManager);
	        return intercepter;
	    }
	    
	    @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	    	httpSecurity.csrf().disable()
	    	// 基于token，所以不需要session
	    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    	.and()
	    	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
	    	.and()
	    	.authorizeRequests()
	    	 .antMatchers("/", "/*.js","/swagger-ui.html","/*.css").permitAll()
	    	 .antMatchers(HttpMethod.OPTIONS, "/api/**").authenticated();
	    	
	        httpSecurity
	                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	        
	        //在适当的地方加入
	        httpSecurity.addFilterBefore(filterSecurityInterceptorBean(),FilterSecurityInterceptor.class);
	        // disable page caching
	        httpSecurity.headers().cacheControl();
	        
	        httpSecurity.exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
	    }
	    
	    @Bean
	    public AccessDeniedHandler getAccessDeniedHandler() {
	        return new RestAuthenticationAccessDeniedHandler();
	    }
	}
}
