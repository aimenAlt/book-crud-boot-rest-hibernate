package com.demo.bookcrudbootresthibernate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		// commented this line so that we can tell the Spring Framework
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
		logger.info("Entered updateBook() in service.");
        //BookPojo returnBookPojo = this.bookRepositoryDao.saveAndFlush(bookPojo);
		Book updateBook = new Book(bookPojo.getId(), bookPojo.getBookTitle(), bookPojo.getBookGenre(), bookPojo.getBookAuthor(), bookPojo.getBookCost(), bookPojo.isBookRemoved());
		// we use save method for update also, the primary key is used 
			//to figure out whether it is an insert or update by spring
			//data jpa, if primary key exists it is an update, else insert
		Book returnBook = bookRepositoryDao.save(updateBook);
		logger.info("Exited updateBook() in service.");
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
		
		//call the findAll method to fetch all the records
		List<Book> allBooksEntity = this.bookRepositoryDao.findAll();
		
		List<BookPojo> allBooksPojo = new ArrayList<BookPojo>();
		//iterating through the collection of book entities(allBooksEntity) and copying them 
				//into a collection of book pojos (allBooksPojo) 
		allBooksEntity.forEach((book)-> {
			BookPojo bookPojo = new BookPojo(book.getBookId(), book.getBookTitle(), book.getBookGenre(), book.getBookAuthor(), book.getBookCost(), book.isBookRemoved());
			allBooksPojo.add(bookPojo);
		});
		logger.info("Exited getAllBooks() in service.");
		return allBooksPojo;
	}

	@Override
	public BookPojo getABook(int bookId) throws ApplicationException {
		logger.info("Entered getABook() in service.");
//		BookPojo returnBookPojo = this.bookDao.getABook(bookId);
		BookPojo bookPojo = null;
		// call the findById to fetch a record by ID
		// findById returns java.util.Optional which contains the book entity 
		Optional<Book> optional = this.bookRepositoryDao.findById(bookId);
		if(optional.isPresent()) {
			//take out the book entity from the optional and store 
				// in a Book reference
			Book book = optional.get();
			// copying entity into pojo
			bookPojo = new BookPojo(book.getBookId(), book.getBookTitle(), book.getBookGenre(), book.getBookAuthor(), book.getBookCost(), book.isBookRemoved());
		}
		logger.info("Exited getABook() in service.");
		return bookPojo;
	}

	@Override
	public void exitApplication() {
		// TODO Auto-generated method stub
		// no implementation required for closing connection here 
			//as Spring Data will take care 
			// of the connections
	}

	
}
