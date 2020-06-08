package entities;

import javax.xml.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author implements Serializable {

    private int id;
    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    private ArrayList<Book> books;
    private int sizeBook;

    public Author() {
        books = new ArrayList<Book>();
    }

    public Author(int id) {
        this.id = id;
        books = new ArrayList<Book>();
    }

    public static Author readFromFile(BufferedReader br) throws ClassNotFoundException, ParseException, IOException {
        Author author = new Author();
        author.setAuthor(Integer.parseInt(br.readLine()));
        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {
            author.addToIndex(i, Book.readFromFile(br));
        }
        return author;
    }

    public int getAuthor() {
        return id;
    }

    public void setAuthor(int id) {
        this.id = id;
    }

    public int getSizeBook() {
        return sizeBook;
    }

    public void setSizeBook(int sizeBook) {
        this.sizeBook = sizeBook;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public Book getBookFromIndex(int index) {
        return this.books.get(index);
    }

    public void addToIndex(int index, Book book) {
        book.setAuthorId(id);
        books.add(book);
        sizeBook = books.size();
    }

    public void deleteToIndex(int index) {
        this.books.remove(index);
        this.sizeBook = this.books.size();
    }

}
