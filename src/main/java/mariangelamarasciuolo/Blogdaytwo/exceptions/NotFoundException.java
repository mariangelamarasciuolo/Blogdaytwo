package mariangelamarasciuolo.Blogdaytwo.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id) {
        super("Elemento con id " + id +" non trovato");
    }
}
