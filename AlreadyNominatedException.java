public class AlreadyNominatedException extends Exception{
    public AlreadyNominatedException(String name){
        super(name + "was already nominated");
    }
}
