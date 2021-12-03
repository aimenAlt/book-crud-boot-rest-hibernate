package com.demo.bookcrudbootresthibernate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bookcrudbootresthibernate.entity.Book;

@Repository
public interface BookRepositoryDao extends JpaRepository<Book, Integer> {

	
	// reference to know more about finder/query methods
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	
	//reference to know more about @Query
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query
	
	// select * from book_details where book_genre='Fiction';
	List<Book> findByBookGenre(String bookGenre);
	
	// select * from book_details where book_genre='Fiction' and book_cost>20;
	List<Book> findByBookGenreAndBookCostGreaterThan(String bookGenre, int bookCost);
}
