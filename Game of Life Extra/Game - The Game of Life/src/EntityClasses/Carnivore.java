package EntityClasses;

import Cell.Cell;
import Cell.Square;
import Conditional.RuleEqualTo;
import Conditional.RuleGreaterThanEqualTo;
import Entities.Entity;
import Entities.Lifeform;
import Entities.SophisticatedLifeform;
import EntityInterfaces.*;
import EntityInterfaces.Hungry;

public class Carnivore extends SophisticatedLifeform implements Hungry, OmnivoreEdible {
	public Carnivore(Cell c, int range, int plantCellsNeeded, int emptyCellsNeeded, int foodCellsNeeded, int starvationPeriod) {
		super(c, range, plantCellsNeeded, emptyCellsNeeded, foodCellsNeeded, starvationPeriod);
		
		neighbourCellsRule = new RuleGreaterThanEqualTo();
		emptyCellsRule = new RuleGreaterThanEqualTo();
		foodCellsRule = new RuleEqualTo();
	}

	public void takeAction() {
		if (!acted) {
			lookToProliferate();
			move(5);
		}
	}
	
	public boolean checkIfFood(Lifeform l) {
		return l instanceof CarnivoreEdible && l.getClass() != this.getClass();
	}

	@Override
	public Lifeform construct(Cell c) {
		return new Carnivore(c, range, 
				nearbyNeighbourCellsNeeded, nearbyEmptyCellsNeeded, nearbyFoodCellsNeeded, startTimeSinceLastMeal);
	}
}
