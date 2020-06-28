package io.murad.movie.streaming.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.murad.movie.streaming.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//User findByUserEmail(String email);
	Optional<User> findByUserName(String userName);
}
