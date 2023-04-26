=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: dailingw
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays
    The board the player plays on is a 4x4 grid of Tiles. This is where all of the game's current
    existing tiles is stored and drawn from. Key presses and buttons may or may not alter this 2D array
    but if the game state changes, the updated array is repainted.

  2. File I/O
    The player has a save button that writes the game state to a file, so when the player closes the game,
    their progress can return when they reopen the game later if they choose. Files don't clear when
    the game stops or is reset unlike the game board or linked lists I use, so this is the optimal way
    to store data in this case.

  3. JUnit Testable Component
    I implemented tests for collapsing tiles into adjacent tiles when shifting. I test for spawning
    new tiles in the empty spaces. I also tested edge cases like when the player tries
    shifting the tiles some direction but no tiles can move so no new tiles spawn in or when a player
    tries to undo after they reset (nothing should happen). Finally, I tested the save and load game
    function and confirmed data was being written and read from the save.txt file.

  4. Collections
    I use a linked list of 2D arrays for keeping track of each time the grid changes. The undo button
    polls from the end of the list to redo their move and restore the game state. Another linked list
    tracks the player's score too, so their points are restored to the appropriate values. I also use a linked list
    when picking a random empty tile to spawn a new tile in. The number of empty tiles is variable so a list
    was an appropriate data structure to keep track of their coordinates.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
    I have 2 main classes: Tile and Grid. Tile is the class that models an actual Tile in the 2048 game.
    It keeps track of a tile's position, value, and color. The position corresponds to its index on the
    2D array and the color is determined by its value.

    Grid contains the 2D Tile array and all the game logic that surrounds it. The contructor has a KeyListener
    that detects what arrow key is pressed and calls a shift function to move the tiles accordingly, as well as
    spawn in new tiles if the game state changed. It also tracks your moves by placing copies of the game board
    into a linked list if you want to undo a move, and it can save your game to a file if you want to come back later.
    Labels are updated accordingly as well.

    Run2048 is the run file. All of your labels and buttons are set here (How to play, Undo, Reset, Save, Load,
    Score, Top Score, Status). It creates a new empty game board and visualizes the GUI for the player.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
    Keeping track of the tile behavior was difficult, as there are very specific rules you have to account for.
    For example, collapsing a line [2 2 4 0] left gives you [4 4 0 0], not [8 0 0 0] or when collapsing [2 2 2 0],
    collapsing left gives [4 2 0 0] not [2 4 0 0]. Sometimes, pressing buttons would cause the game to freeze
    too, and I eventually realized I had to use requestFocusInWindow().

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
    Functionality is adequately separated between classes. I only have one type of game object (Tiles) and the
    game rules require the Tiles to interact with each other, so game actions need to be done in the Grid class
    where we have access to the game board. Variables are encapsulated and properly accessed/modified through
    helper functions.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
