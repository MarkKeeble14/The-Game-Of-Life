package World;

import java.util.ArrayList;
import java.util.Random;

import Cell.Cell;
import Cell.Square;
import RandomGenerator.*;
import Entities.*;
import EntityClasses.*;
import Game.Game;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WorldOfSquares extends World {
	private final static double r = 20; // the inner radius from hexagon center to outer corner
	private final static double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the
	/**
	 * Instantiates a new World.
	 *
	 * @param level the level
	 */
	public WorldOfSquares(int rowperline, int colperline, int level, int tilesize) {
		super(rowperline, colperline, level, tilesize);
		pane = new GridPane();

		cellList = new Cell[rowperline * colperline];
		
		for (int row = 0, tile = 0; row < rowperline; row++) {
			for (int col = 0; col < colperline; col++, tile++) {
				Square s = new Square(row, col, tile, tilesize, level, this, true);
				getCellList()[tile] = s;
				((GridPane)pane).add(s, row, col);
			}
		}

		updateCellColors();
		printQuantities();
		resetLifeforms();
	}
}
