package com.kkb.gcp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkb.gcp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
