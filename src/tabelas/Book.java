package tabelas;
// Generated 22/01/2017 13:41:04 by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.*;



@Entity
@Table(name="Book", schema="sakila")
public class Book
{

	@GeneratedValue
	@Id
	private int id;
	
	@Column(name="pages")
	private Integer pages;
	
	@Column(name="title")
	private String title;

	public Book() {
	}

	public Book(int id) {
		this.id = id;
	}

	public Book(int id, Integer pages, String title) {
		this.id = id;
		this.pages = pages;
		this.title = title;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getPages() {
		return this.pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
