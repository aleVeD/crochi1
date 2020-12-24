package cl.escalab.crochicat.controller;

import cl.escalab.crochicat.exception.ModelNotFoundException;
import cl.escalab.crochicat.model.Comment;
import cl.escalab.crochicat.model.Post;
import cl.escalab.crochicat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(){
        List<Comment> comments = commentService.getAll();
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        Comment commentSaved = commentService.save(comment);
        return new ResponseEntity<Comment>(commentSaved, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("id") UUID id){

        if(id != null){
            Comment com = commentService.get(id);
            return new ResponseEntity<Comment>(com, HttpStatus.OK);
        }else{
            throw new ModelNotFoundException("id: "+id+" no encontrado");
        }
    }
}
