package cl.escalab.crochicat.dto;

import cl.escalab.crochicat.model.Comment;
import cl.escalab.crochicat.model.Photo;
import cl.escalab.crochicat.model.Post;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class CommentPhotoPostDto extends RepresentationModel {
    private Photo photo;
    private Post post;
    private List<Comment> comments;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
