package cl.escalab.crochicat.controller;
import cl.escalab.crochicat.dto.CommentPhotoPostDto;
import cl.escalab.crochicat.exception.ModelNotFoundException;
import cl.escalab.crochicat.model.Comment;
import cl.escalab.crochicat.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value="/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentPhotoPostDto> listComments(){
        List<Comment> comments = new ArrayList<>();
        List<CommentPhotoPostDto> listPhoto = new ArrayList<>();
        comments =commentService.getAll();
        for(Comment c : comments) {
            CommentPhotoPostDto ph = new CommentPhotoPostDto();
            ph.getComments();
            ph.getPost().getTextPost();
            ph.getPhoto().getImage();

            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CommentController.class).getAllComments());
            ph.add(linkTo.withSelfRel());
            listPhoto.add(ph);

            WebMvcLinkBuilder linkTo2 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PhotoController.class).getPhoto(ph.getPhoto().getIdPhoto()));
            ph.add(linkTo2.withSelfRel());
            listPhoto.add(ph);

            WebMvcLinkBuilder linkTo3 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PostController.class).getPost(ph.getPost().getId()));
            ph.add(linkTo3.withSelfRel());
            listPhoto.add(ph);
            }
            return listPhoto;

        }




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
    @ApiOperation(value = "obtiene un comentario especifico",
                  notes = "requiere el id de la clase",
                  response = Object.class)
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("id") UUID id){

        if(id != null){
            Comment com = commentService.get(id);
            return new ResponseEntity<Comment>(com, HttpStatus.OK);
        }else{
            throw new ModelNotFoundException("id: "+id+" no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") UUID id){
        Comment comment = getCommentById(id);
        commentService.delete(comment.getId());
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable("id") UUID id){
        Comment commentUpdated = commentService.save(commentService.save(getCommentById(id)));
        return new ResponseEntity<>(commentUpdated, HttpStatus.OK);
    }


    private Comment getCommentById(UUID id){
        Comment comment = commentService.get(id);
        return comment;
    }
}
