package builders;

import pojo.Book;
import pojo.Isbn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static utils.DataProviderUtils.getData;

public class BookBuilder {
    private Map<String, String> book;

    public BookBuilder(Map<String, String> book){
        this.book = book;
    }

    public Book setBookData() throws IOException {
        return Book.builder()
                .userId(book.get("userId"))
                .collectionOfIsbns(setCollectionOfIsbns())
                .build();
    }

    private List<Isbn> setCollectionOfIsbns() throws IOException {
        Object[] isbnList = getData("BookData_Isbn", book.get("testname"));
        List<Isbn> isbns = new ArrayList<>();
        Arrays.stream(isbnList).forEach(isbn ->
                isbns.add(Isbn.builder().isbn(((Map<String,String>)isbn).get("isbn")).build()));
        return isbns;
    }
}
