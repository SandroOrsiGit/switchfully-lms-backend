package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByClassGroups(ClassGroup classGroup);
}
