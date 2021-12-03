package com.demo.bookcrudbootresthibernate.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bookcrudbootresthibernate.dao.BookRepositoryDao;
import com.demo.bookcrudbootresthibernate.entity.Book;
import com.demo.bookcrudbootresthibernate.exception.ApplicationException;
import com.demo.bookcrudbootresthibernate.pojo.BookPojo;

//@Component
@Service
public class BookServiceImpl implements BookService{

	private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
	
	@Autowired
	//BookDao bookDao;
	BookRepositoryDao bookRepositoryDao;
	
	public BookServiceImpl() {
		//this.bookDao = new BookDaoImpl();
		// commented this line so that we can tell the SF
			// to autowire
		//this.bookDao = new BookJdbcDaoImpl();
	}
  
	@Override
	public BookPojo addBook(BookPojo bookPojo) throws ApplicationException {
		logger.info("Entered addBook() in service.");
		//BookPojo returnBookPojo = this.bookDao.addBook(bookPojo);
		Book newBook = new Book(bookPojo.getId(), bookPojo.getBookTitle(), bookPojo.getBookGenre(), bookPojo.getBookAuthor(), bookPojo.getBookCost(), bookPojo.isBookRemoved());
		Book returnBook = bookRepositoryDao.saveAndFlush(newBook);
		bookPojo.setId(returnBook.getBookId());
		logger.info("Exited addBook() in service.");
		return bookPojo;
	}

	@Override
	public BookPojo updateBook(BookPojo bookPojo) throws ApplicationException {
//		logger.info("Entered updateBook() in service.");
//		BookPojo returnBookPojo = this.bookRepositoryDao.saveAndFlush(bookPojo);
//		logger.info("Exited updateBook() in service.");
		return bookPojo;
	}

	@Override
	public boolean deleteBook(int bookId) throws ApplicationException {
		logger.info("Entered deleteBook() in service.");
		this.bookRepositoryDao.deleteById(bookId);
		logger.info("Exited deleteBook() in service.");
		return true;
	}

	@Override
	public List<BookPojo> getAllBooks() throws ApplicationException {
		logger.info("Entered getAllBooks() in service.");
		List<BookPojo> allBooks = new ArrayList<BookPojo>();// = this.bookRepositoryDao.findAll();
		logger.info("Exited getAllBooks() in service.");
		return allBooks;
	}

	@Override
	public BookPojo getABook(int bookId) throws ApplicationException {
//		logger.info("Entered getABook() in service.");
//		BookPojo returnBookPojo = this.bookDao.getABook(bookId);
//		logger.info("Exited getABook() in service.");
		return new BookPojo();
	}

	@Override
	public void exitApplication() {
		// TODO Auto-generated method stub
		
	}

	
}
