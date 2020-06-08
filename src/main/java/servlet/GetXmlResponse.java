package servlet;

import dataSource.XmlConverter;
import entities.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class GetXmlResponse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        XmlConverter xmlConverter = new XmlConverter();
        Client client = (Client) request.getSession().getAttribute("client");
        try {
            ByteArrayOutputStream baos = xmlConverter.convertObject(client);
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
