package com.switchfully.switchfullylmsbackend.repository;

import com.switchfully.switchfullylmsbackend.entity.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {
}
