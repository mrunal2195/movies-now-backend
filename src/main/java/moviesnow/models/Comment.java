package moviesnow.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String imdbid;
	
	@Column(name = "poster", length = 500)
	private String comment;
	
	@ManyToOne
	private User user;
	
	
	private int flagCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovieId() {
		return imdbid;
	}

	public void setMovieId(String movieId) {
		this.imdbid = movieId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public int getFlagCount() {
		return flagCount;
	}
	public void setFlagCount(int flagCount) {
		this.flagCount = flagCount;
	}
	
	
}
