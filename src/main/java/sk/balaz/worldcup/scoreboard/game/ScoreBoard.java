package sk.balaz.worldcup.scoreboard.game;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private final List<Match> matches = new ArrayList<>();


    public void insertMatch(Team homeTeam, Team awayTeam) {
        matches.add(new Match(homeTeam, awayTeam));
    }

    public List<Match> getMatches() {
        return matches;
    }
}
