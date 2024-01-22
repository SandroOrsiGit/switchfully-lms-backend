package com.switchfully.switchfullylmsbackend.repositories;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {
	List<ClassGroup> findByStudentsId(Long userId);
}
