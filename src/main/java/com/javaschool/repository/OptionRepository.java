package com.javaschool.repository;

import com.javaschool.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  OptionRepository extends CrudRepository<Option, Long> {

}
