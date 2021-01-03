package Cell;

import Entities.*;
import EntityClasses.Carnivore;
import EntityClasses.Herbivore;
import EntityClasses.Omnivore;
import EntityClasses.Plant;
import RandomGenerator.RandomGenerator;
import World.World;
import javafx.event.ActionEvent;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The Class Tile.
 */
public class Square extends Rectangle implements Cell {
	protected int row;
	protected int col;
	protected int level;
	protected World parWorld;
	protected int cellCoordinate;
	protected Lifeform resident;
	
	public Square(int row, int col, int level, int coord, float tilesize, World w, boolean spawnable) {
		this.row = row;
		this.col = col;
		this.cellCoordinate = coord;
		this.level = level;
		parWorld = w;

		setFill(Color.WHITE);
		setStroke(Color.BLACK);
		setWidth(tilesize);
		setHeight(tilesize);
		
		if (spawnable) {
			generateLifeform();
		}
	}
	
	public void generateLifeform() {
		int rand = RandomGenerator.nextNumber(100);

		if (rand >= 80) {
			resident = new Herbivore(this, 1, 1, 2, 2, 5);
		} else if (rand >= 60) {
			resident = new Plant(this, 1, 2, 3, 0, -1);
		} else if (rand >= 50) {
			resident = new Carnivore(this, 1, 1, 3, 2, 5);
		} else if (rand >= 45) {
			resident = new Omnivore(this, 1, 1, 3, 1, 5);
		}

		if (resident != null) {
			parWorld.getLifeformList().add(resident);
			resident.curWorld = parWorld;
			resident.level = level;
		}
	}

	public void updateCellColor() {
		if (resident != null) {
			// Spawning
			if (resident instanceof Herbivore) {
				setFill(Color.YELLOW);
			} else if (resident instanceof Plant) {
				setFill(Color.GREEN);
			} else if (resident instanceof Omnivore) {
				setFill(Color.BLUE);
			} else if (resident instanceof Carnivore) {
				setFill(Color.RED);
			}
		} else {
			setFill(Color.WHITE);
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Lifeform getResident() {
		return resident;
	}

	public void setResident(Lifeform resident) {
		this.resident = resident;
	}

	public void evictResident() {
		this.resident = null;
	}

	public int getCellCoordinate() {
		return cellCoordinate;
	}

	public World getParWorld() {
		return parWorld;
	}

	public void setParWorld(World parWorld) {
		this.parWorld = parWorld;
	}

	public String getPositionInWorld() {
		return ("row: " + row + ", col: " + col + ", level: " + level);
	}

	@Override
	public String toString() {
		return "Cell [row=" + row + ", col=" + col + ", level=" + level + ", cellCoordinate=" + cellCoordinate
				+ ", resident=" + resident + "]";
	}
	
	public void setColor(Color color) {
		setFill(color);
	}
}
