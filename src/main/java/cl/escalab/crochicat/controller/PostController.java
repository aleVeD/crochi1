package cl.escalab.crochicat.controller;
import cl.escalab.crochicat.model.Post;
import cl.escalab.crochicat.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @ApiOperation(value = "obtener todos los posts",
            notes = "",
            response = List.class,
            responseContainer = "Post")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "post no enviado"),
            @ApiResponse(code = 404, message = "Post no encontrada"),
            @ApiResponse(code = 200, message = "posts obtenidos exitosamente")})
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){

        List<Post> lista = postService.getAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @ApiOperation(value = "Guardar un post",
            notes = "",
            response = List.class,
            responseContainer = "Post")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "post no enviado"),
            @ApiResponse(code = 404, message = "Pagina no encontrada"),
            @ApiResponse(code = 200, message = "post guardado exitosamente")})
    @PostMapping
    public ResponseEntity<Post> savePost(@Valid @RequestBody Post post){
        Post postSaved = postService.save(post);
        return new ResponseEntity<>(postSaved, HttpStatus.OK);
    }

    @ApiOperation(value = "obtener un post especifico",
            notes = "",
            response = List.class,
            responseContainer = "Post")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "post no enviado"),
            @ApiResponse(code = 404, message = "Pagina no encontrada"),
            @ApiResponse(code = 200, message = "post obtenido exitosamente")})
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") UUID id){
        Post post = getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @ApiOperation(value = "borrar un post",
            notes = "",
            response = List.class,
            responseContainer = "Post")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "post no fue eliminado"),
            @ApiResponse(code = 404, message = "No se encontró el post"),
            @ApiResponse(code = 200, message = "post borrado exitosamente")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") UUID id){
        Post postDeleted = getPostById(id);
        postService.delete(id);
        return new ResponseEntity<>(postDeleted, HttpStatus.OK);
    }

    @ApiOperation(value = "actualizar un post",
            notes = "",
            response = List.class,
            responseContainer = "Post")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "Post no actualizado"),
            @ApiResponse(code = 404, message = "Post no encontrado"),
            @ApiResponse(code = 200, message = "Post actualizado exitosamente")})
    @PutMapping("/id")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable("id") UUID id){
        Post postUpdated = postService.update(post, id);
        return new ResponseEntity<>(postUpdated, HttpStatus.OK);
    }

    private Post getPostById(UUID id){
        Post post = postService.get(id);
        return post;
    }
}
