package bank.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import bank.domain.Account;

public interface AccountRepository extends MongoRepository<Account, Long> {

}
