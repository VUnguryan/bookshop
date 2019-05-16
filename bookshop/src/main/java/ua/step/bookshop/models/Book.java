package ua.step.bookshop.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Books")
@Data
public class Book {
	@Id
    @GeneratedValue
	private Integer id;
	private String name;
	private Integer authorId; // потом заменю на List<author> authors
	private Integer year;
	private Short genreId; // потом заменю на List<Genre>
	private Integer publisherId; 	// заменить на Publisher publisher
	private Integer price;
	private Double rate;
	private Integer adminId; // заменить на Admin admin
	private Date createDate;
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
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Short getGenreId() {
		return genreId;
	}
	public void setGenreId(Short genreId) {
		this.genreId = genreId;
	}
	public Integer getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
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
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public Date getCreateIate() {
		return getCreateIate();
	}
	public void setCreateIate(Date createIate) {
		this.createDate = createIate;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
}