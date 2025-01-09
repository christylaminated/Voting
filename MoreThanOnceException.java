public class MoreThanOnceException extends Exception{
    public MoreThanOnceException(String name){
        super(name + "given more than once");
    }
}
