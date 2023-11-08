package services;

import mariangelamarasciuolo.Blogdaytwo.entities.Post;
import mariangelamarasciuolo.Blogdaytwo.entities.Utente;
import mariangelamarasciuolo.Blogdaytwo.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@Service
public class PostService {
    private List<Post> post =new ArrayList<>();
    public Post save(Post body){
        Random rndm = new Random();
        body.setId(rndm.nextInt(1, 1000));
        body.setCover("https://picsum.photos/200/300");
        this.post.add(body);
        return body;
    }
    public List<Post> getPost() {
        return this.post;
    }

    public Post findById(int id) {
        Post p = null;
        for (Post post: this.post){
            if(post.getId() == id ){
                p = post;
            }
        }
        if(p == null ){
            throw new NotFoundException(id);
        } else {
            return p;
        }
    }

    public Post findAndUpdateById(int id, Post body){
        Post foundP = null;
        for (Post post: this.post) {
            if (post.getId() == id){
                foundP = post;
                foundP.setId(id);
                foundP.setCategoria(body.getCategoria());
                foundP.setTitolo(body.getTitolo());
                foundP.setTempoDiLettura(body.getTempoDiLettura());
                foundP.setContenuto(body.getContenuto());

            }
        }
        if (foundP == null) {
            throw new NotFoundException(id);
        } else{
            return foundP;
        }
    }

    public void findAndDeleteById (int id){
        ListIterator<Post> iterator = this.post.listIterator();
        while(iterator.hasNext()){
            Post corrente = iterator.next();
            if (corrente.getId() == id){
                iterator.remove();
            }
        }
    }
}
