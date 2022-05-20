package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.Response;
import com.demo.model.Book;
import com.demo.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(value = "/{id}")
	public Book getBookById(@PathVariable("id") int id) {
		Book book = bookService.getBookById(id);

		return book;
	}

	@GetMapping("/allBooks")
	public List<Book> getAllBooks() {

		return bookService.getAllBooks();
	}

	@PutMapping("/updateBook/{id}")
	public Response updateBookById(@PathVariable("id") int id, @RequestBody Book book) {

		Book bookToBeUpdated = bookService.getBookById(id);
		
		bookToBeUpdated.setName(book.getName());
		
		bookService.saveBook(bookToBeUpdated);
		
		return new Response("SUCCESS", "Book with id :" + id + "updated successfully");
		
	}

	@PostMapping("/saveBook")
	public Book updateBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}

	@DeleteMapping("/delete/{id}")
	public Response deleteBookById(@PathVariable("id") int id) {

		bookService.deleteBookById(id);
		return new Response("SUCCESS", "Book with id :" + id + "deleted successfully");
	}

}
