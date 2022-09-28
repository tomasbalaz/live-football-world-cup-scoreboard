package sk.balaz.worldcup.scoreboard.game;

import java.time.LocalDateTime;
import java.util.Objects;

public class Match {

    private final Team homeTeam;

    private final Team awayTeam;

    private boolean isActive;

    private final LocalDateTime startTime;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.isActive = true;
        this.startTime = LocalDateTime.now();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getScore() {
        return homeTeam.getScore() + awayTeam.getScore();
    }

    public LocalDateTime getStartTime() {
        return startTime;
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
