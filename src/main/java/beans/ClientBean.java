package beans;

import dataSource.DataSource;
import dataSource.XmlConverter;
import entities.Author;
import entities.AuthorList;
import entities.Book;
import entities.Client;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean(name = "client", eager = true)
@SessionScoped
public class ClientBean {
    private DataSource dataSource = new DataSource();
    private ClientEJB clientEJB = new ClientEJB();
    private Client client = new Client();

    public String getName() {return  client.getName();}
    public void setName(String name) { this.client.setName(name);}
    public String getLogin(){
        return client.getLogin();
    }
    public void setLogin(String login){
        this.client.setLogin(login);
    }
    public String getPassword(){
        return client.getPassword();
    }
    public void setPassword(String password){
        this.client.setPassword(password);
    }
    public Client getClient(){
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public String validateUser() throws SQLException, ClassNotFoundException{
        if(clientEJB.validateUserLogin(client) != null){
            client = clientEJB.validateUserLogin(client);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("client",this);
            return "result";
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Неверные данные"));
            return "login";
        }
    }
    public String logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "login";
    }
    public Client fillClient() throws SQLException{
        Client clientFull = client;
        ArrayList<AuthorList> authorLists = (ArrayList<AuthorList>) dataSource.findAuthorListByClient(client.getId());
        ArrayList<Author> authors = new ArrayList<Author>();
        for(AuthorList authorList: authorLists){
            Author author = dataSource.findAuthorById(authorList.getAuthor_id());
            author.setBooks((ArrayList<Book>) dataSource.findBookByAuthor(authorList.getAuthor_id()));
            authors.add(author);
        }
        clientFull.setAuthors(authors);
        for (Author aut: clientFull.getAuthors()){
            for (Book bok: aut.getBooks()){
                System.out.println(bok.getId());
            }
        }
        return clientFull;
    }
    public void convert() throws JAXBException, IOException, SQLException{
        XmlConverter xmlConverter = new XmlConverter();
        ByteArrayOutputStream baos = xmlConverter.convertObject(fillClient());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("text/xml");
        response.setHeader("Content-Disposition", "attachment;filename=file.xml");
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        baos.close();
        FacesContext.getCurrentInstance().getResponseComplete();
    }
    public String moveToInsertAuthor(){
        return "insertauthor";
    }
    public String moveToInsertBook(){
        return "insertbook";
    }
}
