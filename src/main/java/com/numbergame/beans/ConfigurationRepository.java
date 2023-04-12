package com.numbergame.beans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigutationB, Long>{
    boolean existsById(String propiedadId);

    ConfigutationB findById(String propiedadId);
}
