# Red Blue Nim Game - README file

## Programming Language Used
The programming language used for this task is JAVA. The version used for this project is Java 20.

## Code Structure
The code is structured into 1 file:
*red_blue_nim.java:* This is the main program that should be run from the command line with arguments for the game.

## How to Run the Code
To run the code, follow the steps below:
-- Open the command line or terminal and navigate to the directory containing the red_blue_nim..java file. 
-- Run the command:

javac red_blue_nim.java 

#This will compile the code
# then type

java red_blue_nim [num_red] [num_blue] [first_player] [depth]   
# test command - java red_blue_nim 12 11 computer 5
where:
num_red is the number of red marbles in the game.
num_blue is the number of blue marbles in the game.
first_player is the player that starts the game. This can be either 'computer' or 'human'.
depth is an optional argument that determines the depth of the minmax search algorithm. If not specified, the algorithm will use its default value.

Follow the prompts to play the game.

## ACS Omega
This code has not been tested on ACS Omega.

## Resources used
- https://www.hackerearth.com/blog/developers/minimax-algorithm-alpha-beta-pruning/
- https://www.cs.cornell.edu/courses/cs312/2004fa/hw/ps4/ps4.htm
- https://realpython.com/python-minimax-nim/