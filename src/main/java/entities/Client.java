package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {
	
	private int id;
	private String name;
	private String login;
	@XmlTransient
	private String password;
	private int countAuthors;
	@XmlElementWrapper(name = "authors")
	@XmlElement(name = "author")
	private ArrayList<Author> authors;
	
	public Client(){
		this.authors = new ArrayList<Author>();
	}
	
	public Client(int id, String name, String login, String password){
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.authors = new ArrayList<Author>();
	}
	
	public int getId(){
		return this.id; 
	}
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String getLogin(){
		return this.login;
	}
	public void setLogin(String login){
		this.login = login;
	}
	
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
	public int getCountAuthors(){
		return this.countAuthors;
	}
	public void setCountAuthors(int countAuthors){
		this.countAuthors = countAuthors;
	}
	
	public ArrayList<Author> getAuthors(){
		return this.authors;
	}
	public void setAuthors(ArrayList<Author> authors){
		this.authors = authors;
	}
	
	public Author getAutFromIndex(int index){
		return this.authors.get(index);
	}
	public void addAuthorToIndex(int index, Author author){
		authors.add(index, author);
		countAuthors = authors.size();
	}
	public void deleteToIndex(int index){
		authors.remove(index);
		countAuthors = authors.size();
	}
	
	public static Client readFromFile(BufferedReader br) throws ClassNotFoundException, ParseException, IOException{
		Client client = new Client();
		client.setId(Integer.parseInt(br.readLine()));
		client.setName(br.readLine());
		client.setLogin(br.readLine());
		client.setPassword(br.readLine());
		int count = Integer.parseInt(br.readLine());
		for(int i = 0; i < count; i++){
			client.addAuthorToIndex(i, Author.readFromFile(br));
		}
		return client;
	}
	

}
