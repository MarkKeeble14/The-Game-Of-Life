package Entities;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

import Cell.Cell;
import Cell.Cell;
import Conditional.*;
import EntityInterfaces.HerbivoreEdible;
import EntityInterfaces.Hungry;
import RandomGenerator.RandomGenerator;
import World.Main;
import World.TileMode;
import javafx.scene.paint.Color;

public abstract class SophisticatedLifeform extends Lifeform implements HerbivoreEdible {
	protected int timeSinceLastMeal;
	protected int startTimeSinceLastMeal;

	protected int nearbyNeighbourCellsNeeded;
	protected int nearbyEmptyCellsNeeded;
	protected int nearbyFoodCellsNeeded;

	protected Conditional neighbourCellsRule;
	protected Conditional emptyCellsRule;
	protected Conditional foodCellsRule;

	public abstract Lifeform construct(Cell c);

	public abstract void takeAction();

	public SophisticatedLifeform(Cell c, int range, int plantCellsNeeded, int emptyCellsNeeded, int foodCellsNeeded,
			int starvationPeriod) {
		updatePositionInWorld(c);

		this.range = range;

		nearbyNeighbourCellsNeeded = plantCellsNeeded;
		nearbyEmptyCellsNeeded = emptyCellsNeeded;
		nearbyFoodCellsNeeded = foodCellsNeeded;

		startTimeSinceLastMeal = starvationPeriod;
		timeSinceLastMeal = startTimeSinceLastMeal;
	}

	public void lookToProliferate() {
		Cell[] curCellList = curWorld.getCellList();
		ArrayList<Cell> adjacentCells = findAdjacentCells(curCell, curCellList);
		int nearbyNeighbourCells = 0;
		int nearbyFoodCells = 0;
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();

		// Set Conditions
		for (Cell c : adjacentCells) {
			if (c.getResident() == null) {
				emptyCells.add(c);
			} else if (c.getResident().getClass() == this.getClass() && c.getResident().spawnedThisTurn == false) {
				nearbyNeighbourCells++;
			} else if (checkIfFood(c.getResident())) {
				nearbyFoodCells++;
			}
		}

		// See if the rules set for this lifeform allow for proliferation
		if (neighbourCellsRule.getResponse(nearbyNeighbourCells, nearbyNeighbourCellsNeeded)
				&& emptyCellsRule.getResponse(emptyCells.size(), nearbyEmptyCellsNeeded)
				&& foodCellsRule.getResponse(nearbyFoodCells, nearbyFoodCellsNeeded)) {
			System.out.println(this + ": Check Passed With...\n" + nearbyNeighbourCells + neighbourCellsRule.toString() + nearbyNeighbourCellsNeeded + " Neighbour Cells\n"
					+ emptyCells.size() + emptyCellsRule.toString() + nearbyEmptyCellsNeeded + " Empty Cells\n"
					+ nearbyFoodCells + foodCellsRule.toString() + nearbyFoodCellsNeeded + " Food Cells\n");
			proliferate(emptyCells);
		}
		acted = true;
	}

	private void proliferate(ArrayList<Cell> emptyCells) {
		int rand = RandomGenerator.nextNumber(emptyCells.size());
		Cell tarCell = emptyCells.get(rand);

		Lifeform p = construct(tarCell);
		System.out.println(p + " Has Been Born\n");
		tarCell.setResident(p);
		curWorld.getLifeformList().add(p);
	}

	private boolean starve() {
		if (timeSinceLastMeal == 0) {
			System.out.println(this + " Has Died\n");
			curWorld.getLifeformList().remove(this);
			curCell.setResident(null);
			return true;
		} else {
			timeSinceLastMeal--;

			return false;
		}
	}

	private void feed(Lifeform l) {
		System.out.println(this + " Ate " + l + "\n");
		timeSinceLastMeal = startTimeSinceLastMeal;
		curWorld.getLifeformList().remove(l);
	}

	public boolean move(int attempts) {
		Cell[] curCellList = curWorld.getCellList();
		ArrayList<Cell> c = null;
		
		if (Main.TM == TileMode.Square) {
			c = findAdjacentCellsCardinal(curCell, curCellList);	
		} else if (Main.TM == TileMode.Hex) {
			c = findAdjacentCellsHex(curCell, curCellList);
		}
		try {
			int r = RandomGenerator.nextNumber(c.size());	

			Lifeform l = c.get(r).getResident();
			if (!checkMove(c.get(r))) {
				return false;
			}
			if (checkIfFood(l)) {
				feed(l);
			}
			
			moveToCell(c.get(r));
			
			if (this instanceof Hungry) {
				starve();
			}
		} catch (Exception e) {
		}
		
		return false;
	}
}
