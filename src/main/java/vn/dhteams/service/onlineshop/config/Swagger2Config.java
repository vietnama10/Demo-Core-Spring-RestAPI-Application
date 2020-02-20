package vn.dhteams.service.onlineshop.config;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Value("${swagger2.base-package}")
	private String basePackage;
	
	@Value("${swagger2.auth-service}")
	private String authService;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("OnlineShop Rest Api")
				.select()
				.apis(RequestHandlerSelectors.basePackage(basePackage))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiEndPointsInfo())
				.securitySchemes(newArrayList(oauth()))
		        .securityContexts(newArrayList(securityContext()));
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder()
				.title("D&H Online Shop Rest Api")
				.description("AnToanThucPham Management REST API")
				.contact(new Contact("D&H Teams", "www.dhteams.vn", "support@dhteams.vn"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("1.0.0")
				.build();
	}
	
	/* Start Enable Oauth2 */
	private SecurityContext securityContext() {
	    return SecurityContext.builder()
	        .securityReferences(writeAuth())
	        .forPaths(PathSelectors.any())
	        .build();
	}

	List<SecurityReference> writeAuth() {
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[4];
	    authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");
        authorizationScopes[3] = new AuthorizationScope("server", "server");
	    return newArrayList(
	        new SecurityReference("my_oauth2", authorizationScopes));
	}
	
	@SuppressWarnings("deprecation")
	@Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration("browser", "", "", "", "", ApiKeyVehicle.HEADER, "", " ");
    }
	
	private OAuth oauth() {
		List<AuthorizationScope> authorizationScopeList = newArrayList();
        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
        authorizationScopeList.add(new AuthorizationScope("trust", "trust all"));
        authorizationScopeList.add(new AuthorizationScope("write", "access all"));
        authorizationScopeList.add(new AuthorizationScope("server", "server"));
	    return new OAuth("my_oauth2", newArrayList(authorizationScopeList), 
	    		newArrayList(new ResourceOwnerPasswordCredentialsGrant(authService + "/oauth/token")));
	}
	/* End Enable Oauth2 */
}
