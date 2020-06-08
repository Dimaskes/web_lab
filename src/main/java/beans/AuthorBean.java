package beans;

import dataSource.DataSource;
import entities.Author;
import entities.AuthorList;
import entities.Client;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@ManagedBean(name = "author")
public class AuthorBean {
    private final DataSource dataSource = new DataSource();
    private Author author = new Author();

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author findAuthorById(int id) throws SQLException {
        return dataSource.findAuthorById(id);
    }
}
