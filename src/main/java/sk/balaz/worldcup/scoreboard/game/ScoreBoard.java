package sk.balaz.worldcup.scoreboard.game;

import java.util.List;

public interface ScoreBoard {
    void insertMatch(Team homeTeam, Team awayTeam);

    List<Match> getMatches();

    void removeMatch();

    void updateMatch(Team homeTeam, Team awayTeam);

    List<Match> getOrderedMatches();
}
