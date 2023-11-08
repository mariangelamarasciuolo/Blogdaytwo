package services;

import mariangelamarasciuolo.Blogdaytwo.entities.Utente;
import mariangelamarasciuolo.Blogdaytwo.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@Service
public class UtenteService {
    private List<Utente> utente =new ArrayList<>();
    public Utente save(Utente body){
        Random rndm = new Random();
        body.setId(rndm.nextInt(1, 1000));
        body.setAvatar("https://ui-avatars.com/api/?name="+body.getName()+"+"+body.getSurname());
        this.utente.add(body);
        return body;
    }
    public List<Utente> getUtente() {
        return this.utente;
    }

    public Utente findById(int id) {
        Utente u = null;
        for (Utente utente: this.utente){
            if(utente.getId() == id ){
                u = utente;
            }
        }
        if(u == null ){
            throw new NotFoundException(id);
        } else {
            return u;
        }
    }

    public Utente findAndUpdateById(int id, Utente body){
        Utente found = null;
        for (Utente utente: this.utente) {
            if (utente.getId() == id){
                found = utente;
                found.setId(id);
                found.setName(body.getName());
                found.setSurname(body.getSurname());
            }
        }
        if (found == null) {
            throw new NotFoundException(id);
        } else{
            return found;
        }
    }

    public void findAndDeleteById (int id){
        ListIterator<Utente> iterator = this.utente.listIterator();
        while(iterator.hasNext()){
            Utente corrente = iterator.next();
            if (corrente.getId() == id){
                iterator.remove();
            }
        }
    }

}

