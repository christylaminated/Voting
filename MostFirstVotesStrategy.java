import java.util.HashMap;
import java.util.Optional;

public class MostFirstVotesStrategy implements I3VoteStrategy {

    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        int maxFirstVotes = 0;
        String currentWinner = "";
        String winner = "";
        int totalFirstVotes = 0;

        for (String candidate : votes.keySet()) {
            int candidateFirstVotes = votes.get(candidate).getFirstVotes();
            totalFirstVotes += candidateFirstVotes;

            if (candidateFirstVotes > maxFirstVotes) {
                maxFirstVotes = candidateFirstVotes;
                currentWinner = candidate;
            }
        }

        if (0.5 * totalFirstVotes < maxFirstVotes) {
            winner = currentWinner;
            return Optional.of(winner);
        } else {
            return Optional.empty();
        }
    }
}
