package vn.dhteams.service.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.dhteams.service.onlineshop.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByName(String roleName);
}
