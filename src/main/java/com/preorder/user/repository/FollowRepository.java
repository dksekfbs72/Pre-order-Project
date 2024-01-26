package com.preorder.user.repository;

import com.preorder.user.domain.entity.Follow;
import com.preorder.user.domain.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByUserIdAndFollowId(User user, User followUser);
    @Query(value = "select f.follow_user_id from follow f where f.user_id = :user", nativeQuery = true)
    List<Long> findUsersByUserId(@Param(value = "user") Long user);
}


