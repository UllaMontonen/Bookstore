package Projekti.Bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Projekti.Bookstore.domain.Book;
import Projekti.Bookstore.domain.BookRepository;


@RestController
public class RestBookController {

	@Autowired
	BookRepository bookRepository;
	
	
	//return list of books
    @GetMapping("/books")
    public Iterable<Book> getBooks() {	
        return bookRepository.findAll();
    }    

	//return certain book
    @GetMapping("/book/{id}")
    public Optional<Book> findBooktRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findById(bookId);
    }
    
    //add new book
    @PostMapping("books")
    Book newBook(@RequestBody Book newBook) {
    	return bookRepository.save(newBook);
    }
    
    //edit existing book
    @PutMapping("/books/{id}")
    Book editBook(@RequestBody Book editBook, @PathVariable long id) {
    	editBook.setId(id);
    	return bookRepository.save(editBook);
    }
    
    //delete book
    @DeleteMapping("/books/{id}")
    public Iterable<Book> deleteBook(@PathVariable Long id) {
    	bookRepository.deleteById(id);
    	return bookRepository.findAll();
    }
    
    
    
    
    
}
