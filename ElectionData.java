 import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class ElectionData {
    private I3VoteStrategy currentStrategy;
    private HashMap<String, Votes> candidateVotesMap;

    public ElectionData(I3VoteStrategy initialStrategy) {
        this.currentStrategy = initialStrategy;
        this.candidateVotesMap = new HashMap<>();
    }

    public void setStrategy(I3VoteStrategy newStrategy) {
        this.currentStrategy = newStrategy;
    }

    public Set<String> getCandidates() {
        HashMap<String, Votes> copyCandidateVotesMap = new HashMap<>();
        for (String candidate : candidateVotesMap.keySet()) {
            copyCandidateVotesMap.put(candidate, new Votes(candidateVotesMap.get(candidate)));
        }
        return copyCandidateVotesMap.keySet();
    }

    public void nominateCandidate(String candidate) throws AlreadyNominatedException {
        if (candidateVotesMap.containsKey(candidate)) {
            throw new AlreadyNominatedException(candidate);
        } else {
            candidateVotesMap.put(candidate, new Votes(0, 0, 0));
        }
    }

    public void submitVote(String first, String second, String third) throws CandidateNotNominatedException, MoreThanOnceException {
        if (!candidateVotesMap.containsKey(first)) {
            throw new CandidateNotNominatedException(first);
        }
        if (!candidateVotesMap.containsKey(second)) {
            throw new CandidateNotNominatedException(second);
        }
        if (!candidateVotesMap.containsKey(third)) {
            throw new CandidateNotNominatedException(third);
        }
        if (first.equals(second) || first.equals(third)) {
            throw new MoreThanOnceException(first);
        }
        if (second.equals(third)) {
            throw new MoreThanOnceException(second);
        } else {
            candidateVotesMap.put(first, new Votes(candidateVotesMap.get(first).getFirstVotes() + 1,
                    candidateVotesMap.get(first).getSecondVotes() + 0, candidateVotesMap.get(first).getThirdVotes() + 0));
            candidateVotesMap.put(second, new Votes(candidateVotesMap.get(second).getFirstVotes() + 0,
                    candidateVotesMap.get(second).getSecondVotes() + 1, candidateVotesMap.get(second).getThirdVotes() + 0));
            candidateVotesMap.put(third, new Votes(candidateVotesMap.get(third).getFirstVotes() + 0,
                    candidateVotesMap.get(third).getSecondVotes() + 0, candidateVotesMap.get(third).getThirdVotes() + 1));

        }
    }

    public Optional<String> calculateWinner() {
        HashMap<String, Votes> copyCandidateVotesMap = new HashMap<>();
        for (String candidate : candidateVotesMap.keySet()) {
            copyCandidateVotesMap.put(candidate, new Votes(candidateVotesMap.get(candidate)));
        }
        return currentStrategy.calculateWinner(copyCandidateVotesMap);
    }
}
