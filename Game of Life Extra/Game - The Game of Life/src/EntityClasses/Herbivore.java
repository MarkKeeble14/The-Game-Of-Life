package EntityClasses;

import java.util.List;

import Cell.Cell;
import Cell.Square;
import Conditional.RuleEqualTo;
import Conditional.RuleGreaterThanEqualTo;
import Entities.Entity;
import Entities.Lifeform;
import Entities.SophisticatedLifeform;
import EntityInterfaces.*;
import EntityInterfaces.Hungry;
import RandomGenerator.RandomGenerator;

public class Herbivore extends SophisticatedLifeform implements Hungry, CarnivoreEdible, OmnivoreEdible {
	public Herbivore (Cell c, int range, int plantCellsNeeded, int emptyCellsNeeded, int foodCellsNeeded, int starvationPeriod) {
		super(c, range, plantCellsNeeded, emptyCellsNeeded, foodCellsNeeded, starvationPeriod);
		
		neighbourCellsRule = new RuleGreaterThanEqualTo();
		emptyCellsRule = new RuleGreaterThanEqualTo();
		foodCellsRule = new RuleGreaterThanEqualTo();
		
		
	}
	
	public void takeAction() {
		if (!acted) {
			lookToProliferate();
			move(5);
		}
	}

	public boolean checkIfFood(Lifeform l) {
		return l instanceof HerbivoreEdible && l.getClass() != this.getClass();
	}

	@Override
	public Lifeform construct(Cell c) {
		return new Herbivore(c, range, 
				nearbyNeighbourCellsNeeded, nearbyEmptyCellsNeeded, nearbyFoodCellsNeeded, startTimeSinceLastMeal);
	}
}
