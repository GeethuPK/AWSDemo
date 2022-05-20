package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.exception.CustomException;
import com.demo.model.Book;
import com.demo.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book getBookById(int id) {

		return bookRepository.findById(id).orElseThrow(() -> new CustomException("Not found book with id = " + id));

	}

	public List<Book> getAllBooks() {

		return bookRepository.findAll();
	}

	@Transactional
	@Retryable(maxAttempts = 2)
	public Book saveBook(Book book) {

		return bookRepository.save(book);
	}

	@Transactional
	@Retryable(maxAttempts = 2)
	public void deleteBookById(int id) {
		try {
			bookRepository.deleteById(id);
		} catch (EmptyResultDataAccessException exception) {

			throw new EmptyResultDataAccessException("No book with id:" + id + " found", 100);
		}
	}

}
