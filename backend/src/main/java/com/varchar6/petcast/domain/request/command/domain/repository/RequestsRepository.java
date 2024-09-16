package com.varchar6.petcast.domain.request.command.domain.repository;


import com.varchar6.petcast.domain.request.command.domain.aggregate.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsRepository extends JpaRepository<Requests, Integer> {

}