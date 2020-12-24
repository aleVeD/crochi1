package cl.escalab.crochicat.service.impl;

import cl.escalab.crochicat.exception.ModelNotFoundException;
import cl.escalab.crochicat.model.Post;
import cl.escalab.crochicat.repo.PostRepoInterface;
import cl.escalab.crochicat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepoInterface postRepoInterface;
    @Override
    public List<Post> getAll() {
        return postRepoInterface.findAll();
    }

    @Override
    public Post save(Post post) {
        return postRepoInterface.save(post);
    }

    @Override
    public Post get(UUID id) {
        Post post = getPostById(id);
        return post;
    }

    @Override
    public Boolean delete(UUID id) {
        Post getPost = getPostById(id);
        postRepoInterface.deleteById(getPost.getId());
        return true;
    }

    @Override
    public Post update(Post post, UUID id) {
        Post postUpdated = postRepoInterface.save(post);
        return postUpdated;
    }
    private Post getPostById(UUID id){
        Optional<Post> post = postRepoInterface.findById(id);
        if(post.isPresent()) {
            return post.get();
        }else{
            throw new ModelNotFoundException("El id "+id+" no se encuentra");
        }
    }
}
