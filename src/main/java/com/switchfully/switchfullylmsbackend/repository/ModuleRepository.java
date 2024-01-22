package com.switchfully.switchfullylmsbackend.repository;

import com.switchfully.switchfullylmsbackend.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}
