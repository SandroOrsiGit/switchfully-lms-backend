package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.Codelab;
import com.switchfully.switchfullylmsbackend.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodelabRepository extends JpaRepository<Codelab, Long> {
    List<Codelab> findByModule(Module module);
    List<Codelab> findByModuleId(Long moduleId);
}
