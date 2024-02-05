package com.switchfully.switchfullylmsbackend.repositories;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {
	List<ClassGroup> findByStudentsId(Long userId);
	List<ClassGroup> findByCoachesId(Long userId);
	Optional<ClassGroup> findClassGroupById(Long classGroupId);
}
