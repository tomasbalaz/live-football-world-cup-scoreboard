package sk.balaz.worldcup.scoreboard.game;

import sk.balaz.worldcup.scoreboard.exception.ScoreBoardException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ScoreBoard {

    private final List<Match> matches = new ArrayList<>();


    public void insertMatch(Team homeTeam, Team awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        if(matches.contains(match)) {
            throw new IllegalArgumentException(
                    "It is not possible to have two same matches in same time."
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

    public void updateMatch(Team homeTeam, Team awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        Optional<Match> optionalFoundMatch = matches.stream()
                .filter(m -> m.getHomeTeam().getName().equals(match.getHomeTeam().getName()))
                .filter(m -> m.getAwayTeam().getName().equals(match.getAwayTeam().getName()))
                .findFirst();
        if (optionalFoundMatch.isEmpty()) {
            throw new ScoreBoardException(
                    "It is not possible to update match which is not active.");
        }
        Match foundMatch = optionalFoundMatch.get();
        Team ht = foundMatch.getHomeTeam();
        Team at = foundMatch.getAwayTeam();

        ht.setScore(match.getHomeTeam().getScore());
        at.setScore(match.getAwayTeam().getScore());
     }

    public List<Match> getOrderedMatches() {
        matches.sort(
                Comparator.comparing(Match::getScore)
                        .thenComparing(Match::getStartTime)
                        .reversed()
        );

        return matches;
    }
}
