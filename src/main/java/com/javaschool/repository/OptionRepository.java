package com.javaschool.repository;

import com.javaschool.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
    
}
