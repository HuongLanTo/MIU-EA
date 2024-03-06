package bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.domain.TraceRecord;

public interface TraceRecordRepository extends JpaRepository<TraceRecord, Long> {

}
