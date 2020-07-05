package com.springboot.web.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.web.principal.entity.Person;

@Repository
public interface  PersonRepository extends JpaRepository<Person, Long> {

}
