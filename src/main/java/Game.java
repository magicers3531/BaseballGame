public class Game {

    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);
        if(guessNumber.equals(question)){
            return new GuessResult(true, 3, 0);
        }else{
            int strikeCount = getStrikeCount(guessNumber);
            int ballCount = getBallCount(guessNumber);

            if( strikeCount == 2 && ballCount == 0){
                return new GuessResult(false, 2, 0);
            }else{
                return new GuessResult(false, 0, 0);
            }
        }

    }

    private int getStrikeCount(String guessNumber) {
        int strikeCount = 0;
        for(int i = 0; i < 3; i++){
            if(guessNumber.charAt(i) == question.charAt(i))
                strikeCount++;
        }
        return strikeCount;
    }
    private int getBallCount(String guessNumber) {
        int ballCount = 0;
        if(guessNumber.charAt(0) == question.charAt(1) || guessNumber.charAt(0) == question.charAt(2)) ballCount++;
        if(guessNumber.charAt(1) == question.charAt(0) || guessNumber.charAt(1) == question.charAt(2)) ballCount++;
        if(guessNumber.charAt(2) == question.charAt(0) || guessNumber.charAt(2) == question.charAt(1)) ballCount++;
        return ballCount;
    }

    private void assertIllegalArgument(String guessNumber) {
        if(guessNumber == null){
            throw new IllegalArgumentException();
        }

        if(guessNumber.length() != 3){
            throw new IllegalArgumentException();
        }

        for(char number : guessNumber.toCharArray()){
            if(number < '0' || number > '9'){
                throw new IllegalArgumentException();
            }
        }

        if(idDuplicatedNumber(guessNumber)){
            throw new IllegalArgumentException();
        }
    }

    private boolean idDuplicatedNumber(String guessNumber) {
        return guessNumber.charAt(0) == guessNumber.charAt(1)
                || guessNumber.charAt(0) == guessNumber.charAt(2)
                || guessNumber.charAt(1) == guessNumber.charAt(2);
    }
}
