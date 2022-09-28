package sk.balaz.worldcup.scoreboard.game;

import java.time.LocalDateTime;

public interface Match {

    int getScore();

    Team getHomeTeam();

    Team getAwayTeam();

    void setActive(boolean active);

    boolean isActive();

    LocalDateTime getStartTime();
}
