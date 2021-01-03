package Game;

import java.util.ArrayList;

import Entities.Lifeform;
import EntityClasses.*;
import World.*;
import javafx.scene.paint.Color;

public class Game {
	
	/** List of Worlds */
	public static ArrayList<World> worldList = new ArrayList<World>();
	
	public static void updateTurn() {
		for (World w : getWorldList()) {
			w.advanceTurn();
		}
	}

	/**
	 * Gets the board list.
	 *
	 * @return the board list
	 */
	public static ArrayList<World> getWorldList() {
		return worldList;
	}
}
