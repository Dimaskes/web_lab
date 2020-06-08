package entities;

import java.io.*;
import java.text.ParseException;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "book")
public class Book implements Serializable,  Comparable<Book>  {
	
	private int id;
    private String name;
    private int year;
    private int countPage;
    private double price;
    private int authorId;
    
    public Book(){
    	
    }
    
    public Book(int id, String name, int year, int countPage, double price) {
    	this.id = id;
        this.name = name;
        this.year = year;
        this.countPage = countPage;
        this.price = price;
        this.setAuthorId(authorId);
    }
    
    public String getName(){
    	return name;
    }
    public void setName(String name){
    	this.name = name;
    }

    public int getYear(){
    	return year;
    }
    public void setYear(int year){
    	this.year = year;
    }
    
    public int getCountPage(){
    	return countPage;
    }
    public void setCountPage(int countPage) throws IllegalArgumentException{
    	if(countPage <= 0)
    		throw new IllegalArgumentException("Incorrect count pages");
    	else
    		this.countPage = countPage;
    }
    
    public double getPrice(){
    	return price;
    }
    public void setPrice(double price){
    	this.price = price;
    }
    
    public int getId(){
    	return id;
    }
    public void setId(int id){
    	this.id = id;
    }
    
    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorId) {
    	this.authorId = authorId;
    }

    
    public static void writeToFile(Book book, PrintWriter wr){
    	wr.println(book.id);
    	wr.println(book.name);
    	wr.println(book.year);
    	wr.println(book.countPage);
    	wr.println(book.price);
    	wr.println(book.authorId);
    }
    
    public static Book readFromFile(BufferedReader br) throws IOException, ClassNotFoundException, ParseException{
    	Book book = new Book();
    	int id = Integer.parseInt(br.readLine());
    	System.out.println("book id: " + id);
    	String name = br.readLine();
    	int year = Integer.parseInt(br.readLine());
    	int countPage = Integer.parseInt(br.readLine());
    	double price = Double.parseDouble(br.readLine());
    	int authorId = Integer.parseInt(br.readLine());
    	book.setId(id);
    	book.setName(name);
    	book.setYear(year);
    	book.setCountPage(countPage);
    	book.setPrice(price);
    	book.setAuthorId(authorId);
    	return book;
    	
    } 
    
    public int compareTo(Book book) {
    	  return this.name.compareTo(book.getName());
    }
    
}
