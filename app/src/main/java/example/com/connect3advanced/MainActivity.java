package example.com.connect3advanced;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final float TRANSLATE_AMOUNT = 1000f;
    private Game game;
    private LinearLayout playAgainLayout;
    private TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();

        playAgainLayout = (LinearLayout) findViewById((R.id.playAgainLayout));
        playAgainLayout.setVisibility(LinearLayout.GONE);

        scoreText = (TextView) findViewById(R.id.scoreText);
        scoreText.setText(game.getScoreDisplayText());
    }

    public void placeCounter(View view) {
        int counterPosition = Integer.parseInt(view.getTag().toString());

        if (game.isSelectedSpaceAvailable(counterPosition)) {
            game.placeCounter(counterPosition);
            animatePlayersCounter(view);

            if (game.isBoardFilled()) {
                ((TextView)findViewById(R.id.playAgainText))
                        .setText("Game ends a draw! Play again?");
                playAgainLayout.setBackgroundColor(Color.LTGRAY);
                playAgainLayout.setVisibility(LinearLayout.VISIBLE);
                return;
            } else if (game.isWinner()) {
                ((TextView)findViewById(R.id.playAgainText))
                        .setText(game.getCurrentPlayer().getName() + " is the winner!");
                switch (game.getCurrentCounterColour()) {
                    case RED:
                        playAgainLayout.setBackgroundColor(Color.RED);
                        break;
                    case YELLOW:
                        playAgainLayout.setBackgroundColor(Color.YELLOW);
                }
                game.increaseCurrentPlayersScore();
                scoreText.setText(game.getScoreDisplayText());
                playAgainLayout.setVisibility(LinearLayout.VISIBLE);
                return;
            }

            game.switchCurrentPlayer();
        } else {
            makeLongToast("Space taken");
        }
    }

    private void animatePlayersCounter(View view) {
        ImageView counter = (ImageView) view;
        setCounterImageColourBasedOnCurrentPlayer(counter);
        counter.setTranslationY(TRANSLATE_AMOUNT * -1f);
        counter.setAlpha(1f);
        counter.animate().translationYBy(TRANSLATE_AMOUNT).rotationBy(360f).setDuration(500);
    }

    private void setCounterImageColourBasedOnCurrentPlayer(ImageView counter) {
        switch (game.getCurrentCounterColour()) {
            case RED:
                counter.setImageResource(R.drawable.red);
                break;
            case YELLOW:
                counter.setImageResource(R.drawable.yellow);
        }
    }

    public void playAgainButtonClick(View view) {
        game.reset();
        resetCounterImages();
        playAgainLayout.setVisibility(LinearLayout.GONE);
    }

    private void resetCounterImages() {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counterView = (ImageView) gridLayout.getChildAt(i);
            counterView.setAlpha(0f);
        }
    }

    private void makeLongToast(String message) {
        makeToast(message, Toast.LENGTH_LONG);
    }

    private void makeShortToast(String message) {
        makeToast(message, Toast.LENGTH_SHORT);
    }

    private void makeToast(String message, int toastLength) {
        Toast.makeText(getApplicationContext(), message, toastLength).show();
    }
}
