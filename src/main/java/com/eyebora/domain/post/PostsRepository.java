package com.eyebora.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query(value = "SELECT p.* FROM posts p ORDER BY p.id DESC", nativeQuery = true)
    List<Posts> findAllDesc();
}
