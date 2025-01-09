import java.util.HashMap;
import java.util.Optional;

public class MostAgreeableStrategy implements I3VoteStrategy {

    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        int maxFirstVotes = 0;
        String firstVotesWinner = "";
        int maxSecondVotes = 0;
        String secondVotesWinner = "";
        int maxThirdVotes = 0;
        String thirdVotesWinner = "";
        String winner = "";

        for (String candidate : votes.keySet()) {
            if (maxFirstVotes < votes.get(candidate).getFirstVotes()) {
                maxFirstVotes = votes.get(candidate).getFirstVotes();
                firstVotesWinner = candidate;
            }
            if (maxSecondVotes < votes.get(candidate).getSecondVotes()) {
                maxSecondVotes = votes.get(candidate).getSecondVotes();
                secondVotesWinner = candidate;
            }
            if (maxThirdVotes < votes.get(candidate).getThirdVotes()) {
                maxThirdVotes = votes.get(candidate).getThirdVotes();
                thirdVotesWinner = candidate;
            }
        }

        if (maxFirstVotes > maxSecondVotes && maxFirstVotes > maxThirdVotes) {
            winner = firstVotesWinner;
            return Optional.of(winner);
        } else if (maxSecondVotes > maxFirstVotes && maxSecondVotes > maxThirdVotes) {
            winner = secondVotesWinner;
            return Optional.of(winner);
        } else if (maxThirdVotes > maxFirstVotes && maxThirdVotes > maxSecondVotes) {
            winner = thirdVotesWinner;
            return Optional.of(winner);
        } else {
            return Optional.empty();
        }
    }
}
