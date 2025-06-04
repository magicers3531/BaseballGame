import org.assertj.core.api.Assertions;
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

    @Test
    void returnSolveResultIfMatchNumber() {
        game.question = "123";
        GuessResult result = game.guess("123");

        assertThat(result).isNotNull();
        assertThat(result.isSolved()).isEqualTo(true);
        assertThat(result.getStrikes()).isEqualTo(3);
        assertThat(result.getBalls()).isEqualTo(0);
    }

    @Test
    void returnSolveResultIfUnMatchNumber() {
        game.question = "123";
        GuessResult result = game.guess("456");

        assertThat(result).isNotNull();
        assertThat(result.isSolved()).isEqualTo(false);
        assertThat(result.getStrikes()).isEqualTo(0);
        assertThat(result.getBalls()).isEqualTo(0);
    }
}