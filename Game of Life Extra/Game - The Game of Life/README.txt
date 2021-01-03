The Game of Life
The Game of Life is a simple simulation based game, consisting of different variants of entities which interact with
the world around them in unique ways. The world consists of many individual cells, in a grid based environment.

Getting Started
When you run the program, before the JavaFX window appears, you'll get asked through the console which mode you wish to play.
The three options are:
	- Stacked: 		This mode will allow you to choose the amount of Worlds you want to generate, as well as a single
					value for the number of lines per row/col that will be applied to every World. These Worlds currently
					exist independently from one another, but each one is generated with a separate level index. That means 
					should we have to make Lifeform's travel in between levels, it will be quick and easy.		
					
	- Comparison: 	This mode is similar to Stacked as it allows you to choose the amount of Worlds to generate. However,
					in addition you are also able to specify the number of lines per row/col for each World individually.
					This means that you can have for example, 4 worlds, with 64, 256, 32, and 64 Cells total, respectively.
					In the future, I may also add the capability to have different per line values for rows and columns.
					The constructor already allows for taking in two different values, I just haven't allowed the user that 
					option yet.
					
	- Default: 		This is the standard way to play. It consists of only one square World with 16 Cells per row/column.

Flexibility
Lifeform's
	- At the top of my hierarchy is an interface Entity, which contains method takeAction. This Entity interface exists because 
	  it means we can have entities such as simple stumps or walls which won't take actions, but do exist.
	  
	  Then, we have abstract class Lifeform which implements Entity. The Lifeform abstract class contains all the required 
	  members a Lifeform must have, such as acted, curWorld, curCell, etc. The Lifeform abstract class also contains method 
	  takeAction(), as well as some other methods which are used to alter the Lifeform's position such as an overloaded 
	  moveToCell(), findCell(), and updatePositionInWorld().
	  
	  I then have the actual classes, Herbivore and Plant, which extend from Lifeform. These classes set have a constructor which
	  takes in arguments to set a few of the variables the respective Lifeform has.
	  
	- I've been using Unity recently and I've drawn the connection that interfaces can be used similarly to tags. By checking that
	  the object is an instanceof an interface, you control/segregate things really easily. I've created a few separate interfaces
	  to use as tags, in case in the future we need to do anything with any similar concepts.
		- WaterType, Growable, and Procreater.
	  
	  An example of how I use them is my interface HerbivoreEdible, which my Plant class implements. My Herbivore's do not check for
	  their meal being specifically a plant, instead it checks if the Entity returned by moveToCell() is an instance of 
	  HerbivoreEdible. If so, they have eaten.
	  
	  I also have interface MovementBlocker, which my Herbivore's implement. All this interface is used for is to disallow
	  any Lifeform's from moving onto the same space as one another. In the future if we were to implement obstacles
	  such as a wall or maybe a tree stump, I can simply have those entities implement MovementBlocker and ta-da! No
	  Lifeform will be allowed to move onto that cell.

Worlds
	- I've made an effort to make sure that the Worlds size doesn't matter. The different gamemode's are possible because of 
	  the flexibility of my World constructor, allowing for a ton of configuration from within the Main class. 
	- Each World is also given a Z-Level, which means in the future, 3D related tasks should be really straightforward
	  to implement. 
	- Each World has it's own CellList and LifeformList, which are used to trigger the lifeform's on that specific World.
	  This means that I can activate the different Worlds independently from one-another. So if for example we had to move the 
	  Mouse_Clicked event from only the window to the individual World that was clicked on, it would be trivial to do.
	  Theoretically, I could also activate only certain Lifeform's as well by searching the list.
	
Cells
	- I've made my Cell class abstract, and created two sub-classes, SpawnableCell, and NonSpawnableCell. I've put the method
	  which generates a lifefor'm into the Cell constructor, and checked whether the current tile is an instanceof SpawnableCell
	  before allowing the method to be called. Maybe in the future we are supposed to inhibit the number of Lifeforms spawned. 
	  This makes it so that would be no problem to implement.
	
Movement 
	- I've gone ahead and created an override for my movement method. I have one version that takes in parameters for row, col, 
	  and level, and this is the version that is used to simply move the Lifeform in a direction by an amount. Say 1 left or 1 down.
	  The override just takes in a Cell as a parameter and will move the resident directly to the specified Cell. The Cell could be 
	  diagonal from the current position, straight down, or it could be completely unconnected, it doesn't matter. Maybe in the future,
	  we add a teleporting plant? This override means this I am able to do that with no difficulty.
	
Authors
	- Mark Keeble