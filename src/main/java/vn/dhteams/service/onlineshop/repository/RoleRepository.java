package vn.dhteams.service.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.dhteams.service.onlineshop.domain.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

	public Roles findByName(String roleName);
}
