=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

==============
=: Overview :=
==============

In this project, I've created a 2D horror game based on The Backrooms, an urban legend which describes an endless maze
of empty offices. The objective of the game is to escape the Backrooms by locating the escape ladder before the time
runs out. 

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays. This implements the actual map of the "backrooms". Using integers in this 2D, I can easily map each
     number to a corresponding type of tile that's placed on the map. This makes it easy to iterate through the map and
     display the correct tiles where they need to be.

  2. File I/O. This implements the "save game" and "load game" feature. When exiting out of the level or game, it
     automatically saves the game state to a file called "game_file.txt". When you're on the menu, you can select
     "load game" and it will return you to the saved state. File I/O makes it easy to read from and write onto text
     files that the game can store/retrieve information from.

     CHANGES: Originally, I was going to use File I/O to keep track of the different difficulties of the game, but I
     changed it because I thought that pausing and loading the game was a more necessary feature.

  3. Inheritance and Subtyping. One instance of Inheritance and subtyping is for my entities. In my entity package of
     the source folder, I create a parent class called "Entity". Then, I also have two classes in the same package,
     "Player", and "ShadowMan" that extend "Entity". "Player" is the class for the character that the user controls.
     The user can use the arrow keys to control the player, and the player has unique methods that define its
     abilities, such as the "pickUpObject()" method which lets the player interact with objects on the screen. The
     "ShadowMan" class is for an NPC, which cannot interact with objects, but has its own unique methods "setAction()",
     and "BFSRecursive()", which set the movements of the NPC. Inheritance and Subtyping was especially useful
     in this case because it allowed me to create entities that are built upon a single Entity parent class to build a
     new implementation while maintaining the same core behaviors like checking for collisions and drawing the entity.
     This reduced the repetition of code.

     CHANGES: Originally, I was going to implement DFS as an Advanced Topic, which allowed the player to see a path to
     the most optimal route out of the maze. However, this was very challenging and I couldn't properly implement it
     the way I wanted to.

  4. Recursion. In my game, I wanted the "shadowmen" to chase the character to scare the player (even though they are
  harmless). To do this, I implemented Recursion in BFS, in which at every step, the closest path from the entity to
  the player is found, and the "shadowmen" can take a step in the direction of the most optimal path. I chose to use
  Recursion over Iteration because it turned out to much easier to implement for me, since I only needed to define the
  base case and recursive case. The base case is when the frontier (next layer of the bfs graph) is empty, and the
  recursive case is when it's non-empty. This makes it simpler and shorter than iteration.

    CHANGES: Originally, I was going to implement testing as my fourth concept. However, I just thought that it would
    be a lot more fun to create a more dynamic game that included BFS to move the "shadowmen".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  DataStorage: keeps track of what values should be stored in the save file
  SaveLoad: contains methods that allows the user to save and load game states from file
  Entity: Parent class for all entites
  Player: Sub class of entity that represents the controllable player
  ShadowMan: Sub class of entity that represents the harmless "monsters" that can follow you
  Lighting: Creates the vingette overlay of the game
  ScreenSetter: Contains method that sets the environment, including the lighting
  EndBoy: Image of the boy for the End Screen
  Instructions: Image of the Instructions for the instructions screen
  LoseScreen: Image of the Lose Screen
  MenuScreen: Image of the Menu for the Menu Screen
  WinScreen: Image of the Win Screen
  CollisionChecker: Has methods that checks player collisions with tiles and objects
  GamePanel: Controls the main panel of the game, calling the run, update, and paintComponent methods.
  KeyHandler: Keeps track of which keys are pressed on what screens
  ObjectPlacer: Sets objects and NPCs on the screen when the game starts
  UI: Controls the User Interface
  Object: Parent class of all objects
  Candle: Sub class of object, creating a candle that lights up your screen
  Ladder: Sub class of object, creating a ladder that lets the player win when reached
  Game: Contains main method.
  Tile: Parent class for tiles.
  TileManager: Loads and draws the tiles onto a map from a 2D array.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  The single most significant stumbling block was implementing away to control the “shadowmen” in the game to follow
  the player. I had no prior knowledge of BFS and DFS, so a lot of research had to be done to understand these topics.
  At first, I tried implementing both iterative DFS and BFS. However, it never turned out quite right.  I then tried
  implementing recursive DFS, but that ended up being extremely challenging for me. In the end, I gave BFS a shot and
  stuck with it instead. Even though the results aren’t quite as smooth as I wanted them to be, it was the best I
  could do with the time I had.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  I think that I separate my packages pretty neatly and organize my classes in a way that makes it easier for people
  reading the code. It definitely helped looking at other examples of java games so I could base my structure off of
  them to make my code more organized. However, one thing I think I could’ve have worked on was private state
  encapsulation. I think at most times, I may have just left most variables public just for the sake of being able to
  access them later if I needed to.
