package beans;

import dataSource.DataSource;
import entities.AuthorList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean(name = "authorList")
public class AuthorListBean {
    private final DataSource dataSource = new DataSource();
    private AuthorList authorList = new AuthorList();

    public AuthorList getAuthorList() {
        return authorList;
    }

    public void setAuthorList(AuthorList authorList) {
        this.authorList = authorList;
    }

    public ArrayList<AuthorList> findByClientId() throws SQLException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        ClientBean clientBean = (ClientBean) session.getAttribute("client");
        return (ArrayList<AuthorList>) dataSource.findAuthorListByClient(clientBean.getClient().getId());
    }

    public String insertAuthorList() throws SQLException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        ClientBean clientBean = (ClientBean) session.getAttribute("client");
        if (dataSource.existAuthorId(authorList.getAuthor_id())){
            authorList.setAuthor_id(authorList.getAuthor_id());
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Такого автора не существует"));
            return "insertauthor";
        }
        authorList.setClient_id(clientBean.getClient().getId());
        int countAuthors = clientBean.getClient().getCountAuthors();
        dataSource.updateCountAuthors(clientBean.getClient().getId(),++countAuthors);
        dataSource.insertAuthorList(this.authorList);
        return "result";
    }
}
