import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Shape;
import java.util.*;

public class Board extends Application{
	public static void main (String[]args) {
		launch(args);
	}
	public static Color[] colors1= { Color.ALICEBLUE, Color.DARKGREEN, Color.RED, Color.GREENYELLOW};	
	Color[] colors2= { Color.ALICEBLUE, Color.DARKGREEN, Color.RED, Color.GREENYELLOW};	
	Color[] colors3= { Color.ALICEBLUE, Color.DARKGREEN, Color.RED, Color.GREENYELLOW};	
	Color[] colors4= { Color.ALICEBLUE, Color.DARKGREEN, Color.RED, Color.GREENYELLOW};	
	public static Tile selected = null;
	public static Tile[][] board = new Tile[5][5];
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("3 in a row");
		primaryStage.setScene(new Scene(creategame()));
		primaryStage.show();
	}
	public Parent creategame() {
		Pane root = new Pane();
		root.setPrefSize(800, 500);
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				Tile tile = new Tile(row, col);
				tile.setTranslateX(row * 100);
				tile.setTranslateY(col * 100);
				board[row][col] = tile;
				root.getChildren().add(tile);
			}
		}
		return root;
	}
}
