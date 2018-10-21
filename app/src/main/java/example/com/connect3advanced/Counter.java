package example.com.connect3advanced;

/**
 * Created by Kashish Luthra on 21/10/18.
 */

public class Counter {
    public enum CounterColor {RED,YELLOW};
    private  CounterColor counterColor;

    public Counter(CounterColor counterColor){
        this.counterColor=counterColor;
    }

    public CounterColor getCounterColor() {
        return counterColor;
    }
}
