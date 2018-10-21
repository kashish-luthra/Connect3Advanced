package example.com.connect3advanced;

/**
 * Created by Kashish Luthra on 21/10/18.
 */

public class GameBoard {
    private BoardSpace[] spaces = new BoardSpace[9];
    private int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}
            , {0, 3, 6}, {1, 4, 7}, {2, 5,  8}, {0, 4, 8}, {2, 4, 6}};

    public GameBoard() {
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = new BoardSpace();
        }
    }

    public boolean isSelectedSpaceAvailable(int spacePosition) {
        return spaces[spacePosition].isAvailable();
    }

    public void placeCounterOnSelectedSpace(int spacePosition, Counter counter) {
        spaces[spacePosition].placeCounter(counter);
    }

    public boolean isWinner() {
        for (int[] winningCombo : winningPositions) {
            if (combinationContainsWinner(winningCombo)) {
                return true;
            }
        }
        return false;
    }

    private boolean combinationContainsWinner(int[] combo) {
        return isSpaceCombinationFilled(combo) && allSpacesTakenBySamePlayer(combo);
    }

    private boolean allSpacesTakenBySamePlayer(int[] combo) {
        return (spaces[combo[0]].getPlacedCounterSymbol()
                .equals(spaces[combo[1]].getPlacedCounterSymbol()))
                && (spaces[combo[1]].getPlacedCounterSymbol()
                .equals(spaces[combo[2]].getPlacedCounterSymbol()));
    }

    private boolean isSpaceCombinationFilled(int[] combo) {
        for (int i: combo) {
            if (spaces[i].getPlacedCounterSymbol().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public boolean isFilled() {
        for (BoardSpace space : spaces) {
            if (space.isAvailable()) {
                return false;
            }
        }

        return true;
    }

    public void reset() {
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = new BoardSpace();
        }
    }
}
