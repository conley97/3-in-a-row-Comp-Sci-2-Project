import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Stopwatch extends Text {
    private Timeline timeline;
    private int timeSeconds;

    public Stopwatch() {
        timeSeconds = 0;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDisplay()));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void go() {
        timeline.play();
    }

    private void updateDisplay() {
        timeSeconds++;
        setText("Time: " + timeSeconds + "s");
    }
}
