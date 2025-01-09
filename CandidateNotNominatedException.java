public class CandidateNotNominatedException extends Exception{
    private String candidate;
    public CandidateNotNominatedException(String name){
        super(name + "not nominated");
        this.candidate = name;
    }

    public String getCandidate() {
        return candidate;
    }
}
