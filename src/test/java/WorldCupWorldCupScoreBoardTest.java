import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.balaz.worldcup.scoreboard.exception.ScoreBoardException;
import sk.balaz.worldcup.scoreboard.game.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorldCupWorldCupScoreBoardTest {

    private ScoreBoard underTest;

    @BeforeEach
    void setUp() {
        List<Match> footballMatches = new ArrayList<>();
        underTest = new WorldCupScoreBoard(footballMatches);
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
                .anyMatch(footballMatch -> "Mexico".equals(footballMatch.getHomeTeam().getName()))
        );
        assertTrue(underTest.getMatches().stream()
                .anyMatch(footballMatch -> "Canada".equals(footballMatch.getAwayTeam().getName()))
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
        assertThrows(ScoreBoardException.class, () -> underTest.insertMatch(homeTeam2, awayTeam2));
    }

    @Test
    void itShouldNotInsertMatchWhenActiveMatchExist() {

        //given
        Team homeTeam = new Team("Mexico");
        Team awayTeam =  new Team("Canada");

        Team homeTeam2 = new Team("Spain");
        Team awayTeam2 =  new Team("Brazil");

        //when
        underTest.insertMatch(homeTeam, awayTeam);
        //then
        assertThrows(ScoreBoardException.class, () -> underTest.insertMatch(homeTeam2, awayTeam2));
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
        assertFalse(underTest.getMatches().isEmpty());
        assertFalse(underTest.getMatches().get(0).isActive());
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
        underTest.updateMatch(homeTeam, awayTeam);
        underTest.removeMatch();

        //then
        assertThrows(ScoreBoardException.class, () -> underTest.updateMatch(homeTeam, awayTeam));
    }

    @Test
    void itShouldNotUpdateTeamWithNegativeTeamScoreValue() {

        //given
        Team homeTeam = new Team("Mexico");
        Team awayTeam =  new Team("Canada");

        //when
        underTest.insertMatch(homeTeam, awayTeam);

        homeTeam.setScore(-1);
        awayTeam.setScore(-1);

        underTest.updateMatch(homeTeam, awayTeam);
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
        underTest.removeMatch();
        underTest.insertMatch(home2, away2);
        underTest.removeMatch();
        underTest.insertMatch(home3, away3);
        underTest.removeMatch();
        underTest.insertMatch(home4, away4);
        underTest.removeMatch();
        underTest.insertMatch(home5, away5);
        underTest.removeMatch();

        //when
        List<Match> orderedFootballMatches = underTest.getOrderedMatches();

        //then
        assertFalse(orderedFootballMatches.isEmpty());
        //1. Uruguay 6 - Italy 6
        assertEquals(new FootballMatch(home4, away4), orderedFootballMatches.get(0));
        //2. Spain 10 - Brazil 2
        assertEquals(new FootballMatch(home2, away2), orderedFootballMatches.get(1));
        //3. Mexico 0 - Canada 5
        assertEquals(new FootballMatch(home1, away1), orderedFootballMatches.get(2));
        //4. Argentina 3 - Australia 1
        assertEquals(new FootballMatch(home5, away5), orderedFootballMatches.get(3));
        //5. Germany 2 - France 2
        assertEquals(new FootballMatch(home3, away3), orderedFootballMatches.get(4));

    }
}
