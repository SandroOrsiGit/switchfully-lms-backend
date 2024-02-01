package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.Codelab;
import com.switchfully.switchfullylmsbackend.entities.CodelabProgress;
import com.switchfully.switchfullylmsbackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodelabProgressRepository extends JpaRepository<CodelabProgress, Long> {
//    List<CodelabProgress> findByCodelabAndStudent(Codelab codelab, Student student);
    CodelabProgress findByCodelabAndStudent(Codelab codelab, Student student);
}
