package Projekti.Bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Projekti.Bookstore.domain.ApplicationUserRepository;
import Projekti.Bookstore.domain.Book;
import Projekti.Bookstore.domain.BookRepository;
import Projekti.Bookstore.domain.CategoryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RepositoryTests {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ApplicationUserRepository applicationUserRepository;
	
	// Testataan hakua
	@Test
	public void findBook() {
		Book book = bookRepository.findById((long) 2).get();
		System.out.println("Haetaan id:llä 2 " + book);
		assertEquals(book.getId(), 2);
	}
	
	
	// Testataan kirjan luomista
	@Test
	public void insertNewBook() {
		Book book = new Book("Kirjan Otsikko", "Nimi", 2020, "9789520418168", 7);
		bookRepository.save(book);
		assertNotNull(book.getId());
	}
	
	// Testataan kirjan poistamista
	@Test
	public void deleteBook() {
		
	}
	
	
	
}
