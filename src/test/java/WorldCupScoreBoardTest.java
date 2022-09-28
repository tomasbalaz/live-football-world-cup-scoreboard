import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.balaz.worldcup.scoreboard.exception.ScoreBoardException;
import sk.balaz.worldcup.scoreboard.game.Match;
import sk.balaz.worldcup.scoreboard.game.ScoreBoard;
import sk.balaz.worldcup.scoreboard.game.Team;

import java.util.List;

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

    @Test
    void itShouldNotUpdateMatchWhenMatchIsDisabled() {
        //given
        Team homeTeam = new Team("Mexico");
        Team awayTeam =  new Team("Canada");

        //when
        underTest.insertMatch(homeTeam, awayTeam);
        underTest.removeMatch();

        //then
        assertThrows(ScoreBoardException.class, () -> underTest.updateMatch(homeTeam, awayTeam));
    }

    @Test
    void itShouldReturnOrderedMatches() {

        //given
        Team home1 = new Team("Mexico");
        Team away1 = new Team("Canada");

        home1.setScore(0);
        away1.setScore(5);

        Team home2 = new Team("Spain");
        Team away2 = new Team("Brazil");

        home2.setScore(10);
        away2.setScore(2);

        Team home3 = new Team("Germany");
        Team away3 = new Team("France");

        home3.setScore(2);
        away3.setScore(2);

        Team home4 = new Team("Uruguay");
        Team away4 = new Team("Italy");

        home4.setScore(6);
        away4.setScore(6);

        Team home5 = new Team("Argentina");
        Team away5 = new Team("Australia");

        home5.setScore(3);
        away5.setScore(1);

        underTest.insertMatch(home1, away1);
        underTest.insertMatch(home2, away2);
        underTest.insertMatch(home3, away3);
        underTest.insertMatch(home4, away4);
        underTest.insertMatch(home5, away5);

        //when
        List<Match> orderedMatches = underTest.getOrderedMatches();

        //then
        assertFalse(orderedMatches.isEmpty());
        //1. Uruguay 6 - Italy 6
        assertEquals(new Match(home4, away4), orderedMatches.get(0));
        //2. Spain 10 - Brazil 2
        assertEquals(new Match(home2, away2), orderedMatches.get(1));
        //3. Mexico 0 - Canada 5
        assertEquals(new Match(home1, away1), orderedMatches.get(2));
        //4. Argentina 3 - Australia 1
        assertEquals(new Match(home5, away5), orderedMatches.get(3));
        //5. Germany 2 - France 2
        assertEquals(new Match(home3, away3), orderedMatches.get(4));

    }
}
