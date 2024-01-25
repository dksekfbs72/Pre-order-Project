package com.preorder.user.repository;

import com.preorder.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String loginId);

    boolean existsByEmail(String email);

    boolean existsByName(String name);
}
