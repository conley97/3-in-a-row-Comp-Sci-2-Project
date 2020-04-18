
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
import javafx.scene.text.Text;

import java.lang.Math;
import java.util.*;


class Tile extends StackPane{
	Circle tile = new Circle();
	Random rand = new Random();
	int row;
	int col;
	int z;
	int xmatches;
	int ymatches;
	List<Tile> rmatches;
	List<Tile> cmatches;
	private Scoreboard scoreboard;
	private Stopwatch stopwatch;
	public Tile(int r, int c, int z, Stopwatch stopwatch, Scoreboard score) {
		tile.setRadius(50);
		this.scoreboard = score;
		this.stopwatch = stopwatch;
		this.row = r;
		this.col = c;
		this.z = z;
		tile.setFill(Color.valueOf(Board.colors[z]));
		tile.setStroke(Color.BLACK);
		setAlignment(Pos.CENTER);
		getChildren().addAll(tile);		
		setOnMouseClicked(event ->{
			if (Board.selected == null) {
				Board.selected = this;
				stopwatch.go();
			}else{
				change(Board.selected, this);
				findandChangeMatch(this);
				findandChangeMatch(Board.selected);
				Board.selected= null;
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
	public int getZ() {
		return z;

	}
	public int upMatch(int row, int col, Tile a, int matches) {
		List<Tile> upmatches   = new ArrayList<>();
	    
	    
	   
	    Paint match = a.getColor();
	    
	    row--;
	  
	    while (row >= 0 && a.getColor() == match) {
	      matches++;
	      upmatches.add(Board.board[row][col]);
	      row--;
	    }  	
	    	return matches;
	  }
	public void findandChangeMatch(Tile a) {
		HashSet<Tile> matches   = new HashSet<>();
		xmatches = 0;
		ymatches = 0;
		rmatches = new ArrayList<>();
		cmatches = new ArrayList<>();
		upMatch(a.getY(a), a.getX(a), a);
		downMatch(a.getY(a), a.getX(a), a);
		rightMatch(a.getY(a), a.getX(a), a);
		leftMatch(a.getY(a), a.getX(a), a);
		if(rmatches.size()>=3 || cmatches.size()>=3) {
			matches.addAll(rmatches);
			matches.addAll(cmatches);
		}
		 Object[] match =  matches.toArray();
		for(int i = 0; i < match.length; i++) {
			((Tile) match[i]).setColor(Color.valueOf(Board.colors[rand.nextInt(Board.colors.length)]));
			
		}
		scoreboard.change(match.length);
	}
	public void upMatch(int row, int column, Tile a) {
		ymatches = 0;

		Paint match = Board.board[row][column].getColor();
		rmatches.add(Board.board[getY(a)][getX(a)]);
		row--;
		while (row >= 0 && Board.board[row][column].getColor().equals(match)) {
			ymatches++;
			rmatches.add(Board.board[row][column]);
			row--;
		}
	}
	public void downMatch(int row, int column, Tile a) {

		Paint match = Board.board[row][column].getColor();

		row++;
		while (row < 5  && Board.board[row][column].getColor().equals(match)) {
			ymatches++;
			rmatches.add(Board.board[row][column]);
			row++;
		}

	}

	public void rightMatch(int row, int column, Tile a) {

		xmatches = 0;

		Paint match = Board.board[row][column].getColor();
		cmatches.add(Board.board[row][column]);
		column++;
		while (column < 5 && Board.board[row][column].getColor().equals(match)) {
			xmatches++;
			cmatches.add(Board.board[row][column]);
			column++;
		}

	}

	public void leftMatch(int row, int column, Tile a) {

		

		Paint match = Board.board[row][column].getColor();

		column--;
		while (column >= 0 && Board.board[row][column].getColor().equals(match)) {
			xmatches++;
			cmatches.add(Board.board[row][column]);
			column--;
		}

	}

}

