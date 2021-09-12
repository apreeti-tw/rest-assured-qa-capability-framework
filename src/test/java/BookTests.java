import base.BookBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import builders.BookBuilder;
import pojo.Book;
import pojo.Books;
import pojo.Isbn;
import utils.DataProviderUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static enums.EndPoints.ADD_BOOKS_LIST;
import static utils.DataProviderUtils.getData;

public class BookTests extends BookBaseTest {
    @Test(dataProvider = "DataContainer", dataProviderClass = DataProviderUtils.class)
    public void testAddListOfBooks(Map<String,String> book) throws IOException {
        Book books = requestSpecification
                .body(new BookBuilder(book).setBookData())
                .post(ADD_BOOKS_LIST.getEndPoint())
                .then()
                .assertThat()
                .statusCode(Integer.parseInt(book.get("expected")))
                .extract()
                .response()
                .as(Book.class);

        List<String> expectedList = new ArrayList();
        books.getBooks().parallelStream().forEach(bookId-> expectedList.add(bookId.getIsbn()));

        List<String> actualList = new ArrayList();
        Arrays.stream(getData("BookData_Isbn", book.get("testname"))).forEach(isbn ->
                actualList.add(((Map<String,String>)isbn).get("isbn")));

        Assert.assertEquals(expectedList, actualList);
    }
}
