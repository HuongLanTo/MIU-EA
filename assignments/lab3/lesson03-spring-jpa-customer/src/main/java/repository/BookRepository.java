package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Book;
import jakarta.transaction.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByISBN(String isbn);
	}
