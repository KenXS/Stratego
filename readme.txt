Instructions for playing Ken's Stratego game

To avoid setting up all the red players, you can start the program with this command. 
Each number is the player rank  (1=Marshall,...,11=Flag) and the players are assigned 
the back row to front row in sequence.

"java Stratego -Red 3,6,10,6,8,8,5,8,5,8,9,10,11,10,7,1,5,8,6,4,0,4,10,7,2,4,7,7,10,6,8,3,7,8,4,5,3,8,2,10"

******Setup
Select a player from the red tray at the left with the left mouse button.
Then left click the board spot you want the player to be placed.
If you want to place a player of the same rank, you dont need to reclick the red tray.
After each placement, the count of remaining players of the selected rank decreases by one.
When the count goes to zero, the red tray button is deselected and you must select a 
different player. Continue in this manner until all players are placed on the board.
To put a player back on the tray, right click the player. The count increases by one.

When all players are placed on the board, the START button is activated andyou can click
it to start playing.

Each time the game starts, one of 20 strategies is randomly selected for the blue side.
You can reset it by clicking RESET BLUE. Cheat and view the blue players by clicking
the EXPOSE BLUE button - the strategies title is displayed in the tool bar.

*******Game play
To move a red player, drag it from its start spot to a destination adjacent to it 
according to the standard rules. As soon as the red move is made, Blue moves 
automatically. As this occurs, a blue arrow is displayed to better highlight the move
which occurs very fast.

Be sure to have the sound volume turned on to aid in play.

The end of the game occurs when either team has no moveable players, or a flag 
is captured.

*******Beta Release Notes
1) The intelligence of Blue is limited in this initial version. But it will be improved
soon. 
2) As captured players are moved horizontally off the board, the image on occassion
is not visible for some reason. To help in announcing each play/attack result, the 
red & blue tray text is updated with "->" indication of the most recent play result. 
3) A help screen with this text will be added in the next release.
4) The game window can be resized but the board does not repaint properly currently - 
some boxes are lost. It is best to keep the window the same size as it is initially 
appears. 
5) Development of the game has been achieved on a single PC and no attempt has been 
made to test its function on other hardware.
6) The ability to save a game to a named file for subsequent reload will be added.
7) The ability to expose blue might be removed. For now it is required for proving
correctness.
8) On the final play of the game, a window appears with a summary asking for quit or
new game. This always quits currently. To play a new game, the program must be
restarted. This will be corrected. 
9) The rules of the game might be added for quick help. Havent decided yet.
10) Game startup should be converted to a simple .exe file so that Java is not
needed.    

******************************************************
* Thanks for your interest and enjoy!                *
* Ken Shapiro                                        *
******************************************************
