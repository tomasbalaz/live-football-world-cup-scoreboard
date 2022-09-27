import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.balaz.worldcup.scoreboard.game.ScoreBoard;
import sk.balaz.worldcup.scoreboard.game.Team;

public class WorldCupScoreBoardTest {

    private ScoreBoard underTest;

    @BeforeEach
    void setUp() {
        underTest = new ScoreBoard();
    }

    @Test
    void itShouldInsertMatch() {
        //given
        Team homeTeam = new Team();
        Team awayTeam =  new Team();

        //when
        underTest.insertMatch(Team homeTeam, Team awayTeam);

        //then
    }
}
