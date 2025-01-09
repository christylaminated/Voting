# Ranked Voting Machine

The **Ranked Voting Machine** is an interactive program that allows users to nominate candidates, cast ranked votes, and calculate election winners based on different voting strategies. Designed with encapsulation and flexibility in mind, the system uses the **Strategy Pattern** to support multiple winner calculation algorithms and ensures data integrity through strict validation.

---

## Features

### 1. Ranked Voting System
- Voters rank their top three candidates in order of preference:
  - **First Choice**
  - **Second Choice**
  - **Third Choice**
- Candidates must:
  - Be nominated before voting.
  - Not appear more than once in a single vote.

### 2. Multiple Voting Strategies
- **Most First Votes Strategy**:
  - A candidate must have the most first-choice votes and receive strictly more than 50% of the total first-choice votes to win.
- **Most Agreeable Strategy**:
  - A candidate wins by having the most votes in any single category (first, second, or third).

### 3. Interactive Command-Line Interface
- Nominate new candidates.
- Cast ranked votes.
- Switch between voting strategies.
- View the current election winner or no winner if no clear decision exists.

### 4. Encapsulation and Exception Handling
- Fully encapsulates voting data to prevent unauthorized modifications.
- Handles and reports errors with the following custom exceptions:
  - **AlreadyNominatedException**: Thrown if a candidate is nominated multiple times.
  - **CandidateNotNominatedException**: Thrown if a voter selects a candidate who is not on the ballot.
  - **MoreThanOnceException**: Thrown if a voter selects the same candidate multiple times in one vote.

---

## Core Components

### 1. **ElectionData**
- Manages the voting data and logic.
- Encapsulates the following:
  - A `HashMap<String, Votes>` to track vote counts for each candidate.
  - An `I3VoteStrategy` object for determining the winner based on the current strategy.
- Key Methods:
  - `nominateCandidate(String person)`: Adds a new candidate to the ballot.
  - `submitVote(String first, String second, String third)`: Submits a ranked vote.
  - `calculateWinner()`: Calculates the winner using the current strategy.
  - `setStrategy(I3VoteStrategy strategy)`: Switches the winner calculation strategy.

### 2. **Voting Strategies**
- **MostFirstVotesStrategy**:
  - Implements `calculateWinner(HashMap<String, Votes>)`.
  - Declares a winner if a candidate has the most first votes and more than 50% of the total first-choice votes.
- **MostAgreeableStrategy**:
  - Implements `calculateWinner(HashMap<String, Votes>)`.
  - Declares a winner based on the highest single-category vote count (first, second, or third votes).

### 3. **Custom Exceptions**
- **AlreadyNominatedException**:
  - Thrown when attempting to nominate an already-nominated candidate.
  - Includes the candidate's name in the exception message.
- **CandidateNotNominatedException**:
  - Thrown when attempting to vote for a non-nominated candidate.
  - Stores the candidate's name and provides a getter.
- **MoreThanOnceException**:
  - Thrown when a voter selects the same candidate multiple times in one vote.

### 4. **VotingMachine**
- A command-line program that interacts with users to:
  - Nominate candidates.
  - Cast votes.
  - Switch winner calculation strategies.
  - Display the current winner.

---

## How It Works

### Input
1. **Nominate Candidates**:
   - Add candidates to the ballot using `nominateCandidate(String name)`.
2. **Cast Votes**:
   - Submit ranked votes with first, second, and third choices.
3. **Switch Strategies**:
   - Choose between `MostFirstVotesStrategy` and `MostAgreeableStrategy`.

### Output
- Displays the winner based on the selected strategy or indicates no clear winner if no candidate satisfies the conditions.

---

