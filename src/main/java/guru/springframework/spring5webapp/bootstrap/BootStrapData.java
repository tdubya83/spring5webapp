package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(BootStrapData.class);

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher treshaunaWright = new Publisher("Treshauna Wright",
                "4848 Grand Gate Way, Apt, 2200", "Frisco", "TX", "75034");

        publisherRepository.save(treshaunaWright);

        Author ericEvans = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        ericEvans.getBooks().add(ddd);
        ddd.getAuthors().add(ericEvans);
        ddd.setPublisher(treshaunaWright);
        treshaunaWright.getBooks().add(ddd);

        authorRepository.save(ericEvans);
        bookRepository.save(ddd);
        publisherRepository.save(treshaunaWright);

        Author rodJohnson = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE Development without EJB", "321321");

        rodJohnson.getBooks().add(noEjb);
        noEjb.getAuthors().add(rodJohnson);
        noEjb.setPublisher(treshaunaWright);
        treshaunaWright.getBooks().add(noEjb);

        authorRepository.save(rodJohnson);
        bookRepository.save(noEjb);
        publisherRepository.save(treshaunaWright);

        LOGGER.info("Started in Bootstrap");
        LOGGER.info("Number of Author(s): " + authorRepository.count());
        LOGGER.info("Number of Book(s): " + bookRepository.count());
        LOGGER.info("Number of Publisher(s): " + publisherRepository.count());
        LOGGER.info("Publisher Number of Book(s): " + treshaunaWright.getBooks().size());
    }
}
