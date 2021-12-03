package com.demo.bookcrudbootresthibernate.service;

import java.util.List;

import com.demo.bookcrudbootresthibernate.exception.ApplicationException;
import com.demo.bookcrudbootresthibernate.pojo.BookPojo;

public interface BookService {
	BookPojo addBook(BookPojo bookPojo) throws ApplicationException;
	BookPojo updateBook(BookPojo bookPojo) throws ApplicationException;
	boolean deleteBook(int bookId) throws ApplicationException;
	List<BookPojo> getAllBooks() throws ApplicationException;
	BookPojo getABook(int bookId) throws ApplicationException;
	void exitApplication(); 
}
