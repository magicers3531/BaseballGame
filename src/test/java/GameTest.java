import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void createGame() {
        assertNotNull(game);
    }

    private void assertIllegalArgument(String guessNumber) {
        try{
            game.guess(guessNumber);
            org.assertj.core.api.Assertions.fail();
        }
        catch (IllegalArgumentException e){

        }
    }


    @Test
    void throwIllegalArgumentExceptionInvalidInput() {
        assertIllegalArgument(null);
        assertIllegalArgument("12");
        assertIllegalArgument("1234");
        assertIllegalArgument("12s");
        assertIllegalArgument("121");
    }

    private void generateQuestion(String questionNumber) {
        game.question = questionNumber;
    }

    private static void assertMatchedNumber(GuessResult result, boolean solved, int strikes, int balls) {
        assertThat(result).isNotNull();
        assertThat(result.isSolved()).isEqualTo(solved);
        assertThat(result.getStrikes()).isEqualTo(strikes);
        assertThat(result.getBalls()).isEqualTo(balls);
    }

    @Test
    void returnSolveResultIfMatchNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("123"), true, 3, 0);
    }

    @Test
    void returnSolveResultIfUnMatchNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("456"), false, 0, 0);
    }

    @Test
    void returnSolveResultIf2S0BNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("124"), false, 2, 0);
    }

    @Test
    void returnSolveResultIf1S2BNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("132"), false, 1, 2);
    }
}