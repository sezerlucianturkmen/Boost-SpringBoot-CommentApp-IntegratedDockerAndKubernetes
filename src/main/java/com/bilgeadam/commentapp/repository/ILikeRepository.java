package com.bilgeadam.commentapp.repository;

import com.bilgeadam.commentapp.repository.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILikeRepository extends JpaRepository<Like,Long> {
}
