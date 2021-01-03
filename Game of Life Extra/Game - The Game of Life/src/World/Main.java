package World;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Orientation;
import Game.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends Application {
	/** The Constant HEIGHT. */
	public static final int HEIGHT = 768;
	public static final int WIDTH = 768;
	
	private static final int MAX_NUM_OF_WORLDS = 10;
	private static final int MIN_NUM_OF_WORLDS = 1;
	
	private static final int MAX_WORLD_SIZE = 64;
	
	private static final int DEFAULT_PERLINE = 24;
	
	public static TileMode TM = TileMode.Hex;
	
	private Scanner scan = new Scanner(System.in);
	
	public static FlowPane fp = new FlowPane();
	public static Group root = new Group();
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Start.
	 *
	 * @param arg0 the arg 0
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage arg0) throws Exception {
	    arg0.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
			Game.updateTurn();
		});
	    
	    fp.setOrientation(Orientation.VERTICAL);
	    fp.setVgap(HEIGHT * 0.025);
	    fp.setHgap(HEIGHT * 0.025);
	    
	    System.out.println("Which Mode do you wish to Play?");
	    System.out.println("1) Default\n2) Single \n3) Stacked\n4) Comparison");
	    int choice = 1;
	    do {
	    	if (choice > 4 || choice < 1) {
	    		System.out.println("Sorry, please enter a valid choice.");
	    	}
	    	choice = scan.nextInt();
	    	if (choice == 0) {
	    		choice = -1;
	    	}
	    } while (choice > 4 || choice < 1);
	    
	    switch (choice) {
	    case 1:
	    	playInDefaultMode();
	    	break;
	    case 2: 
	    	playInSingleMode();
	 		break;
	    case 3:
	    	playInStackedMode();
	    	break;
	    case 4:
	    	playInComparisonMode();
	    	break;
	    }
 		
 		// JavaFX cleanup
	    final int appHeight = HEIGHT;
	    root.getChildren().add(fp);
	    Scene scene = new Scene(root, WIDTH, appHeight, Color.WHITE);
	    
	    arg0.setTitle("The Game of Life");
	    arg0.setScene(scene);
	    arg0.show();		
	}
	
	private int getNumOfWorlds() {
		System.out.println("How Many Worlds do you Want to create?");
    	int numworlds = scan.nextInt();	
    	if (numworlds > MAX_NUM_OF_WORLDS) {
    		System.out.println(numworlds + " is a big number! Lets try " + MAX_NUM_OF_WORLDS + " instead.");	
    		numworlds = MAX_NUM_OF_WORLDS;
    	}
    	if (numworlds < MIN_NUM_OF_WORLDS) {
    		System.out.println(numworlds + " is too small! Lets try " + MIN_NUM_OF_WORLDS + " instead.");	
    		numworlds = MIN_NUM_OF_WORLDS;
    	}
    	
    	return numworlds;
	}
	
	private int[] getPerLineForEachWorld(int numworlds) {
		int[] sizeofworlds = new int[MAX_NUM_OF_WORLDS];
	    int index = 0;
    	for (; index < numworlds; index++) {
    		System.out.println("How Many Tiles Per Line for World Number #" + (index + 1) + "?");
    		
    		sizeofworlds[index] = scan.nextInt();
    		if (sizeofworlds[index] > MAX_WORLD_SIZE) {
        		System.out.println(sizeofworlds[index] + " is Too Many! Defaulting to Maximum of " + MAX_WORLD_SIZE + ".");	
        		sizeofworlds[index] = MAX_WORLD_SIZE;
    		}
    		if (sizeofworlds[index] == 0) {
    			System.out.println(sizeofworlds[index] + " is Not ALLOWED! Defaulting to " + "8" + ".");	
        		sizeofworlds[index] = MAX_WORLD_SIZE;
    		}
    	}
    	return sizeofworlds;
	}
	
	private int getPerLine(int[] sizeofworlds, int index) {
			System.out.println("How Many Tiles Per Line for World Number #" + (index + 1) + "?" 
		+ "\\nNOTE: non-poweroftwo integers will result in incorrect formatting!");
			
			sizeofworlds[index] = scan.nextInt();
			if (sizeofworlds[index] > MAX_WORLD_SIZE) {
	    		System.out.println(sizeofworlds[index] + " is Too Many! Defaulting to Maximum of " + MAX_WORLD_SIZE + ".");	
	    		sizeofworlds[index] = MAX_WORLD_SIZE;
			}
			if (sizeofworlds[index] == 0) {
				System.out.println(sizeofworlds[index] + " is Not ALLOWED! Defaulting to " + "8" + ".");	
	    		sizeofworlds[index] = MAX_WORLD_SIZE;
			}
			return sizeofworlds[index];
	}
	
	private int getPerLine() {
		System.out.println("How Many Tiles Per Line?");
		int num = scan.nextInt();
		if (num > MAX_WORLD_SIZE) {
    		System.out.println(num + " is Too Many! Defaulting to Maximum of " + MAX_WORLD_SIZE + ".");	
    		num = MAX_WORLD_SIZE;
		}
		if (num == 0) {
			System.out.println(num + " is Not ALLOWED! Defaulting to " + "8" + ".");	
    		num = MAX_WORLD_SIZE;
		}
		return num;
	}
	
	private TileMode getTileMode() {
		System.out.println("Hex or Square Tiles?");
	    System.out.println("1) Square\n2) Hex");
	    int choice = 1;
	    do {
	    	if (choice > 2 || choice < 1) {
	    		System.out.println("Sorry, please enter a valid choice.");
	    	}
	    	choice = scan.nextInt();
	    	if (choice == 0) {
	    		choice = -1;
	    	}
	    } while (choice > 2 || choice < 1);
	    
	    if (choice == 1) {
	    	return TileMode.Square;
	    } else {
	    	return TileMode.Hex;
	    }
	}
	
	// Returns the width of the view.
	private void playInComparisonMode() {
		int numworlds = getNumOfWorlds();
	    int[] sizeofworlds = getPerLineForEachWorld(numworlds);
	    
	    int index = 0;
	    for (int i : sizeofworlds) {
	    	if (i != 0) {
	    		index++;
	    	}
	    }
	    
	    TM = getTileMode();
	    
	    for (int i = 0; i < index; i++) {
 			instantiateWorld(sizeofworlds[i], i, ((HEIGHT / sizeofworlds[i]) / numworlds));
 		}
	}
	
	private void playInStackedMode() {
		int numworlds = getNumOfWorlds();
		int perline = getPerLine();
		
	    TM = getTileMode();
	    
 		for (int i = 0; i < numworlds; i++) {
 			instantiateWorld(perline, i, ((HEIGHT / perline) / numworlds));
 		}
	}
	
	private void playInSingleMode() {
		int numworlds = 1;
		int perline = getPerLine();
	    TM = getTileMode();
	    
 		for (int i = 0; i < numworlds; i++) {
 			instantiateWorld(perline, i, ((HEIGHT / perline) / numworlds));
 		}
	}
	
	private void playInDefaultMode() {
	    TM = getTileMode();
	    
		instantiateWorld(DEFAULT_PERLINE, 0, WIDTH / DEFAULT_PERLINE);
	}
	
	private void instantiateWorld(int perline, int level, int sizemod) {
		sizemod *= 0.95;
		System.out.println(level);
		World world = null;
		if (TM == TileMode.Square) {
			world = new WorldOfSquares(perline, perline, sizemod, level);
		} else if (TM == TileMode.Hex) {
			world = new WorldOfHexs(perline, perline, sizemod, level);
		}
		fp.getChildren().add(world.pane);
		System.out.println(world.toString() + " INSTANTIATED.");
		System.out.println(world.getLevel());
	}
}
