package vn.dhteams.service.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.dhteams.service.onlineshop.domain.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	public Users findByUserName(String userName);
}
