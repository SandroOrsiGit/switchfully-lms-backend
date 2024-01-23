package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.*;
import org.springframework.data.jpa.repository.*;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
