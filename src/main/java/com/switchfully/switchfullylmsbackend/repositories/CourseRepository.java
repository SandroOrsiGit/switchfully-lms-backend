package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByClassGroup(ClassGroup classGroup);
}
