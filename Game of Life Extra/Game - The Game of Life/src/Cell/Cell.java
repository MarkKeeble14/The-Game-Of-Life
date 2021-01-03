package Cell;

import Entities.Lifeform;
import World.World;
import javafx.scene.paint.Color;

public interface Cell {
	public void generateLifeform();
	public void updateCellColor();
	public int getRow();
	public int getCol();
	public int getLevel();
	public World getParWorld();
	public void setResident(Lifeform lifeform);
	public Lifeform getResident();
	public void evictResident();
	public void setColor(Color color);
}
