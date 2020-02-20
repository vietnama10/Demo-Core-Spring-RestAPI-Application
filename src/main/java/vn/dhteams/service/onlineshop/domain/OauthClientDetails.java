package vn.dhteams.service.onlineshop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_details")
public class OauthClientDetails implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String clientId;
	private Integer accessTokenValidity;
	private String additionalInformation;
	private String authorities;
	private String authorizedGrantTypes;
	private String autoApprove;
	private String clientSecret;
	private Integer refreshTokenValidity;
	private String resourceIds;
	private String scope;
	private String webServerRedirectUri;

	public OauthClientDetails() {
	}

	public OauthClientDetails(String clientId) {
		this.clientId = clientId;
	}

	public OauthClientDetails(String clientId, Integer accessTokenValidity, String additionalInformation,
			String authorities, String authorizedGrantTypes, String autoapprove, String clientSecret,
			Integer refreshTokenValidity, String resourceIds, String scope, String webServerRedirectUri) {
		this.clientId = clientId;
		this.accessTokenValidity = accessTokenValidity;
		this.additionalInformation = additionalInformation;
		this.authorities = authorities;
		this.authorizedGrantTypes = authorizedGrantTypes;
		this.autoApprove = autoapprove;
		this.clientSecret = clientSecret;
		this.refreshTokenValidity = refreshTokenValidity;
		this.resourceIds = resourceIds;
		this.scope = scope;
		this.webServerRedirectUri = webServerRedirectUri;
	}

	@Id

	@Column(name = "client_id", unique = true, nullable = false)
	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Column(name = "access_token_validity")
	public Integer getAccessTokenValidity() {
		return this.accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	@Column(name = "additional_information")
	public String getAdditionalInformation() {
		return this.additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	@Column(name = "authorities")
	public String getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	@Column(name = "authorized_grant_types")
	public String getAuthorizedGrantTypes() {
		return this.authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	@Column(name = "autoapprove")
	public String getAutoApprove() {
		return this.autoApprove;
	}

	public void setAutoApprove(String autoApprove) {
		this.autoApprove = autoApprove;
	}

	@Column(name = "client_secret")
	public String getClientSecret() {
		return this.clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	@Column(name = "refresh_token_validity")
	public Integer getRefreshTokenValidity() {
		return this.refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	@Column(name = "resource_ids")
	public String getResourceIds() {
		return this.resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	@Column(name = "scope")
	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Column(name = "web_server_redirect_uri")
	public String getWebServerRedirectUri() {
		return this.webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

}
