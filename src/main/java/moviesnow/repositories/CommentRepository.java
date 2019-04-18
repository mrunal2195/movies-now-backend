package moviesnow.repositories;

import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import moviesnow.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
	
	@Query("Select comment from Comment comment where comment.imdbid=:imdbid")
	public List<Comment> findCommentsOfMovie(@Param("imdbid") String imdbid);
	
	@Query("select comment from Comment comment where comment.flagCount>0")
	public List<Comment> findFlaggedComments();
}
