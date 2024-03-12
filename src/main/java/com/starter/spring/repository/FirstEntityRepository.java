package com.starter.spring.repository;

import com.starter.spring.model.FirstEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstEntityRepository extends JpaRepository<FirstEntity, Long> {

}
