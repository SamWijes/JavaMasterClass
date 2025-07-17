public class MethodChallenge {
    public static void main(String[] args) {
    displayHighScorePosition("Tim",calculateHighScorePosition(1500));
    displayHighScorePosition("Tim",calculateHighScorePosition(1000));
    displayHighScorePosition("Tim",calculateHighScorePosition(500));
    displayHighScorePosition("Tim",calculateHighScorePosition(100));
    displayHighScorePosition("Tim",calculateHighScorePosition(25));

    }

    public  static void displayHighScorePosition(String playerName,int playerPosition){
        System.out.println(playerName+" managed to get into "+playerPosition+" on the High score list");
    }
    public  static int calculateHighScorePosition(int playerScore){
        if (playerScore>=1000){
            return 1;
        }
        else if (playerScore>=500 && playerScore<1000  ) {
            return 2;
        }
        else if (playerScore>=100 && playerScore<500 ) {
            return 3;
        }
        else if (playerScore>=100 && playerScore<500 ) {
            return 3;
        }
        else return 4;
    }
}
