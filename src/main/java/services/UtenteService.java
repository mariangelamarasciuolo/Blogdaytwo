package services;

import mariangelamarasciuolo.Blogdaytwo.entities.Utente;
import mariangelamarasciuolo.Blogdaytwo.exceptions.BadRequestException;
import mariangelamarasciuolo.Blogdaytwo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.UtenteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    public Utente save(Utente body){
        utenteRepository.findByEmail(body.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });

        body.setAvatar("http://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());

        return utenteRepository.save(body);
    }
    public Page<Utente> getUtente(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return utenteRepository.findAll(pageable);
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

