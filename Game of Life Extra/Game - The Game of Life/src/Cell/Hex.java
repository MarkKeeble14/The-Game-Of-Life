package Cell;

import Entities.Lifeform;
import EntityClasses.Carnivore;
import EntityClasses.Herbivore;
import EntityClasses.Omnivore;
import EntityClasses.Plant;
import RandomGenerator.RandomGenerator;
import World.World;
import World.WorldOfHexs;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Hex extends Polygon implements Cell {
	protected int row;
	protected int col;
	protected int level;
	protected World parWorld;
	protected int cellCoordinate;
	protected Lifeform resident;
	
	public Hex(double x, double y, int posX, int posY, int pos, int level, float tilesize, boolean spawnable, World w) {
		parWorld = w;
		
		col = posX;
		row = posY;
		double r = WorldOfHexs.r;
		double n = WorldOfHexs.n;
		double TILE_WIDTH = WorldOfHexs.TILE_WIDTH;
		
		// creates the polygon using the corner coordinates
		getPoints().addAll(x, y, x, y + r, x + n, y + r * 1.5, x + TILE_WIDTH, y + r, x + TILE_WIDTH, y, x + n,
				y - r * 0.5);

		// set up the visuals and a click listener for the tile
		setFill(Color.WHITE);
		setStrokeWidth(1);
		setStroke(Color.BLACK);
		
		if (spawnable) {
			generateLifeform();
		}
		setOnMouseClicked(e -> System.out.println("Clicked: " + this));
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

	public String toString() {
		return "Cell [row=" + row + ", col=" + col + ", level=" + level + ", cellCoordinate=" + cellCoordinate
				+ ", resident=" + resident + "]";
	}
	
	public void setColor(Color color) {
		setFill(color);
	}
}
