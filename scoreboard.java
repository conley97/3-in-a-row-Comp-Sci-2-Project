import javafx.scene.text.Text;

public class Scoreboard extends Text {
    private int score;

    public Scoreboard() {
        score = 0;
        updateDisplay();
    }

    public void change(int matches) {
        score += matches;
        updateDisplay();
    }

    private void updateDisplay() {
        setText("Score: " + score);
    }
}
