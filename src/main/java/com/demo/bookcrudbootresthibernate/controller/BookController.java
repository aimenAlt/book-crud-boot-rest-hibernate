package com.demo.bookcrudbootresthibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bookcrudbootresthibernate.exception.ApplicationException;
import com.demo.bookcrudbootresthibernate.pojo.BookPojo;
import com.demo.bookcrudbootresthibernate.service.BookService;

@RestController
@RequestMapping("api") // optional
public class BookController {

	// we want something like this
	//BookService bookService = new BookServiceImpl();
	@Autowired // we are telling the framework to inject a
				// bookServiceImpl object to 
				//the field/property bookService
	BookService bookService;
	
	@PostMapping("books")
	BookPojo addBook(@RequestBody BookPojo bookPojo) throws ApplicationException{
		return bookService.addBook(bookPojo);
	}
	
	@PutMapping("books/{bid}")
	BookPojo updateBook(@RequestBody BookPojo bookPojo) throws ApplicationException{
		return bookService.updateBook(bookPojo);
	}
	
	@DeleteMapping("books/{bid}")
	boolean deleteBook(@PathVariable("bid") int bookId) throws ApplicationException{
		return bookService.deleteBook(bookId);
	}
	
	@GetMapping("books")
	List<BookPojo> getAllBooks() throws ApplicationException{
		return bookService.getAllBooks();
	}
	
	@GetMapping("books/{bid}")
	BookPojo getABook(@PathVariable("bid") int bookId) throws ApplicationException{
		return bookService.getABook(bookId);
	}
	
//	@GetMapping("books/pending")
//	BookPojo getPendingBook(int bookId) throws ApplicationException{
//		return bookService.getABook(bookId);
//	}
}
