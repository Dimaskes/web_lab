package beans;

import dataSource.DataSource;
import entities.Client;

import javax.ejb.Stateless;
import java.sql.SQLException;

@Stateless
public class ClientEJB {
    private DataSource dataSource = new DataSource();

    public Client validateUserLogin(Client client) throws SQLException {
        if (dataSource.existLogin(client.getLogin())) {
            Client clientFull = dataSource.findClientByLogin(client.getLogin());
            if (clientFull.getPassword().equals(client.getPassword())) {
                return clientFull;
            }
        }
        return null;
    }
}
