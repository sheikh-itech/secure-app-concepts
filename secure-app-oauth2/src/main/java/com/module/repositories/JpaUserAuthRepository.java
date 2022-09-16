package com.module.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.beans.User;

@Repository
public interface JpaUserAuthRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username);
}
