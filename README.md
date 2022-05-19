# This program was created as a semester project of the PJV

## (CTU - Summer semester 2022)

### Authors: Dmitry Rastvorov
#### Project: GAME ENGINE (Zombie Survival)
-- -- --
#### The project was created with the JDK 18
#### Used tools: IntelliJ IDEA, draw.io

-- -- -- --
### This is main UML diagram of game project ([Powered by draw.io](https://app.diagrams.net)):
![[Image description]](https://gitlab.fel.cvut.cz/B212_B0B36PJV/rastvdmy/-/wikis/uploads/3e15591f91456f01ced6e6fca65a8269/Zombie_survival_drawio.png)
-- -- -- --
### Also we can see more readable version of UML diagram ([Powered by IntelliJ Ultimate](https://www.jetbrains.com/help/idea/class-diagram.html)):
![[Image description]](https://gitlab.fel.cvut.cz/B212_B0B36PJV/rastvdmy/-/wikis/uploads/fb383a0ccde5d0344be005b33b8456a8/Zombie_survival_intellij.png)
-- -- -- --

### Manual
-- -- --
#### **Gameplay:**

● The game takes place in a post-apocalyptic future. COVID-19 reached its development and began to turn people into zombies. Our task is to survive in this difficult world. The game is an RPG genre with survival elements. By killing zombies, we will receive useful resources for us (money that can be exchanged for weapons, as well as medicine that will allow us to replenish our units of health).
-- -- -- 
#### **The end of the game:**

● The game ends after the player dies. Therefore, the very task of the player is not to allow this to happen!
-- -- -- 
#### **Survival Conditions:** 

● After killing zombies, the player will receive money, which he can spend to buy health points, weapons, food and water.
The player needs to restore health points, food and water in order to survive.
Also during the gameplay the zombies themselves become stronger and more aggressive. 
-- -- -- 
#### **Character control:**

##### ● Movement:

To move the player on the map You will be offered 2 options:
    
    I) Standard WASD:
    
    W - forward
    
    A - left
    
    S - down
    
    D - right

    A + W - upper left corner

    A + S - bottom left corner

    D + W - upper right corner

    D + S - bottom right corner

    II) Arrows on the keyboard:
    
    The character's movements depend on the direction of the arrows on the keyboard.

    Left + upper arrow - upper left corner

    Left + bottom arrow - bottom left corner

    Right + upper arrow - upper right corner

    Right + bottom arrow - bottom right corner

##### ● Attack:
    
    To attack, point Your mouse at the zombie and use the left mouse button.

##### ● Store:
    
    To open the store, press M.

##### ● Exit:
    
    To exit the game, press esc.

##### ● Additional Functions:
    
    It is possible to turn on tracking and observe the movement of zombies. To do this, press the T key.

------

#### Menu:

In the menu the player will be given three buttons:

##### ● New Game:
    
    Clicking will launch the game itself.
-- -- --
##### ● Options:
    
    Where the player will be prompted to change the screen size (Works only on MacOS).
-- -- --
##### ● Exit:
    
    The player will be asked to exit the game.
------

#### Game settings:

● The game have default settings. To change the basic settings, the player has the opportunity to change the settings in the administrator mode (Admin mod), which runs in a separate code. Changes to health units will be available for change, as well as changes to things (item cost, damage, etc.).

-----

#### Purpose of the game:

● To survive in a post-apocalyptic and to give the player the pleasure of the game.

-----

## Additions:

### Used technologies (libraries)

● To create the game I used **JavaFX library**, with which I implemented animations, battles, movements and other complex aspects, which is very important to give player maximum pleasure.

#### ● Before running the game, don't forget to install **JDK 18** and be sure, that in configurations and project structure JDK is set to 18 version! 
-- -- --
### Program description:

#### Our program has 7 different packages performing specific tasks.

#### -- animation package --

● AnimationInterface.java - has all methods, which will be implemented in AnimationQueue.java

● AnimationQueue.java - Creating a queue of character animations and punches.

● AnimationView.java - Initialising animations.

#### -- elememnts package --

● GetAndSetSettings - Creating getters and setters for Settings.java as abstract class.

● Menu.java - Creating game menu, setting buttons, initializing game music. 

● Settings.java - Initialising game settings, world, player, zombie information, animation.


#### -- features package --

● Indicator.java - Item info initialisation, setting images, setting labels and items.

● Obstacles.java - Initialising the map, creating borders with the obstacles.txt file.

● Sprite.java - Initialising characters sprites.

#### -- gui package --

● GUI.java - Creating graphic user interface, creating death window after player death.

● GUIInfo.java - Setting GUI information, making style, setting icons, making refresh for items.

#### -- handlers package --
    
    -- abstractClass package --
    
    ● GetAndSetMoveHandler - Creating abstract class with getters and setters for MovementHandler.java
    
    ● GetAndSetSceneHandler - Creating abstract class with getters and setters for SceneHandler.java

● BattleHandler.java - Initialising attack animation.

● EnemyHandler.java - Initialising and respawning zombies in random coordinates.

● MovementHandler.java - Initialising player movements.

● SceneHandler.java - Setting GUI colors, setting character coordinates, making scene, activating movements, making stop animation.

● ShopHandler.java - Initialising the game store, setting item information, loading items into the store.

#### -- item package --

● GetAndSetItemInfo - Creating abstract class with getters and setters for ItemInfo.java

● ItemInfo.java - Creating characters items (HP, DMG, SPEED, FOOD, WATER).

● ItemCell.java - Creating a button for the item, initializing the button press, adding an image of items.

#### -- launch package --

● InitScreen.java - Initialising main game screen.

● **Launcher.java** - Launches the game.

● Main.java - Activating all game processes.
-- -- --
#### Other packages:

● resources -  Contains:

    gameFiles/... - folder with all using elements (map, images, sounds, obstacles).

    gameFiles/items.xml - item settings (can be changed).

    gameFiles/settings.xml - game settings (can be changed).

    META-INF - folder for creating .jar archives.
    
    obstacles.txt - txt file with coordinates on the game map, which are barriers.
    
    style.css - setting background colour.

● test - Contains game testing package classes:
    
    ● animation/AnimationQueueTest - testing animation which is in queue.
    
    ● elements/MenuElementsTest - testing music init path, path to files.

    ● elements/SettingsTest - testing path to folder, getters and setters.

    ● gui/GUITest - testing image.

    ● handlers/MovementHandlerTest - testing used coordinates and directions with getter and setter.

    ● item/ItemTest - testing Item stats addition + info getters and setters.

● target - IntelliJ will keep the compiled versions of application files. 

● **pom.xml** - .xml file for launching the game without opening main code.

-- -- --
#### Other good mensions:
    ● You can make a .jar by going Maven->Zombie_survival->(Lifecycle->package/Plugins->jar->jar:jar). You will find .jar in the target folder.
    
    ● In this project was used threads! You can see them in next classes:
        I) AnimationQueue (rows 13-24 & rows 54-86)
        II) GUI (rows 77-87)
    
    ● Also You will be able to see how they works in the game!

-- -- --
#### How to change map:
    Working with the map is simple:
    1 - Download the map from the internet.
    2 - Add the map to the resources -> map file.
    3 - Add the obstacles.txt file to the resources folder. The .txt file must contain the coordinates of the new map's boundaries.
    4 - Change in the resources/gameFiles/settings.xml file in lines 69 (map.png) and 70 (map_with_obst.png) to the map you want to use.
    5 - Ready to go!

#### Menu:
![[Image description]](https://gitlab.fel.cvut.cz/B212_B0B36PJV/rastvdmy/-/wikis/uploads/2856b6dd8ad29a90203f681d7046d2a7/ZpmbieSurvivalMenu.png)

#### Options:
![[Screen size on MacOS]](https://gitlab.fel.cvut.cz/B212_B0B36PJV/rastvdmy/-/wikis/uploads/1be26be37ce4b7981dc8bdbb66b1c361/Zombie_gif.mov)

#### Gameplay:
![[Image description]](https://gitlab.fel.cvut.cz/B212_B0B36PJV/rastvdmy/-/wikis/uploads/f289ad7a98833d684a9f3ec0b6574543/Gameplay.jpg)

#### Gameplay (with tracking):
![[Image description]](https://gitlab.fel.cvut.cz/B212_B0B36PJV/rastvdmy/-/wikis/uploads/de1767b88c90dd7293aa51b265f1c457/Gameplay2.jpg)

#### Shop: 
![[Image description]](https://gitlab.fel.cvut.cz/B212_B0B36PJV/rastvdmy/-/wikis/uploads/4c74c27ff2d07c47b797f2eef201fb4c/Shop.jpg)

# Enjoy! 🧟‍♂️

