package servlet;

import dataSource.DataSource;
import entities.Author;
import entities.Book;
import entities.Client;
import entities.User;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetUserInfo extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        User user = (User)request.getSession().getAttribute("user");
        try{
            Client client = DataSource.findClientByUsername(user.getLogin());
            ArrayList<Author> authors = (ArrayList<Author>) DataSource.findAuthorByClient(client);
            for (int i=0; i<authors.size(); i++){
                ArrayList<Book> books = (ArrayList<Book>) DataSource.findBooksByAuthor(authors.get(i));
                for (int j=0; j<books.size(); j++){
                    authors.get(i).addToIndex(j, books.get(j));
                }
                client.addAuthorToIndex(i, authors.get(i));
            }
            request.getSession().setAttribute("client", client);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
