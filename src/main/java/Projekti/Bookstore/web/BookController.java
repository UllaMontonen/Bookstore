package Projekti.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Projekti.Bookstore.domain.BookRepository;
import Projekti.Bookstore.domain.CategoryRepository;
import Projekti.Bookstore.domain.Book;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	
	
	@Autowired
	private CategoryRepository crepository;
	
		// Show all books
	 	@GetMapping({"/", "/booklist"})
	    public String bookList(Model model) {	
	        model.addAttribute("books", repository.findAll());
	        return "booklist";
	    }
	  
	 	// Add new book
	 	@PreAuthorize("hasAuthority('ADMIN')") // only admin can add books
	    @GetMapping("/add")
	    public String addBook(Model model){
	    	model.addAttribute("book", new Book());
	    	model.addAttribute("categories", crepository.findAll());
	        return "addbook";
	    }     
	    
	    // Save new book
	    @PostMapping("/save")
	    public String save(Book book){
	        repository.save(book);
	        return "redirect:booklist";
	    }    

	    // Delete book
	    @PreAuthorize("hasAuthority('ADMIN')") // only admin can delete books
	    @GetMapping("/delete/{id}")
	    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	    	repository.deleteById(bookId);
	        return "redirect:../booklist";
	    }     
	    
	    // Edit book
	    @PreAuthorize("hasAuthority('ADMIN')") // only admin can edit books
	    @GetMapping("/edit/{id}")
	    public String editBook(@PathVariable("id") Long bookId, Model model) {
	    	model.addAttribute("book", repository.findById(bookId));
	    	model.addAttribute("categories", crepository.findAll());
	    	return "editbook";
	    }   
	
}
