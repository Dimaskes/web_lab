//import dataSource.DataSource;
//import entities.Client;
//import entities.Author;
//import entities.Book;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.postgresql.core.SqlCommand;
//import java.io.IOException;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ClientTest {
//
//    private Book book1, book2, book3, book4, book5, book6, book7, book8, book9, book10, book11, book12;
//    private Book book13, book14, book15, book16, book17, book18, book19, book20, book21, book22, book23, book24;
//    private Book book25, book26, book27, book28, book29, book30, book31, book32, book33, book34, book35, book36;
//    private Author author1, author2, author3, author4, author5, author6, author7;
//    private Client client1, client2, client3;
//
//    @Before
//    public void setUp() throws Exception {
//
//        client1 = new Client(1, "user1", "user1", "password");
//        client2 = new Client(2, "user2", "user2", "password");
//        client3 = new Client(3, "user3", "user3", "password");
//
//        author1 = new Author(1);
//        author2 = new Author(2);
//        author3 = new Author(3);
//        author4 = new Author(4);
//        author5 = new Author(5);
//        author6 = new Author(6);
//        author7 = new Author(7);
//
//        book1 = new Book(1, "name1", 2000, 101, 5.22);
//        book2= new Book(2, "name2", 2001, 101, 5.22);
//        book3 = new Book(3, "name3", 2002, 101, 5.22);
//        book4 = new Book(4, "name4", 2003, 101, 5.22);
//        book5 = new Book(5, "name5", 2004, 101, 5.22);
//        book6 = new Book(6, "name6", 2005, 101, 5.22);
//        book7 = new Book(7, "name7", 2006, 101, 5.22);
//        book8 = new Book(8, "name8", 2007, 101, 5.22);
//        book9 = new Book(9, "name9", 2008, 101, 5.22);
//        book10 = new Book(10, "name10", 2009, 101, 5.22);
//        book11 = new Book(11, "name11", 2010, 101, 5.22);
//        book12= new Book(12, "name12", 2011, 101, 5.22);
//        book13 = new Book(13, "name13", 2012, 101, 5.22);
//        book14 = new Book(14, "name14", 2013, 101, 5.22);
//        book15 = new Book(15, "name15", 2014, 101, 5.22);
//        book16 = new Book(16, "name16", 2015, 101, 5.22);
//        book17 = new Book(17, "name17", 2016, 101, 5.22);
//        book18 = new Book(18, "name18", 2017, 101, 5.22);
//        book19 = new Book(19, "name19", 2018, 101, 5.22);
//        book20 = new Book(20, "name20", 2019, 101, 5.22);
//        book21 = new Book(21, "name21", 2020, 101, 5.22);
//        book22= new Book(22, "name22", 2021, 101, 5.22);
//        book23 = new Book(23, "name23", 2022, 101, 5.22);
//        book24 = new Book(24, "name24", 2023, 101, 5.22);
//        book25 = new Book(25, "name25", 2024, 101, 5.22);
//        book26 = new Book(26, "name26", 2025, 101, 5.22);
//        book27 = new Book(27, "name27", 2026, 101, 5.22);
//        book28 = new Book(28, "name28", 2027, 101, 5.22);
//        book29 = new Book(29, "name29", 2028, 101, 5.22);
//        book30 = new Book(30, "name30", 2029, 101, 5.22);
//        book31 = new Book(31, "name31", 2030, 101, 5.22);
//        book32= new Book(32, "name32", 2031, 101, 5.22);
//        book33 = new Book(33, "name33", 2032, 101, 5.22);
//        book34 = new Book(34, "name34", 2033, 101, 5.22);
//        book35 = new Book(35, "name35", 2034, 101, 5.22);
//
//        author1.addToIndex(0, book1);
//        author1.addToIndex(1, book2);
//        author1.addToIndex(2, book3);
//        author1.addToIndex(3, book4);
//        author1.addToIndex(4, book5);
//
//        author2.addToIndex(0, book6);
//        author2.addToIndex(1, book7);
//        author2.addToIndex(2, book8);
//        author2.addToIndex(3, book9);
//        author2.addToIndex(4, book10);
//
//        author3.addToIndex(0, book11);
//        author3.addToIndex(1, book12);
//        author3.addToIndex(2, book13);
//        author3.addToIndex(3, book14);
//        author3.addToIndex(4, book15);
//
//        author4.addToIndex(0, book16);
//        author4.addToIndex(1, book17);
//        author4.addToIndex(2, book18);
//        author4.addToIndex(3, book19);
//        author4.addToIndex(4, book20);
//
//        author5.addToIndex(0, book21);
//        author5.addToIndex(1, book22);
//        author5.addToIndex(2, book23);
//        author5.addToIndex(3, book24);
//        author5.addToIndex(4, book25);
//
//        author6.addToIndex(0, book26);
//        author6.addToIndex(1, book27);
//        author6.addToIndex(2, book28);
//        author6.addToIndex(3, book29);
//        author6.addToIndex(4, book30);
//
//        author7.addToIndex(0, book31);
//        author7.addToIndex(1, book32);
//        author7.addToIndex(2, book33);
//        author7.addToIndex(3, book34);
//        author7.addToIndex(4, book35);
//
//        client1.addAuthorToIndex(0, author1);
//        client1.addAuthorToIndex(1, author2);
//        client1.addAuthorToIndex(2, author3);
//        client1.addAuthorToIndex(3, author4);
//        client1.addAuthorToIndex(4, author5);
//
//        client2.addAuthorToIndex(0, author1);
//        client2.addAuthorToIndex(1, author6);
//        client2.addAuthorToIndex(2, author3);
//        client2.addAuthorToIndex(3, author2);
//        client2.addAuthorToIndex(4, author5);
//
//        client3.addAuthorToIndex(0, author6);
//        client3.addAuthorToIndex(1, author2);
//        client3.addAuthorToIndex(2, author5);
//        client3.addAuthorToIndex(3, author4);
//        client3.addAuthorToIndex(4, author7);
//    }
//    @Test
//    public void addClients() throws SQLException, ClassNotFoundException {
//        DataSource.insertClient(client1);
//        DataSource.insertClient(client2);
//        DataSource.insertClient(client3);
//    }
//
//
//
//}
