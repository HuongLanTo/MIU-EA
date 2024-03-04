package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
