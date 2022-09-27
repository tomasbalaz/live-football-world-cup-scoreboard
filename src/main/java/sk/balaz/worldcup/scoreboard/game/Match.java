package sk.balaz.worldcup.scoreboard.game;

import java.util.Objects;

public class Match {

    private final Team homeTeam;

    private final Team awayTeam;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(homeTeam.getName(), match.homeTeam.getName())
                && Objects.equals(awayTeam.getName(), match.awayTeam.getName());
    }
}
