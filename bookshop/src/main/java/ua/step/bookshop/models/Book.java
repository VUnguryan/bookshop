package ua.step.bookshop.models;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Books")
public class Book {
	@Id
	private Integer id;
	private String name;
	private Integer author_id; // потом заменю на List<author>
	private Integer year;
	private Short genre_id; // потом заменю на List<Genre>
	private Integer publisher_id; 	
	private Integer price;
	private Double rate;
	private Integer admin_id;
	private Date create_date;
	private String background;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Short getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(Short genre_id) {
		this.genre_id = genre_id;
	}
	public Integer getPublisher_id() {
		return publisher_id;
	}
	public void setPublisher_id(Integer publisher_id) {
		this.publisher_id = publisher_id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
}
