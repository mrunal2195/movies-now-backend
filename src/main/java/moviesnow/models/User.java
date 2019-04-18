package moviesnow.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private boolean isflagged = false;
	private String role;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, orphanRemoval=true)
	@JsonIgnore
	@Fetch(FetchMode.SELECT)
	private List<Comment> comments = new ArrayList();
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, orphanRemoval=true)
	@JsonIgnore
	@Fetch(FetchMode.SELECT)
	private List<FavouriteMovie> movies = new ArrayList<>();
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<FavouriteMovie> getMovies() {
		return movies;
	}
	public void setMovies(List<FavouriteMovie> movies) {
		this.movies = movies;
	}
	
	public boolean isIsflagged() {
		return isflagged;
	}
	public void setIsflagged(boolean isflagged) {
		this.isflagged = isflagged;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
