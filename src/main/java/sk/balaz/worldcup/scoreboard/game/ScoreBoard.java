package sk.balaz.worldcup.scoreboard.game;

import sk.balaz.worldcup.scoreboard.exception.ScoreBoardException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ScoreBoard {

    private final List<Match> footballMatches = new ArrayList<>();


    public void insertMatch(Team homeTeam, Team awayTeam) {
        FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam);
        if(footballMatches.contains(footballMatch)) {
            throw new IllegalArgumentException(
                    "It is not possible to have two same matches in same time."
            );
        }

        footballMatches.add(new FootballMatch(homeTeam, awayTeam));
    }

    public List<Match> getMatches() {
        return footballMatches;
    }

    public void removeMatch() {
        Optional<Match> first = footballMatches.stream()
                .filter(Match::isActive)
                .findFirst();

        if(first.isPresent()) {
            Match footballMatch = first.get();
            footballMatches.remove(footballMatch);
            footballMatch.setActive(false);
        }
    }

    public void updateMatch(Team homeTeam, Team awayTeam) {
        FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam);
        Optional<Match> optionalFoundMatch = footballMatches.stream()
                .filter(m -> m.getHomeTeam().getName().equals(footballMatch.getHomeTeam().getName()))
                .filter(m -> m.getAwayTeam().getName().equals(footballMatch.getAwayTeam().getName()))
                .findFirst();
        if (optionalFoundMatch.isEmpty()) {
            throw new ScoreBoardException(
                    "It is not possible to update match which is not active.");
        }
        Match foundFootballMatch = optionalFoundMatch.get();
        Team ht = foundFootballMatch.getHomeTeam();
        Team at = foundFootballMatch.getAwayTeam();

        ht.setScore(footballMatch.getHomeTeam().getScore());
        at.setScore(footballMatch.getAwayTeam().getScore());
     }

    public List<Match> getOrderedMatches() {
        footballMatches.sort(
                Comparator.comparing(Match::getScore)
                        .thenComparing(Match::getStartTime)
                        .reversed()
        );

        return footballMatches;
    }
}
