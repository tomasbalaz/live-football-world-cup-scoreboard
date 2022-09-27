package sk.balaz.worldcup.scoreboard.game;

public class Team {

    private final String name;

    private int score;

    public Team(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
