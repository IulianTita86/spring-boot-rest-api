package com.example.simplebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simplebooks.model.Book;

/**
 * Created by iuliantita on 16/07/18.
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
}
