package EntityClasses;

import java.util.ArrayList;

import Cell.Cell;
import Cell.Square;
import Conditional.*;
import Entities.Entity;
import Entities.Lifeform;
import Entities.SophisticatedLifeform;
import EntityInterfaces.*;
import RandomGenerator.RandomGenerator;
import javafx.scene.paint.Color;

public class Plant extends SophisticatedLifeform implements HerbivoreEdible, OmnivoreEdible {
	public Plant (Cell c, int range, int plantCellsNeeded, int emptyCellsNeeded, int foodCellsNeeded, int starvationPeriod) {
		super(c, range, plantCellsNeeded, emptyCellsNeeded, foodCellsNeeded, starvationPeriod);
		
		neighbourCellsRule = new RuleGreaterThanEqualTo();
		emptyCellsRule = new RuleGreaterThanEqualTo();
		foodCellsRule = new RuleEqualTo();
	}

	public void takeAction() {
		if (!acted) {
			lookToProliferate();
		}
	}

	public Lifeform construct(Cell c) {
		return new Plant(c, range, 
				nearbyNeighbourCellsNeeded, nearbyEmptyCellsNeeded, nearbyFoodCellsNeeded, -1);
	}
	
	public boolean checkIfFood(Lifeform l) {
		return false;
	}
}
