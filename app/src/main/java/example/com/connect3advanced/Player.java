package example.com.connect3advanced;

/**
 * Created by Kashish Luthra on 21/10/18.
 */

public class Player {
    private String name;
    private Counter counter;
    private int score;

    public Player(String name, Counter counter) {
        this.name = name;
        this.counter = counter;
        this.score=0;
    }

    public String getName() {
        return name;
    }

    public Counter getCounter() {
        return counter;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(){
        this.score++;
    }
}
