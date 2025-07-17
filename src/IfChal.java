public class IfChal {
    public static void main(String[] args) {
        calculateScore(true,800,5,100);
        calculateScore(true,10000,8,200);
        int score=calculateScoreRet(true,4000,10,150);
        System.out.println(score);

    }
    public static void calculateScore(boolean gameOver,int score,  int levelCompleted,int bonus){

        int finalScore=score;
        if(gameOver){
            finalScore+=levelCompleted * bonus;
            finalScore+=1000;
            System.out.println("Your Final Score was "+ finalScore);
        }
    }
    public static int calculateScoreRet(boolean gameOver,int score,  int levelCompleted,int bonus){

        int finalScore=score;
        if(gameOver){
            finalScore+=levelCompleted * bonus;
            finalScore+=1000;
            System.out.println("Your Final Score was "+ finalScore);
        }
        return finalScore;
    }
}
