package cl.escalab.crochicat.controller;
import cl.escalab.crochicat.dto.CommentPhotoPostDto;
import cl.escalab.crochicat.exception.ModelNotFoundException;
import cl.escalab.crochicat.model.Comment;
import cl.escalab.crochicat.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "obtener todos los comentarios",
            notes = "",
            response = List.class,
            responseContainer = "Comments")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "comentario no enviado"),
            @ApiResponse(code = 404, message = "Pagina no encontrada"),
            @ApiResponse(code = 200, message = "Comentarios obtenidos exitosamente")})

    @GetMapping(value="/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentPhotoPostDto> listComments(){
        List<Comment> comments = new ArrayList<>();
        List<CommentPhotoPostDto> listPhoto = new ArrayList<>();
        comments =commentService.getAll();
        for(Comment c : comments) {
            CommentPhotoPostDto ph = new CommentPhotoPostDto();
            ph.getComments();
            ph.getPost().getTextPost();
            ph.getPhoto().getIdPhoto();

            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CommentController.class).getAllComments());
            ph.add(linkTo.withSelfRel());
            listPhoto.add(ph);

            WebMvcLinkBuilder linkTo2 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PhotoController.class).getAllPhotos());
            ph.add(linkTo2.withSelfRel());
            listPhoto.add(ph);

            WebMvcLinkBuilder linkTo3 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PostController.class).getPost(ph.getPost().getId()));
            ph.add(linkTo3.withSelfRel());
            listPhoto.add(ph);
            }
            return listPhoto;

        }


    @ApiOperation(value = "obtener todos los comentarios",
                  notes = "",
                  response = List.class,
                  responseContainer = "Comments")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "comentario no enviado"),
                           @ApiResponse(code = 404, message = "Pagina no encontrada"),
                           @ApiResponse(code = 200, message = "Comentarios obtenidos exitosamente")})
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(){
        List<Comment> comments = commentService.getAll();
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }

    @ApiOperation(value = "guardar un comentario",
            notes = "",
            response = List.class,
            responseContainer = "Comments")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "comentario no se pudo guardar"),
            @ApiResponse(code = 404, message = "Pagina no encontrada"),
            @ApiResponse(code = 200, message = "Comentario guardado exitosamente")})
    @PostMapping
    public ResponseEntity<Comment> saveComment(@Valid @RequestBody Comment comment){
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
    @ApiOperation(value = "eliminar un comentario",
            notes = "",
            response = List.class,
            responseContainer = "Comments")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "comentario no enviado"),
            @ApiResponse(code = 404, message = "Comentario no encontrada"),
            @ApiResponse(code = 200, message = "Comentario eliminado exitosamente")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") UUID id){
        Comment comment = getCommentById(id);
        commentService.delete(comment.getId());
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
    @ApiOperation(value = "actualizar un comentario",
            notes = "",
            response = List.class,
            responseContainer = "Comments")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "comentario no enviado"),
            @ApiResponse(code = 404, message = "Comentario no encontrada"),
            @ApiResponse(code = 200, message = "Comentario actualizado exitosamente")})
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
