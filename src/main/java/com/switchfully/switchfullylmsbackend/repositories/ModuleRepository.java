package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.Codelab;
import com.switchfully.switchfullylmsbackend.entities.Course;
import com.switchfully.switchfullylmsbackend.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByCourses(Course course);

    Module findByCodelabs(Codelab codelab);

}
