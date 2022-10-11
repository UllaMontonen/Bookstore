package Projekti.Bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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
	
	// Testataan hakua - ei toimi - laita opettajalle
	@Test
	public void findBook() {
		Book book = bookRepository.findById((long) 5).get();
		System.out.println("Haetaan id:llä 2 " + book);
		assertEquals(book.getAuthor(), "Elly Griffiths");
	}
	
	// Testataan hakua - haetaan Elly Griffithsin kirjoja
	@Test
	public void findByCategory() {
		//haetaan kyseisen kirjailijan kirjat - niitä on yksi
		List<Book> books = bookRepository.findByAuthor("Elly Griffiths");
		assertEquals(books.size(), 1);
	}
	
	// Testataan kirjan luomista
	@Test
	public void insertNewBook() {
		// luodaan uusi kirja
		Book book = new Book("Kirjan Otsikko", "Nimi", 2020, "9789520418168", 7);
		bookRepository.save(book);
		assertNotNull(book.getId());
	}
	
	// Testataan kirjan poistamista
	@Test
	public void deleteBook() {
		Optional<Book> poistettava = bookRepository.findById((long) 5);
		bookRepository.deleteById(poistettava.get().getId());
		
	}
	
	
	
}
