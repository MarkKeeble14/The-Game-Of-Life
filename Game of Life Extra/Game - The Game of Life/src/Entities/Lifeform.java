package Entities;

import World.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import Cell.Cell;
import EntityClasses.Herbivore;
import EntityInterfaces.HerbivoreEdible;
import Game.*;

public abstract class Lifeform implements Entity {
	public boolean acted = true;
	public boolean spawnedThisTurn = true;
	public World curWorld;
	public Cell curCell;
	public int col;
	public int row;
	public int level;
	public int range;
	
	public abstract void takeAction();
	public abstract boolean checkIfFood(Lifeform l);
	
	public ArrayList<Cell> findAdjacentCells(Cell curCell2, Cell[] curCellList) {
		ArrayList<Cell> adjacentCells = new ArrayList<Cell>();
		for (Cell c : curCellList) {
			if (Math.abs(curCell2.getRow() - c.getRow()) <= range && Math.abs(curCell2.getCol() - c.getCol()) <= range) {
				if (c != curCell2) {					
					adjacentCells.add(c);
				}
			}
		}
		return adjacentCells;
	}
	
	public ArrayList<Cell> findAdjacentCellsCardinal(Cell curCell2, Cell[] curCellList) {
		ArrayList<Cell> adjacentCells = new ArrayList<Cell>();
		for (Cell c : curCellList) {
			if (Math.abs(curCell2.getRow() - c.getRow()) <= range && Math.abs(curCell2.getCol() - c.getCol()) <= range) {
				if ((c.getRow() == curCell2.getRow()) || c.getCol() == curCell2.getCol()) {
					if (c != curCell2) {					
						adjacentCells.add(c);
						c.setColor(Color.BLUE);
					}	
				}
			}
		}
		return adjacentCells;
	}
	
	public ArrayList<Cell> findAdjacentCellsHex(Cell curCell2, Cell[] curCellList) {
		ArrayList<Cell> adjacentCells = new ArrayList<Cell>();
		int run = 0;
		for (Cell c : curCellList) {
			if (Math.abs(curCell2.getRow() - c.getRow()) <= range && Math.abs(curCell2.getCol() - c.getCol()) <= range) {
				if (c != curCell2) {	
					if (curCell2.getRow() % 2 == 0) {
						if (curCell2.getCol() % 2 == 0) {
							if (run != 5 && run != 7) {
								adjacentCells.add(c);
							}	 	
						} else {
							if (run != 0 && run != 2) {
								adjacentCells.add(c);	
							}
						}
					} else if (curCell2.getRow() % 2 == 1) {
						if (curCell2.getCol() % 2 == 1) {
							if (run != 0 && run != 2) {
								adjacentCells.add(c);
							}		
						} else {
							if (run != 5 && run != 7) {
								adjacentCells.add(c);
							}	
						}
					}
					run++;
				}
			}
		}
		return adjacentCells;
	}
	
	public Cell findCell(int row, int col, int level) {
		World worldToSearch = Game.getWorldList().get(level);
		Cell[] cellListToSearch = worldToSearch.getCellList();
		
		for (Cell c : cellListToSearch) {
			if (c.getRow() == row && c.getCol() == col) {
				return c;
			}
		}
		
		return null;
	}
	
	public void updatePositionInWorld(Cell c) {
		curCell = c;
		row = c.getRow();
		col = c.getCol();
		level = c.getLevel();
		curWorld = c.getParWorld();
		
		c.setResident(this);
	}
	
	public boolean checkMove(Cell c) {
		if (c.getResident() == null) {
			return true;
		} else {
			if (checkIfFood(c.getResident())) {
				return true;
			}
			return false;
		}
	}
	
	public boolean moveToCell(Cell cell) {
		curCell.evictResident();
		updatePositionInWorld(cell);
		acted = true;
		
		return true;
	}
	
	public boolean moveToCell(int row, int col, int level) {
		Cell cellToMoveTo = findCell(curCell.getRow() + row, curCell.getCol() + col, curCell.getLevel() + level);
		
		if (cellToMoveTo == null) {
			return false;
		}
		// Swap
		curCell.evictResident();
		updatePositionInWorld(cellToMoveTo);
		
		acted = true;
		return true;
	}
	
	
}
