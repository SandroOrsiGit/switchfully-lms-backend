package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AbstractUser, Long> {
	AbstractUser findByEmail(String email);
}
