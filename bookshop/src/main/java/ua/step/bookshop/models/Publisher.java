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
	public Short getId() {
		return id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}