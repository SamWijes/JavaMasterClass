package dev.gameConsoleCh;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public  abstract class Game <T extends Player>  {
    private final Supplier<T> constructor;
    private String  gameName;
    private List<T> players;
    private Map<Character ,GameAction> actionMap;

    protected Game(Supplier<T> constructor) {
        this.constructor = constructor;
    }


    public T createNewPlayer(T player){
        T newPlayer=constructor.get();

        return newPlayer;
    }

    public GameAction getGameActions(char prompt){
        return actionMap.get(prompt);
    }


}
