package dataSource;

import entities.Author;
import entities.AuthorList;
import entities.Book;
import entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String login = "postgres";
    private static final String password = "123";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }

    public static List<Author> findAuthorByClient(Client client) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from author where id in (select author_id from authorList where client_id = ?)");
        statement.setInt(1, client.getId());
        ResultSet rs = statement.executeQuery();
        ArrayList result = new ArrayList();
        while (rs.next()) {
            Author author = new Author();
            author.setAuthor(rs.getInt("id"));
            author.setSizeBook(rs.getInt("size_book"));
            result.add(author);
        }
        connection.close();
        return result;
    }

    public static List<Book> findBooksByAuthor(Author author) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from book where author_id = ?");
        statement.setInt(1, author.getAuthor());
        ResultSet rs = statement.executeQuery();
        ArrayList result = new ArrayList();
        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setYear(rs.getInt("year"));
            book.setCountPage(rs.getInt("count_page"));
            book.setPrice(rs.getDouble("price"));
            book.setAuthorId(rs.getInt("author_id"));
            result.add(book);
        }
        connection.close();
        return result;
    }

    public static Client findClientByUsername(String login) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from client where login = ?");
        statement.setString(1, login);
        ResultSet rs = statement.executeQuery();
        Client client = new Client();
        if (rs.next()) {
            client.setId(rs.getInt("id"));
            client.setName(rs.getString("username"));
            client.setLogin(rs.getString("login"));
            client.setPassword(rs.getString("password"));
            client.setCountAuthors(rs.getInt("count_authors"));
        }
        return client;
    }

    public Client findClientByLogin(String login) throws SQLException {
        Client client = null;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from client where login = ?");
        statement.setString(1, login);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            client = new Client();
            client.setId(rs.getInt("id"));
            client.setLogin(rs.getString("login"));
            client.setPassword(rs.getString("password"));
        }
        connection.close();
        return client;
    }

    public boolean existLogin(String login) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select exists (select * from client where login = ?)");
        statement.setString(1, login);
        ResultSet rs = statement.executeQuery();
        rs.next();
        boolean exist = rs.getBoolean(1);
        connection.close();
        return exist;
    }

    public boolean existAuthorId(int author_id) throws SQLException{
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select exists(select id from author where id = ?)");
        statement.setInt(1, author_id);
        ResultSet rs = statement.executeQuery();
        rs.next();
        connection.close();
        return rs.getBoolean(1);
    }

    public Author findAuthorById(int id) throws SQLException {
        Author author = null;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from author where id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            author = new Author();
            author.setAuthor(rs.getInt("id"));
            author.setSizeBook(rs.getInt("size_book"));
        }
        connection.close();
        return author;
    }

    public void updateAuthorSizeBook(int id, int size_book) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("update author set size_book = ? where id = ? ");
        statement.setInt(1, size_book);
        statement.setInt(2, id);
        statement.execute();
        connection.close();
    }

    public List<AuthorList> findAuthorListByClient(int client_id) throws SQLException {
        List<AuthorList> result = new ArrayList<AuthorList>();
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from authorList where client_id = ?");
        statement.setInt(1, client_id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            AuthorList authorList = new AuthorList();
            authorList.setAuthor_id(rs.getInt("author_id"));
            authorList.setClient_id(rs.getInt("client_id"));
            result.add(authorList);
        }
        connection.close();
        return result;
    }

    public void insertAuthorList(AuthorList authorList) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into authorlist(author_id,client_id) values (?,?)");
        statement.setInt(1, authorList.getAuthor_id());
        statement.setInt(2, authorList.getClient_id());
        statement.execute();
        connection.close();
    }

    public List<Book> findBookByAuthor(int author_id) throws SQLException {
        ArrayList<Book> books = new ArrayList<Book>();
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from book where author_id = ?");
        statement.setInt(1, author_id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setYear(rs.getInt("year"));
            book.setCountPage(rs.getInt("count_page"));
            book.setAuthorId(rs.getInt("author_id"));
            book.setPrice(rs.getDouble("price"));
            books.add(book);
        }
        connection.close();
        return books;
    }

    public void insertBook(Book book) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into book(name, year, count_page, author_id, price)" +
                "values (?,?,?,?,?)");
        statement.setString(1, book.getName());
        statement.setInt(2, book.getYear());
        statement.setInt(3, book.getCountPage());
        statement.setInt(4, book.getAuthorId());
        statement.setDouble(5, book.getPrice());
        statement.execute();
        connection.close();
    }

    public void updateCountAuthors(int id, int count_authors) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("update client set count_authors = ? where id = ?");
        statement.setInt(1, count_authors);
        statement.setInt(2, id);
        statement.execute();
        connection.close();
    }

}