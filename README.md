# car_game
A car racing game based on Slick with a simple physics engine and three different AI players

# What is this game?
This game is a kart racing game where one player races against computer-controlled enemies to be the first one to the finish. The player controls Donkey, who is skilled at picking up various items found on the road to use or throw at his opponents. The goal of the game is to reach the finish line first, by any means necessary. 
In this game, the “world” is a two-dimensional grid of tiles. While the map is designed as a narrow track, the player is able to drive freely around the world (but of course, the player cannot drive on trees, mountains, water, and other types of terrain)
![Alt text](https://github.com/lhCheung1991/car_game/blob/master/image/acting.png?raw=true "Optional Title")

# Game design
Acceleration: All of the karts accelerate forwards or backwards at a rate of 0.0005 px/ms, and rotate at a rate of 0.004 radians per millisecond, except in special circumstances explained later. Also, all karts are automatically decelerated by friction, which is dependent on the type of terrain underneath the kart.

Players: The player (Donkey). Controlled by you; able to pick up and use items. There are three enemy karts, determined to beat you in the race. Each has a di↵erent racing strategy. Enemy karts follow the same basic rules of movement and physics as the player, except that they cannot pick up or use items.
