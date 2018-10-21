package example.com.connect3advanced;

/**
 * Created by Kashish Luthra on 21/10/18.
 */

public class BoardSpace {
    private boolean isAvailable=true;
    private String placedCounterSymbol="";

    public void placeCounter(Counter counter) {
        isAvailable=false;
        Counter.CounterColor counterColor=counter.getCounterColor();
        switch (counterColor){
            case RED:
                placedCounterSymbol="R";
                break;
            case YELLOW:
                placedCounterSymbol="Y";
                break;

        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getPlacedCounterSymbol() {
        return placedCounterSymbol;
    }
}
