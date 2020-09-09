package codes.neuralkatana.springframework.spring5webapp.bootstrap;

import codes.neuralkatana.springframework.spring5webapp.domain.Author;
import codes.neuralkatana.springframework.spring5webapp.domain.Book;
import codes.neuralkatana.springframework.spring5webapp.domain.Publisher;
import codes.neuralkatana.springframework.spring5webapp.repositories.AuthorRepository;
import codes.neuralkatana.springframework.spring5webapp.repositories.BookRepository;
import codes.neuralkatana.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

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
        Author wg = new Author("William","Gibson");
        Book neuro = new Book("Neuromancer", "11111");

        Author rick = new Author("Rick", "Prisley");
        Book wh40kr = new Book("Warhammer 40k Rogue Trader", "111122");

        Publisher gw = new Publisher("GamesWorkshop");
        gw.setCity("Nothingham");
        gw.setAddress("Willow Road, Lenton, NG7 2WS");
        gw.setCountry("England");

        Publisher al = new Publisher("Aleph");
        al.setAddress("Rua Tabapuã, 81 - Itaim Bibi");
        al.setCity("São Paulo");
        al.setCountry("Brazil");

        wg.getBooks().add(neuro);
        neuro.getAuthors().add(wg);

        rick.getBooks().add(wh40kr);
        wh40kr.getAuthors().add(rick);



        authorRepository.save(wg);
        bookRepository.save(neuro);
        authorRepository.save(rick);
        bookRepository.save(wh40kr);

        wh40kr.setPublisher(gw);
        gw.getBooks().add(wh40kr);
        neuro.setPublisher(al);
        al.getBooks().add(neuro);

        publisherRepository.save(al);
        publisherRepository.save(gw);
        bookRepository.save(wh40kr);
        bookRepository.save(neuro);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Authors"+authorRepository.count());
        System.out.println("Numbers of Books:"+bookRepository.count());
        System.out.println("Number of Publishers"+publisherRepository.count());
    }
}
