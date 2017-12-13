package com.ancs.agpt.security.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ancs.agpt.security.access.AncsFilterSecurityInterceptor;

import static com.google.common.collect.Lists.*;


@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	
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
	         .antMatchers("/api/**")
	         .authenticated();
	    	
	        httpSecurity
	                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	        
	       // AncsFilterSecurityInterceptor filterSecurityInterceptor = new AncsFilterSecurityInterceptor(securityMetadataSource,accessDecisionManager,authenticationManager());
	        //在适当的地方加入
	        httpSecurity.addFilterBefore(filterSecurityInterceptorBean(),FilterSecurityInterceptor.class);
	        // disable page caching
	        httpSecurity.headers().cacheControl();
	    }

	  
	    /**
	     * 投票器
	     */
	   /*
	    private AbstractAccessDecisionManager accessDecisionManager() {
	        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList();
	        decisionVoters.add(new AuthenticatedVoter());
	        decisionVoters.add(new RoleVoter());//角色投票器,默认前缀为ROLE_
	        RoleVoter AuthVoter = new RoleVoter();
	        AuthVoter.setRolePrefix("AUTH_");//特殊权限投票器,修改前缀为AUTH_
	        decisionVoters.add(AuthVoter);
	        AbstractAccessDecisionManager accessDecisionManager = new AffirmativeBased(decisionVoters);
	        return accessDecisionManager;
	    }

	    @Override
	    public AuthenticationManager authenticationManagerBean() {
	        AuthenticationManager authenticationManager = null;
	        try {
	            authenticationManager = super.authenticationManagerBean();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return authenticationManager;
	    }*/
	
}
