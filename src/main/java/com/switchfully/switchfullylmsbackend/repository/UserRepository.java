package com.switchfully.switchfullylmsbackend.repository;

import com.switchfully.switchfullylmsbackend.entity.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AbstractUser, Long> {
	AbstractUser findByEmail(String email);
}
