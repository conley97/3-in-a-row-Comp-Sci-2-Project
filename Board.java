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
	public static String[] colors = { "#fc03d3", "#fc0303", "#f0fc03", "#0bfc03", "#03fcf4", "#0318fc"};
	Random rand = new Random();
	static int count = 0;
	List<Integer> colori   = new ArrayList<>();
	public static Tile selected = null;
	public static Tile[][] board = new Tile[5][5];
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("3 in a row");
		for(int i = 0 ; i < colors.length; i++) {
			colori.add(i);
		}
		primaryStage.setScene(new Scene(creategame()));
		primaryStage.show();
		
		
	}
	public Parent creategame() {
		Pane root = new Pane();
		root.setPrefSize(800, 500);
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				board[row][col] =new Tile(row, col, boardHasNoMatches(row, col));
				count++;
				board[row][col].setTranslateX(row * 100);
				board[row][col].setTranslateY(col * 100);
				root.getChildren().add(board[row][col]);
			}
		}
		return root;
	}
	int boardHasNoMatches(int row, int col) {
		List<Integer> possibleI   = new ArrayList<>();
		possibleI.addAll(colori);
		try {
			possibleI.remove(Integer.valueOf(board[row][col-1].getZ()));
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}catch(IndexOutOfBoundsException e) {
			
		}
		try {
			possibleI.remove(Integer.valueOf(board[row-1][col].getZ()));
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}catch(IndexOutOfBoundsException e) {	
		}
		int size = rand.nextInt(possibleI.size());
		return possibleI.get(size);
	}
}
