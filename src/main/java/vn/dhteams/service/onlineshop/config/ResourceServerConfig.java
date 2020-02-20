package vn.dhteams.service.onlineshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Override 
    public void configure(HttpSecurity http) throws Exception {
         http
         .authorizeRequests()
         .antMatchers("/swagger-ui.html","/swagger-resources/**", 
        		 "/v2/api-docs/**", "/webjars/**").permitAll()
         .antMatchers("/dhshop/oauth/token").permitAll()
         .anyRequest().authenticated();
         
         http.
         authorizeRequests()
         .antMatchers("/h2-console/**").permitAll()
         .and()
         .headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
         //resources.resourceId(OAuth2Config.RESOURCE_ID);
    }

}
