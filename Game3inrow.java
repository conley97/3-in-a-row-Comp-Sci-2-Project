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
public class Game3inrow extends Application {
	Tile[][] board = new Tile[5][5];
	class Tile extends StackPane{
		Circle tile = new Circle();
		int row;
		int col;
		public Tile(int r, int c) {
			tile.setRadius(50);
			row = r;
			col = c;
			tile.setFill(colors1[new Random().nextInt(colors1.length)]);
			tile.setStroke(Color.BLACK);
			setAlignment(Pos.CENTER);
			getChildren().addAll(tile);		
			setOnMouseClicked(event ->{
				if (selected == null) {
					selected = this;
					System.out.println(getX(this));
					System.out.println(getY(this));
				}else{
					change(selected, this);
					checkState(col, row, this);
					checkState(getX(selected), getY(selected), selected);
					selected = null;
				}

			});
		}
		public void change(Tile a, Tile b) {
			Paint temp = a.getColor();
			a.setColor(b.getColor());
			b.setColor(temp);
		}
		public Paint getColor() {
			return tile.getFill();
		}
		public void setColor(Paint a){
			tile.setFill(a);
		}
		public int getX(Tile a) {
			return col;

		}public int getY(Tile a) {
			return row;

		}
		public void checkState(int x, int y, Tile a) {
			Tile[] same = new Tile[9];
			int count = 0;
			Paint match = board[y][x].getColor();
			for(int i = x+1; i < 4; i++) {
				if(board[y][i].getColor()==match) {
					same[count] = board[y][i];
					count++;
				}else
					break;
			}
			for(int i = x-1; i <= 0; i--) {
				if (x==0) {
					break;
				}
				if(board[y][i].getColor()==match) {
					same[count] = board[y][i];
					count++;
				}else
					break;
			}
			for(int i = y+1; i < 4; i++) {
				if(board[i][x].getColor()==match) {
					same[count] = board[i][x];
					count++;
				}else
					break;
			}
			for(int i = y-1; i <= 0; i--) {
				if (x==0) {
					break;
				}
				if(board[i][x].getColor()==match) {
					same[count] = board[i][x];
					count++;
				}else
					break;
			}
			for(int i = 0; i < count; i++) {
				same[i].setColor(colors1[new Random().nextInt(colors1.length)]);
			}
		}
	}

	public static void main (String[]args) {
		launch(args);
	}
	Color[] colors1= { Color.ALICEBLUE, Color.DARKGREEN, Color.RED, Color.GREENYELLOW};	
	Color[] colors2= { Color.ALICEBLUE, Color.DARKGREEN, Color.RED, Color.GREENYELLOW};	
	Color[] colors3= { Color.ALICEBLUE, Color.DARKGREEN, Color.RED, Color.GREENYELLOW};	
	Color[] colors4= { Color.ALICEBLUE, Color.DARKGREEN, Color.RED, Color.GREENYELLOW};	
	Tile selected = null;

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