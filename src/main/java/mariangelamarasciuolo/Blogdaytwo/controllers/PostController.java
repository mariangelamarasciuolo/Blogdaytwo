package mariangelamarasciuolo.Blogdaytwo.controllers;

import mariangelamarasciuolo.Blogdaytwo.entities.Post;
import mariangelamarasciuolo.Blogdaytwo.entities.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import services.PostService;
import services.UtenteService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping("")
    public List<Post> getPost(){
        return postService.getPost();
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Post savePost(@RequestBody Post body){
        return postService.save(body);
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable int id) {
        return postService.findById(id);
    }

    @PutMapping("/{id}")
    public Post findAndUpdateById(@PathVariable int id, @RequestBody Post body){
        return postService.findAndUpdateById(id,body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDeleteById(@PathVariable int id){
        postService.findAndDeleteById(id);
    }
}
