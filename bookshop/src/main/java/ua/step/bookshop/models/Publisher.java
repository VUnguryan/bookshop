package ua.step.bookshop.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 
 * @author Константин
 *
 */
@Entity
@Table(name = "Publishers")
@Data
public class Publisher {
	@Id
	private Short id;
	private String name;
}