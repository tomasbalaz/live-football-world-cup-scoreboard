import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.balaz.worldcup.scoreboard.game.ScoreBoard;
import sk.balaz.worldcup.scoreboard.game.Team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldCupScoreBoardTest {

    private ScoreBoard underTest;

    @BeforeEach
    void setUp() {
        underTest = new ScoreBoard();
    }

    @Test
    void itShouldInsertMatch() {
        //given
        Team homeTeam = new Team("Mexico");
        Team awayTeam =  new Team("Canada");

        //when
        underTest.insertMatch(homeTeam, awayTeam);

        //then
        assertFalse(underTest.getMatches().isEmpty());
        assertTrue(underTest.getMatches().stream()
                .anyMatch(match -> "Mexico".equals(match.getHomeTeam().getName()))
        );
        assertTrue(underTest.getMatches().stream()
                .anyMatch(match -> "Canada".equals(match.getAwayTeam().getName()))
        );
    }
}
