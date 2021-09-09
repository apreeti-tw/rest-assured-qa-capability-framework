import base.BookBaseTest;
import org.testng.annotations.Test;
import builders.BookBuilder;
import utils.DataProviderUtils;

import java.io.IOException;
import java.util.Map;

import static enums.EndPoints.ADD_BOOKS_LIST;

public class BookTests extends BookBaseTest {
    @Test(dataProvider = "DataContainer", dataProviderClass = DataProviderUtils.class)
    public void testAddListOfBooks(Map<String,String> book) throws IOException {
        requestSpecification
                .header("Authorization", "")
                .body(new BookBuilder(book).setBookData())
                .post(ADD_BOOKS_LIST.getEndPoint())
                .then()
                .statusCode(Integer.parseInt(book.get("expected")));
    }
}
