# Silver Arrow Gameplay Demo Update 1.1

## Synopsis
This program is the same demo but has been updated to show off more gameplay. This update added objects as obstacles for the character to move around.
Right now there are walls at the edge of the screen, four boxes near the corner, and a rectangle of water.
The character can't, of course, move the walls, boxes, and water. The arrow can't move through the walls and boxes but can "fly" over the water.
This was a small but needed update, collision and objects is what makes a game. Now that it's finally here, I'm one step closer to moving a full-fledge game.

## Motivation
The motivation to adding collision and objects to collide into is that I wasn't able to do it before. When I made the first demo, I planned to add collision 
but didn't have time to. I was satisfied with being able to make a triangle character to move around but having no objects to move around was not fun. I knew 
adding collision would be a long, but worth it, task. I'm surprised and satisfied to have been able to add collision to my demo and I plan with this knowledge 
to add more in the future. More will come.

## How to Play
You'll need the classes CharecterPane, ObjectPane, and DemoRoom to run this demo. You use WASD to move the character around and the mouse cursor to rotate the 
character around too. CLicking the mouse button "shoots" an arrow in the direction of the circle.

## Code Example
Here's the code that I added to make collision
```
public boolean isCollidingLeft(double x, double y, double width, double height) {
        if (getHitboxX() + getHitboxWidth() + 5 > x && getHitboxX() < x + width && 
                getHitboxY() + getHitboxHeight() > y && getHitboxY() < y + height) {
            return true;
        }
        else {
            return false;
        }
    }
```
```
public boolean isCollidingRight(double x, double y, double width, double height) {
        if (getHitboxX() + getHitboxWidth() > x && getHitboxX() - 5 < x + width && 
                getHitboxY() + getHitboxHeight() > y && getHitboxY() < y + height) {
            return true;
        }
        else {
            return false;
        }
    }
```
```
public boolean isCollidingTop(double x, double y, double width, double height) {
        if (getHitboxX() + getHitboxWidth() > x && getHitboxX() < x + width && 
                getHitboxY() + getHitboxHeight() + 5 > y && getHitboxY() < y + height) {
            return true;
        }
        else {
            return false;
        }
    }
```
```
public boolean isCollidingBottom(double x, double y, double width, double height) {
        if (getHitboxX() + getHitboxWidth() > x && getHitboxX() < x + width && 
                getHitboxY() + getHitboxHeight() > y && getHitboxY() - 5 < y + height) {
            return true;
        }
        else {
            return false;
        }
    }
```
```
public boolean isCollidingLine(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double uA = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));
        double uB = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));
        
        if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
            return true;
        }
        else {
            return false;
        }
    }
```
```
public boolean isCollidingLineBottom(double x1, double y1, double x2, double y2, double rx, double ry, double rw, double rh) {
        if (isCollidingLine(x1, y1, x2, y2, rx, ry + rh, rx + rw, ry + rh)) {
            return true;
        }
        else {
            return false;
        }
    }
```
```
public boolean isCollidingLineTop(double x1, double y1, double x2, double y2, double rx, double ry, double rw, double rh) {
        if (isCollidingLine(x1, y1, x2, y2, rx, ry, rx + rw, ry)) {
            return true;
        }
        else {
            return false;
        }
    }
```
```
public boolean isCollidingLineRight(double x1, double y1, double x2, double y2, double rx, double ry, double rw, double rh) {
        if (isCollidingLine(x1, y1, x2, y2, rx + rw, ry, rx + rw, ry + rh)) {
            return true;
        }
        else {
            return false;
        }
    }
```
```
public boolean isCollidingLineLeft(double x1, double y1, double x2, double y2, double rx, double ry, double rw, double rh) {
        if (isCollidingLine(x1, y1, x2, y2, rx, ry, rx, ry + rh)) {
            return true;
        }
        else {
            return false;
        }
    }
```
