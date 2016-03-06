# car_game
A car racing game based on Slick with a simple physics engine and three different AI players

# What is this game?
This game is a kart racing game where one player races against computer-controlled enemies to be the first one to the finish. The player controls Donkey, who is skilled at picking up various items found on the road to use or throw at his opponents. The goal of the game is to reach the finish line first, by any means necessary. 
In this game, the “world” is a two-dimensional grid of tiles. While the map is designed as a narrow track, the player is able to drive freely around the world (but of course, the player cannot drive on trees, mountains, water, and other types of terrain)
![Alt text](https://github.com/lhCheung1991/car_game/blob/master/image/acting.png?raw=true "Optional Title")

# Game design
Acceleration: All of the karts accelerate forwards or backwards at a rate of 0.0005 px/ms, and rotate at a rate of 0.004 radians per millisecond. Also, all karts are automatically decelerated by friction, which is dependent on the type of terrain underneath the kart.

Players: The player (Donkey). Controlled by you; able to pick up and use items. There are three enemy karts, determined to beat you in the race. Each has a dierent racing strategy. Enemy karts follow the same basic rules of movement and physics as the player, except that they cannot pick up or use items.

Collisions: When a kart A is about to move, the game checks to see if the kart is about to move within 40 pixels of another kart, B, and if so, a collision occurs. 

Items and projectiles: Oil can – Creates an oil slick behind the player. The oil slick is placed 40 pixels away from the player, in the opposite direction to that which the player is facing. Tomato – Can be thrown at enemies in front of the player. A tomato projectile is created 40 pixels away from the player, in the direction the player is facing. The tomato projectile is given a fixed velocity of 1.7px/ms in the direction the player is facing. Boost – Activates a rocket afterburner that increases the player’s acceleration for a short time. The boost lasts for 3 seconds.

# Slick two-dimensional game engine
Slick2D is an easy to use set of tools and utilites wrapped around LWJGL OpenGL bindings to make 2D Java game development easier.
I like the tile map system of Slick, it is very easy to manipulate. 

Offical Site of Slick: [http://slick.ninjacave.com/](http://slick.ninjacave.com/)
![Alt text](https://github.com/lhCheung1991/car_game/blob/master/image/slick.png?raw=true "Optional Title")

# Run this project
I think this game is not a bad study material for the new game programmer, it will help you comprehend all the fundamental concepts of game developing. If you try to run this project, you get to notice the following points: 1. Remember to import the external jar file; 2. Choose the appropriate native library based on your Operating System.
![Alt text](https://github.com/lhCheung1991/car_game/blob/master/image/config.png?raw=true "Optional Title")
