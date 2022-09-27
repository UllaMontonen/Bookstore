package Projekti.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Projekti.Bookstore.domain.ApplicationUser;
import Projekti.Bookstore.domain.ApplicationUserRepository;
import Projekti.Bookstore.domain.Book;
import Projekti.Bookstore.domain.BookRepository;
import Projekti.Bookstore.domain.Category;
import Projekti.Bookstore.domain.CategoryRepository;




@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, ApplicationUserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Mystery"));
			crepository.save(new Category("Romance"));
			crepository.save(new Category("Fantasy"));
			
			brepository.save(new Book("Päättäjäisilta", "Matthew Costello", 2020, "9789520418168", 7, crepository.findByName("Mystery").get(0)));
			brepository.save(new Book("Käärmeen kirous", "Elly Griffiths", 2019, "9789520404512", 22, crepository.findByName("Mystery").get(0)));
			brepository.save(new Book("Taru sormusten herrasta", "J.R.R. Tolkien", 1955, "978-951-0-33337-2", 55, crepository.findByName("Fantasy").get(0)));
			
			ApplicationUser user1 = new ApplicationUser("Milla", "Mallikas", "ADMIN", "admin", "$2a$10$F9iLLq975Pff5G6X3oQvgujHqXbx4Dgakjwbj827pJsgZQETLxodi");
			ApplicationUser user2 = new ApplicationUser("Matti", "Mallikas", "USER", "user", "$2a$10$pcfwxxN.dS6IJKnGYfHr4.LYpmWhcpnJDDSg6GGEmAW5CjwZ0sIEy");
			urepository.save(user1);
			urepository.save(user2);
			
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
