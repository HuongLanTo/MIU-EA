package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
