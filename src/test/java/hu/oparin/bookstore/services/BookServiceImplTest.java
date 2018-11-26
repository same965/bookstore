package hu.oparin.bookstore.services;

import hu.oparin.bookstore.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    @Test
    public void checkAuthorWithWholeName() {
        String author = "Pelevin";
        boolean expectedValue = true;
        boolean actualValue = bookService.checkAuthor(author);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void checkAuthorWithDetail() {
        String author = "le";
        boolean expectedValue = true;
        boolean actualValue = bookService.checkAuthor(author);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void checkAuthorWithNull() {
        String author = null;
        boolean expectedValue = false;
        boolean actualValue = bookService.checkAuthor(author);
        assertEquals(expectedValue, actualValue);
    }

}