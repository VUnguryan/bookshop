package ua.step.bookshop.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Genres")
@Data
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short id;
	private String name;
}