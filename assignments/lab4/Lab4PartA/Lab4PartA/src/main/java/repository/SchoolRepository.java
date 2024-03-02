package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.School;

public interface SchoolRepository extends JpaRepository<School, Integer> {

}