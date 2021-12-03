package com.demo.bookcrudbootresthibernate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bookcrudbootresthibernate.entity.Book;

@Repository
public interface BookRepositoryDao extends JpaRepository<Book, Integer> {

	// finder methods
	// select * from book_details where book_genre='Fiction';
	List<Book> findByBookGenre(String bookGenre);
	
	// select * from book_details where book_genre='Fiction' and book_cost>20;
	List<Book> findByBookGenreAndBookCostGreaterThan(String bookGenre, int bookCost);
}
