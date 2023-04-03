package es.codeurjc;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import es.codeurjc.Post;
import es.codeurjc.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService posts;

    @GetMapping("/")
    public Collection<Post> getPosts() {
        return posts.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable long id) {
        Post post = posts.findById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        posts.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).body(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable long id) {
        Post post = posts.findById(id);
        if (post != null) {
            posts.deleteById(id);
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> replacePost(@PathVariable long id, @RequestBody Post newPost) {
        Post post = posts.findById(id);
        if (post != null) {
            newPost.setId(id);
            posts.save(newPost);
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}