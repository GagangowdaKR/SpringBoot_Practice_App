package com.sparksupport.sparktraining.services;

import com.sparksupport.sparktraining.entity.Book;
import com.sparksupport.sparktraining.exceptions.NullValueException;
import com.sparksupport.sparktraining.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {


    private final BookRepository bookRepository;

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> addBooks(List<Book> books) {
        if (books.isEmpty()) throw new NullValueException("At least one or more book is required");
        return bookRepository.saveAll(books);
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Integer bookId){
        if (bookId < 0) throw new NullValueException("Book Id Can't Be Negative");
        return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book Not Found"));
    }

    public List<Book> searchBookTitleLike(String name){
        if (name.isEmpty() || name.equals("null"))
            throw new NullValueException("Book Name Can't Be Null, Specify the name to search");
        return bookRepository.findByTitleLike('%'+name+'%');
    }

    public List<Book> searchBookTitleStartWith(String title) {
        if (title.isEmpty() || title.equals("null"))
            throw new NullValueException("Book Name Can't Be Null, Specify the 'starting name' to search");
        return bookRepository.findByTitleStartWith(title + '%');
    }

    public List<Book> searchBookEndsWith(String title){
        if (title.isEmpty() || title.equals("null"))
            throw new NullValueException("Book Name Can't Be Null, Specify the 'ending name' to search");
        return bookRepository.findByTitleEndingWith(title);
    }

    public List<Object[]> getBooksAuthorCategory() {
        return bookRepository.findAllBooksAuthorCategory();
    }

    public List<Map<String,String>> getBooksCountInCategory() {
        return bookRepository.countBooksInCategory().stream()
                .map(obj -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("Category", String.valueOf(obj[0]));
                    map.put("BookCount", String.valueOf(obj[1]));
                    return map;
                })
                .toList();
    }

    public List<Map<String,String>> getBookAuthors(){
        return bookRepository.findBookAuthors().stream()
                .map(object -> {
                    Map<String,String> map = new HashMap<>();
                    map.put("Author", String.valueOf(object[1]));
                    map.put("BookTitle", String.valueOf(object[0]));
                    return map;
                })
                .toList();
    }

    public List<Map<String, String>> getBooksNotBorrowed(){
        return bookRepository.findBookNotBorrowed().stream()
                .map(object -> {
                    Map<String,String> map = new HashMap<>();
                    map.put("Book Id", String.valueOf(object[0]));
                    map.put("Book Title", String.valueOf(object[1]));
                    return map;
                })
                .toList();
    }

    public Page<Book> getBooksInPages(int page, int size, String sortBy){
        Sort sort = Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageRequest);
    }


    public List<Book> getBookNeverBorrowed() {
        return bookRepository.findBookNeverBorrowed();
    }
}
