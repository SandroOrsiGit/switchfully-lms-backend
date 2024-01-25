package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.Codelab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodelabRepository extends JpaRepository<Codelab, Long> {
}
