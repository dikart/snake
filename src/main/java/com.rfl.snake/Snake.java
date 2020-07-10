package com.rfl.snake;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.rfl.snake.SnakeApp.GAME_SIZE;
import static com.rfl.snake.SnakeApp.TILE_SIZE;

public class Snake {
    private Direction direction = Direction.RIGHT;
    private Entity head;
    private List<Entity> body = new LinkedList<>();

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setHead(Entity head) {
        this.head = head;
    }

    public void move(boolean grow) {
        if (!grow) {
            body.add(spawn("snakeblock", head.getPosition()));
            moveHead();
            getGameWorld().removeEntity(body.remove(0));
            checkBoundaries();
        } else {
            grow();
        }
    }

    private void checkBoundaries() {
        Point2D p = head.getPosition();
        if (p.getX() > TILE_SIZE * GAME_SIZE || p.getX() < 0
                || p.getY() > TILE_SIZE * GAME_SIZE || p.getY() < 0) {
            getGameController().startNewGame();
        }
    }

    public void moveHead() {
        head.translate(direction.vector.x * TILE_SIZE, direction.vector.y * TILE_SIZE);

    }

    public List<Entity> getBody() {
        return body;
    }

    public void grow() {
        body.add(spawn("snakeblock", head.getPosition()));
        moveHead();
    }
}
