package com.rfl.snake;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.*;

public class SnakeApp extends GameApplication {

    public static final int TILE_SIZE = 20;
    public static final int GAME_SIZE = 20;

    private Snake snake;
    private Random rand = new Random();
    private boolean grow = false;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(TILE_SIZE * GAME_SIZE);
        gameSettings.setHeight(TILE_SIZE * GAME_SIZE);
        gameSettings.setTitle("Snake Game");
        gameSettings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        snake = new Snake();
        getGameWorld().addEntityFactory(new GameEntityFactory());
        snake.setHead(spawn("snakehead", TILE_SIZE * 2 + 1, getAppHeight() / 2 + 1));
        snake.getBody().add(spawn("snakeblock", 1, getAppHeight() / 2 + 1));
        snake.getBody().add(spawn("snakeblock", TILE_SIZE + 1, getAppHeight() / 2 + 1));
        spawn("food", rand.nextInt(GAME_SIZE) * TILE_SIZE + 1, rand.nextInt(GAME_SIZE) * TILE_SIZE + 1);
        run(() -> {
            snake.move(grow);
            grow = false;
        }, Duration.seconds(0.5));
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.RIGHT, () -> {
            if (snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            }
        });
        onKey(KeyCode.LEFT, () -> {
            if (snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }
        });
        onKey(KeyCode.UP, () -> {
            if (snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }
        });
        onKey(KeyCode.DOWN, () -> {
            if (snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            }
        });
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.SNAKEHEAD, EntityType.FOOD, (head, food) -> {
            grow = true;
            food.removeFromWorld();
            spawn("food", rand.nextInt(GAME_SIZE) * TILE_SIZE + 1, rand.nextInt(GAME_SIZE) * TILE_SIZE + 1);
        });
        onCollision(EntityType.SNAKEHEAD, EntityType.SNAKEBLOCK, (head, body) -> {
            getGameController().startNewGame();
        });
    }
}
