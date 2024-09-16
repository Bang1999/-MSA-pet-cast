package com.varchar6.petcast.domain.member.command.domain.repository;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
