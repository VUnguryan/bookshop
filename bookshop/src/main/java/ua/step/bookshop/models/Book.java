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
	private Integer author_id;
	private Integer year;
	private Short genre_id;
	private Integer publisher_id; 	
	private Integer price;
	private Double rate;
	private Integer admin_id;
	private Date create_date;
	private String background;
}