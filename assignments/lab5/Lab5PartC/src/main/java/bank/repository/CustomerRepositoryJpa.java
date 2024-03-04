package bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.domain.Customer;

public interface CustomerRepositoryJpa extends JpaRepository<Customer,Integer> {

}
