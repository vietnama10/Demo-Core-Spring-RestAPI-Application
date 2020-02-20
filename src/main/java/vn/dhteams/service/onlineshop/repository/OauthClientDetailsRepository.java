package vn.dhteams.service.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.dhteams.service.onlineshop.domain.OauthClientDetails;

public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, String> {

	public OauthClientDetails findByClientId(String clientId);
}
