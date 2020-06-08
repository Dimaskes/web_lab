package entities;

import lombok.Data;
import lombok.Getter;

@Data
public class AuthorList {
    private int author_id;
    private int client_id;

    public int getAuthor_id() {
        return author_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
}
