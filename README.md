# Personal Project

## Rock Paper Scissors
___
:fist: :raised_hand_with_fingers_splayed: :v:
___
so this is going to be a game of some kind. <br>
a rock paper scissors kind of game you could say.

how i imagine it is a 2-player 2D game. you got rock, paper and scissors that you can interchange throughout the game
and the goal is to just touch the other player and if youre on top in the triangle then you win. <br>
Each character will have its own upside and downside like rock being the faster or some other traits but I haven't
completely figured out the balance yet.<br>
i imagine who ever wants to play this can use it as i want it to have pretty simple controls with WASD and three other
buttons to control what you want to switch to.

this project interests me because i like games and especially to make something i can be creative in the design aspect
of it. 
___

## User Stories

>- As a user, I want to be able to add my character to the game.
>- As a user, I want to be able to view the score.
>- As a user, I want to be able to pause.
>- As a user, I want to be able to move my character.

>- As a user, I want to be able to the score of the game.
>- As a user, I want to be able to reload the score of the game.

___

## Instructions for Grader

>- you can move player 1 with W A S D
>- you can move player 2 with the arrow keys
>- you can change the images by <br>
>player 1: ROCK [C] PAPER [V] SCISSORS [B] <br>
>player 2: ROCK [<] PAPER [>] SCISSORS [?]
>- you can save by going into menu and clicking save
>- you can load by going into menu and clicking load


___

## Phase 4: Task 2

I choose to test and design a class that is robust <br>
I changed 4 methods in the Player class to throw exceptions <br>
1. the constructor of the Player now throws the NegativePositionException if the x and y are negative
2. the same exception is also thrown in the setXpos and setYpos methods
3. the setScore method throws a NegativeScoreException when the score is less than 0

___

## Phase 4: Task 3

1. i noticed that in RpsGame the setUp method when initiating new players uses the same numbers for the initial
positions as the initialState method that resets everything. So i refactored it as a private variable so that i dont
have to change both if i decide to change its starting positions.
2. i have also done something similar to the movePlayer1 & 2 and stopPlayer1 & 2. I just consolidated the variables so
that i only have to change one private variable instead of changing everything in the methods