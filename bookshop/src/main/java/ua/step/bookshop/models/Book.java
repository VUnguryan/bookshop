package ua.step.bookshop.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "Books")
@Data
public class Book{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer year;
	private Integer price;
	private Double rate;
	private Date createDate;
	private String background;

	@ManyToOne
	@JoinColumn
	private User user;
	@ManyToOne
	@JoinColumn
	private Publisher publisher;

	@ManyToMany
	private List<Genre> genreList;
	@ManyToMany
	private List<Author> authorList;


	public Book() {
	}

	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {	this.name = name;}
	public Integer getYear() {return year;}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

}