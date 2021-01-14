package cl.escalab.crochicat.controller;
import cl.escalab.crochicat.model.Post;
import cl.escalab.crochicat.service.PostService;
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

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){

        List<Post> lista = postService.getAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> savePost(@Valid @RequestBody Post post){
        Post postSaved = postService.save(post);
        return new ResponseEntity<>(postSaved, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") UUID id){
        Post post = getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") UUID id){
        Post postDeleted = getPostById(id);
        postService.delete(id);
        return new ResponseEntity<>(postDeleted, HttpStatus.OK);
    }

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
