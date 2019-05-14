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
	private Integer authorId;
	private Integer year;
	private Short genreId;
	private Integer publisherId; 	
	private Integer price;
	private Double rate;
	private Integer adminId;
	private Date createIate;
	private String background;
}