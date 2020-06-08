package beans;

import dataSource.DataSource;
import entities.Book;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean(name = "book")
@SessionScoped
public class BookBean {
    private Book book = new Book();
    private DataSource dataSource = new DataSource();

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ArrayList<Book> findBookByAuthor(int authorId) throws SQLException {
        return (ArrayList<Book>) dataSource.findBookByAuthor(authorId);
    }

    public String insertBook() throws SQLException {
        book.setName(book.getName());
        book.setCountPage(book.getCountPage());
        book.setYear(book.getYear());
        book.setPrice(book.getPrice());
        if (dataSource.existAuthorId(book.getAuthorId())){
            book.setAuthorId(book.getAuthorId());
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Такого автора не существует"));
            return "insertbook";
        }
        dataSource.insertBook(this.book);
        int sizeBook = dataSource.findAuthorById(book.getAuthorId()).getSizeBook();
        dataSource.updateAuthorSizeBook(book.getAuthorId(), ++sizeBook);
        return "result";
    }

}
