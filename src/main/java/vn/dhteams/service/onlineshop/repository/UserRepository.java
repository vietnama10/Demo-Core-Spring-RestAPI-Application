package vn.dhteams.service.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.dhteams.service.onlineshop.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserName(String userName);
}
