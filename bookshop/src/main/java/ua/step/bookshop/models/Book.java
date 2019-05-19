package ua.step.bookshop.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Books")
@Data
public class Book {
	@Id
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
}