package io.murad.movie.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.murad.movie.streaming.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
