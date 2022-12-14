package sk.balaz.worldcup.scoreboard.game;

import sk.balaz.worldcup.scoreboard.exception.ScoreBoardException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class WorldCupScoreBoard implements ScoreBoard {

    private final List<Match> footballMatches;

    public WorldCupScoreBoard(List<Match> footballMatches) {
        this.footballMatches = footballMatches;
    }


    @Override
    public void insertMatch(Team homeTeam, Team awayTeam) {
        FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam);
        if(footballMatches.contains(footballMatch)) {
            throw new ScoreBoardException(
                    "It is not possible to have two same matches in same time."
            );
        }
        Optional<Match> first = footballMatches.stream()
                .filter(Match::isActive)
                .findFirst();
        if (first.isPresent()) {
            throw new ScoreBoardException(
                    "It is not possible to have two active matches in same time."
            );
        }

        footballMatches.add(new FootballMatch(homeTeam, awayTeam));
    }

    @Override
    public List<Match> getMatches() {
        return footballMatches;
    }

    @Override
    public void removeMatch() {
        Optional<Match> first = footballMatches.stream()
                .filter(Match::isActive)
                .findFirst();

        if(first.isPresent()) {
            Match footballMatch = first.get();
            footballMatch.setActive(false);
        }
    }

    @Override
    public void updateMatch(Team homeTeam, Team awayTeam) {
        FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam);
        checkTeamScoreValue(homeTeam.getScore(), awayTeam.getScore());
        Optional<Match> optionalFoundMatch = footballMatches.stream()
                .filter(m -> m.getHomeTeam().getName().equals(footballMatch.getHomeTeam().getName()))
                .filter(m -> m.getAwayTeam().getName().equals(footballMatch.getAwayTeam().getName()))
                .filter(Match::isActive)
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

    @Override
    public List<Match> getOrderedMatches() {
        footballMatches.sort(
                Comparator.comparing(Match::getScore)
                        .thenComparing(Match::getStartTime)
                        .reversed()
        );

        return footballMatches;
    }

    private void checkTeamScoreValue(int homeTeamScore, int awayTeamScore) {
            if(homeTeamScore < 0 || awayTeamScore < 0 ) {
                throw new ScoreBoardException(
                        "It is not possible to update match with negative values.");
            }
    }
}
