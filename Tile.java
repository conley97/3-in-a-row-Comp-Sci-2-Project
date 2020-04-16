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

class Tile extends StackPane{
		Circle tile = new Circle();
		int row;
		int col;
		public Tile(int r, int c) {
			tile.setRadius(50);
			row = r;
			col = c;
			tile.setFill(Board.colors1[new Random().nextInt(Board.colors1.length)]);
			tile.setStroke(Color.BLACK);
			setAlignment(Pos.CENTER);
			getChildren().addAll(tile);		
			setOnMouseClicked(event ->{
				if (Board.selected == null) {
					Board.selected = this;
					System.out.println(getX(this));
					System.out.println(getY(this));
				}else{
					change(Board.selected, this);
					checkState(col, row, this);
					checkState(getX(Board.selected), getY(Board.selected), Board.selected);
					Board.selected = null;
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
			Tile[] sameY = new Tile[9];
			int count = 0;
			Paint match = Board.board[y][x].getColor();
			//CASE 1 X
			//CASE 1 X
			//CASE 1 X
			try {
				if(Board.board[y][x+1].getColor()==match && Board.board[y][x+2].getColor()==match){
					
					same[0] = Board.board[y][x];
					same[1] = Board.board[y][x+1];
					same[2] = Board.board[y][x+2];
					count =3;
					extraHorizontal(same, count, match);

				}
			}catch(ArrayIndexOutOfBoundsException e) {

			}
			//CASE 2 X
			//CASE 2 X
			//CASE 2 X
			try {
				if(Board.board[y][x-1].getColor()==match && Board.board[y][x+1].getColor()==match){
					
					same[0] = Board.board[y][x-1];
					same[1] = Board.board[y][x];
					same[2] = Board.board[y][x+1];
					count =3; 
					extraHorizontal(same, count, match);
				}
			}catch(ArrayIndexOutOfBoundsException e) {

			}
			//CASE 3 X
			//CASE 3 X
			//CASE 3 X
			try {
				if(Board.board[y][x-1].getColor()==match && Board.board[y][x-2].getColor()==match){
					
					same[0] = Board.board[y-2][x];
					same[1] = Board.board[y-1][x];
					same[2] = Board.board[y][x];
					count =3; 
					same = extraHorizontal(same, count, match);
				}
			}catch(ArrayIndexOutOfBoundsException e) {

			}
			//CASE 1 Y
			//CASE 1 Y
			//CASE 1 Y
			try {
				if(Board.board[y+1][x].getColor()==match && Board.board[y+2][x].getColor()==match){
					
					sameY[0] = Board.board[y][x];
					sameY[1] = Board.board[y+1][x];
					sameY[2] = Board.board[y+2][x];
					count =3;
					extraVerticle(sameY, count, match);

				}
			}catch(ArrayIndexOutOfBoundsException e) {

			}
			//CASE 2 Y
			//CASE 2 Y
			//CASE 2 Y
			try {
				if(Board.board[y-1][x].getColor()==match && Board.board[y+1][x].getColor()==match){
					
					sameY[0] = Board.board[y-1][x];
					sameY[1] = Board.board[y][x];
					sameY[2] = Board.board[y+1][x];
					count =3; 
					extraVerticle(sameY, count, match);
				}
			}catch(ArrayIndexOutOfBoundsException e) {

			}
			//CASE 3 Y
			//CASE 3 Y
			//CASE 3 Y
			try {
				if(Board.board[y-1][x].getColor()==match && Board.board[y-2][x].getColor()==match){
					sameY[0] = Board.board[y-2][x];
					sameY[1] = Board.board[y-1][x];
					sameY[2] = Board.board[y][x];
					count =3; 
					sameY = extraVerticle(sameY, count, match);
				}
			}catch(ArrayIndexOutOfBoundsException e) {

			}
			try {
			for(int i = 0; i < 25; i++) {
				same[i].setColor(Board.colors1[new Random().nextInt(Board.colors1.length)]);
			}
			}catch(NullPointerException e) {

			}
			try {
				for(int i = 0; i < 25; i++) {
					sameY[i].setColor(Board.colors1[new Random().nextInt(Board.colors1.length)]);
				}}catch(NullPointerException e) {

				}
		}
	
	public Tile[] extraHorizontal(Tile[] same, int count, Paint match) {
		int c1 = same[0].getX(same[0]);
		int c2 = same[2].getX(same[0]);
		int r1 = same[0].getY(same[0]);
		int r2 = same[2].getY(same[0]);
		count = 3;
		try {
			for(int i = 1; i < 4 ; i++) {
				if(Board.board[r2][c2+i].getColor()==match) {
					same[count]=Board.board[r2][c2+i];
					count++;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
		}
		try {
			for(int i = 1; i < 4 ; i++) {
				if(Board.board[r1][c1-i].getColor()==match) {
					same[count]=Board.board[r1][c1-i];
					count++;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
		}
		return same;
	}
	public Tile[] extraVerticle(Tile[] same, int count, Paint match) {
		int c1 = same[0].getX(same[0]);
		int c2 = same[2].getX(same[0]);
		int r1 = same[0].getY(same[0]);
		int r2 = same[2].getY(same[0]);
		count = 3;
		try {
			for(int i = 1; i < 4 ; i++) {
				if(Board.board[r2+i][c2].getColor()==match) {
					same[count]=Board.board[r2+i][c2];
					count++;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
		}
		try {
			for(int i = 1; i < 4 ; i++) {
				if(Board.board[r1-i][c1].getColor()==match) {
					same[count]=Board.board[r1-i][c1];
					count++;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
		}
		return same;
	}


}
