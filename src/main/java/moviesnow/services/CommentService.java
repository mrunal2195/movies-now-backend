package moviesnow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import moviesnow.models.Comment;
import moviesnow.models.User;
import moviesnow.repositories.CommentRepository;
import moviesnow.repositories.UserRepository;

@RestController
public class CommentService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	@PostMapping("/api/user/{userId}/comment")
	public Comment postComment(@PathVariable("userId") int id, @RequestBody Comment comment) {
		User user = userRepository.findById(id).get();
		comment.setUser(user);
		commentRepository.save(comment);
		return comment;
	}
	
	@GetMapping("/api/movie/{imdbid}/comment")
	public List<Comment> getCommentsForMovie(@PathVariable("imdbid") String imdbid){
		List<Comment> comments = commentRepository.findCommentsOfMovie(imdbid);
		return comments;
	}
	
	@GetMapping("/api/comment/flagged")
	public List<Comment> getFlaggedComments(){
		List<Comment> comments = commentRepository.findFlaggedComments();
		return comments;
	}
	
	
	@PutMapping("/api/comment/{commentid}")
	public Comment flagComment(@PathVariable("commentid") int id, @RequestBody Comment comment) {
		Comment newComment = commentRepository.findById(id).get();
		newComment.setFlagCount(comment.getFlagCount());
		commentRepository.save(newComment);
		return newComment;	
	}
	
	@PutMapping("/api/comment/{commentid}/{action}")
	public Comment manageComment(@PathVariable("commentid") int commentId, @PathVariable("action") String action) {
		Comment comment = commentRepository.findById(commentId).get();
		if (action.equals("UNFLAG")) {
			comment.setFlagCount(0);
			commentRepository.save(comment);
		}else if(action.equals("DELETE")) {
			commentRepository.deleteById(commentId);
		}	
		return comment;
	}
	
	
	
}
