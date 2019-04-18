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
	
	
	private int flagCount=0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImdbid() {
		return imdbid;
	}

	public void setImdbid(String imdbid) {
		this.imdbid = imdbid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		if(!this.user.getComments().contains(this)) {
			this.user.getComments().add(this);
		}
	}
	public int getFlagCount() {
		return flagCount;
	}
	public void setFlagCount(int flagCount) {
		this.flagCount = flagCount;
	}
}
