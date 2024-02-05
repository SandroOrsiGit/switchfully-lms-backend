package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
