package cl.escalab.crochicat.service.impl;

import cl.escalab.crochicat.exception.ModelNotFoundException;
import cl.escalab.crochicat.model.Comment;
import cl.escalab.crochicat.repo.CommentRepoInterface;
import cl.escalab.crochicat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepoInterface commentRepoInterface;

    @Override
    public List<Comment> getAll() {
        return commentRepoInterface.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepoInterface.save(comment);
    }

    @Override
    public Comment get(UUID id) {
        Comment comment = getCommentById(id);
        return comment;
    }

    @Override
    public Boolean delete(UUID id) {
        Comment comment = getCommentById(id);
        commentRepoInterface.deleteById(comment.getId());
        return true;
    }

    @Override
    public Comment update(Comment comment, UUID id) {
        Comment commentUpdated = commentRepoInterface.save(comment);
        return commentUpdated;
    }

    private Comment getCommentById(UUID id){
        Optional<Comment> comment = commentRepoInterface.findById(id);
        if(comment.isPresent()) {
            return comment.get();
        }else{
            throw new ModelNotFoundException("El id "+id+" no se encuentra");
        }
    }

}
