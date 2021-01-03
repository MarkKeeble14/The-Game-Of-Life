package EntityClasses;

import Cell.Cell;
import Cell.Square;
import Conditional.RuleEqualTo;
import Conditional.RuleGreaterThanEqualTo;
import Entities.Entity;
import Entities.Lifeform;
import Entities.SophisticatedLifeform;
import EntityInterfaces.*;

public class Omnivore extends SophisticatedLifeform implements Hungry, CarnivoreEdible {
	public Omnivore(Cell c, int range, int plantCellsNeeded, int emptyCellsNeeded, int foodCellsNeeded, int starvationPeriod) {
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
	
	public Lifeform construct(Cell c) {
		return new Omnivore(c, range, 
				nearbyNeighbourCellsNeeded, nearbyEmptyCellsNeeded, nearbyFoodCellsNeeded, startTimeSinceLastMeal);
	}
	
	public boolean checkIfFood(Lifeform l) {
		return l instanceof OmnivoreEdible && l.getClass() != this.getClass();
	}
}
