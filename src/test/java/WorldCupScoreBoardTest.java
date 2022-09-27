import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.balaz.worldcup.scoreboard.game.ScoreBoard;
import sk.balaz.worldcup.scoreboard.game.Team;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void itShouldNotInsertMatchWhenSameMatchExists() {
        //given
        Team homeTeam = new Team("Mexico");
        Team awayTeam =  new Team("Canada");

        Team homeTeam2 = new Team("Mexico");
        Team awayTeam2 =  new Team("Canada");

        //when
        underTest.insertMatch(homeTeam, awayTeam);

        //then
        assertThrows(IllegalArgumentException.class, () -> underTest.insertMatch(homeTeam2, awayTeam2));
    }

    @Test
    void itShouldRemoveMatch() {

        //given
        Team homeTeam = new Team("Mexico");
        Team awayTeam =  new Team("Canada");

        //when
        underTest.insertMatch(homeTeam, awayTeam);
        underTest.removeMatch();

        //then
        assertTrue(underTest.getMatches().isEmpty());
        assertFalse(underTest.getMatches().stream()
                .anyMatch(match -> !match.isActive()));

    }

    @Test
    void itShouldUpdateMatch() {

        //given
        Team homeTeam = new Team("Mexico");
        Team awayTeam =  new Team("Canada");

        //when
        underTest.insertMatch(homeTeam, awayTeam);

        assertEquals(0, homeTeam.getScore());
        assertEquals(0, awayTeam.getScore());

        homeTeam.setScore(0);
        awayTeam.setScore(5);

        underTest.updateMatch(homeTeam, awayTeam);

        //then
        assertEquals(0, homeTeam.getScore());
        assertEquals(5, awayTeam.getScore());

    }
}
