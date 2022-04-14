package com.fitnessbuddyapi.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.fitnessbuddyapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	@Query("SELECT username as username FROM  User")
	List<String> findAllUsernames();

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
