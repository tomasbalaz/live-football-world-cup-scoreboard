package sk.balaz.worldcup.scoreboard.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreBoard {

    private final List<Match> matches = new ArrayList<>();


    public void insertMatch(Team homeTeam, Team awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        if(matches.contains(match)) {
            throw new IllegalArgumentException(
                    "It is not possible to have two same matches in same time "
            );
        }

        matches.add(new Match(homeTeam, awayTeam));
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void removeMatch() {
        Optional<Match> first = matches.stream()
                .filter(Match::isActive)
                .findFirst();

        if(first.isPresent()) {
            Match match = first.get();
            matches.remove(match);
            match.setActive(false);
        }
    }
}
