package World;

import java.util.ArrayList;
import java.util.Random;

import Cell.Cell;
import Cell.Hex;
import Cell.Square;
import RandomGenerator.*;
import Entities.*;
import EntityClasses.*;
import Game.Game;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class WorldOfHexs extends World {
	public final static double r = 20; // the inner radius from hexagon center to outer corner
	public final static double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the
																// axis
	public final static double TILE_HEIGHT = 2 * r;
	public final static double TILE_WIDTH = 2 * n;
	public static final int WINDOW_HEIGHT = 800;
	public static final int WINDOW_WIDTH = 400;
	
	/**
	 * Instantiates a new World.
	 *
	 * @param level the level
	 */
	public WorldOfHexs(int rowperline, int colperline, int tilesize, int level) {
		super(rowperline, colperline, level, tilesize);
		pane = new AnchorPane();

		cellList = new Cell[rowperline * colperline];
		int xStartOffset = 20; // offsets the entire field to the right
		int yStartOffset = 20; // offsets the entire field downwards
		int pos = 0;
		for (int row = 0; row < rowperline; row++) {
			for (int col = 0; col < colperline; col++, pos++) {
				double xCoord = row * TILE_WIDTH + (col % 2) * n + xStartOffset;
				double yCoord = col * TILE_HEIGHT * 0.75 + yStartOffset;

				Hex hex = new Hex(xCoord, yCoord, col, row, pos, level, tilesize, true, this);
				cellList[pos] = hex;
				pane.getChildren().add(hex);
			}
		}
		
		updateCellColors();
		printQuantities();
		resetLifeforms();
	}
}
