package com.preorder.user.repository;

import com.preorder.user.domain.entity.Follow;
import com.preorder.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByUserIdAndFollowId(User user, User followUser);
}
