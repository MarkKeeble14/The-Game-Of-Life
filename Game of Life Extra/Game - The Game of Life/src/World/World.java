package World;

import java.util.ArrayList;

import Cell.Cell;
import Cell.Square;
import Entities.Lifeform;
import EntityClasses.Carnivore;
import EntityClasses.Herbivore;
import EntityClasses.Omnivore;
import EntityClasses.Plant;
import Game.Game;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class World {
	private int turn = 0;
	private int rowperline;
	private int colperline;
	private int totalCells;
	protected Cell[] cellList;
	private ArrayList<Lifeform> lifeformList = new ArrayList<Lifeform>();
	private int level;
	public Pane pane;

	public World(int rowperline, int colperline, int level, int tileSize) {
		this.level = level;
		Game.getWorldList().add(this);
	}
	
	public void advanceTurn() {
		System.out.println("\nWorld: " + level + ", Advancing to turn: " + ++turn);

		lifeformsAct();
		updateCellColors();
	}

	public void printContents() {
		for (Lifeform l : getLifeformList()) {
			System.out.println(l.toString());
		}
	}

	public void printQuantities() {
		// Print Quantities
		int a = 0, b = 0, c = 0, d = 0, e = 0;
		for (Lifeform l : getLifeformList()) {
			if (l instanceof Plant) {
				a++;
			} else if (l instanceof Herbivore) {
				b++;
			} else if (l instanceof Carnivore) {
				c++;
			} else if (l instanceof Omnivore) {
				d++;
			}
			e = getCellList().length - a - b - c - d;
		}

		System.out.println(this + ":\nPlants: \t" + a + ",\nHerbivores: \t" + b + ",\nCarnivore: \t" + c 
				+ ",\nOmnivores: \t" + d + ",\nEmpty Cells: \t" + e + "\n");
	}

	public void lifeformsAct() {
		for (int i = 0; i < getLifeformList().size(); i++) {
			Lifeform curResident = getLifeformList().get(i);
			curResident.takeAction();
		}

		printQuantities();
		resetLifeforms();
	}

	public void resetLifeforms() {
		for (Lifeform l : getLifeformList()) {
			l.acted = false;
			l.spawnedThisTurn = false;
		}
	}

	public void updateCellColors() {
		for (int i = 0; i < getCellList().length; i++) {
			getCellList()[i].updateCellColor();
		}
	}

	public int getTotalCells() {
		return totalCells;
	}

	public Cell[] getCellList() {
		return cellList;
	}

	public void setCellList(Cell[] cellList) {
		this.cellList = cellList;
	}

	public ArrayList<Lifeform> getLifeformList() {
		return lifeformList;
	}

	public void setLifeformList(ArrayList<Lifeform> lifeformList) {
		this.lifeformList = lifeformList;
	}

	public int getLevel() {
		return level;
	}

	public int getRowperline() {
		return rowperline;
	}

	public int getColperline() {
		return colperline;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int setPerlines(int row, int col) {
		this.rowperline = row;
		this.colperline = col;
		this.totalCells = row * col;
		return this.totalCells;
	}
}
