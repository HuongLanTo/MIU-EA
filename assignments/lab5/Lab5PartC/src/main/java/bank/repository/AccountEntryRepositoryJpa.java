package bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.domain.AccountEntry;

public interface AccountEntryRepositoryJpa extends JpaRepository<AccountEntry,Integer> {

}
