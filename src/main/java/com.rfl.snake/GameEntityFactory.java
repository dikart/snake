package com.rfl.snake;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

import static com.rfl.snake.SnakeApp.GAME_SIZE;
import static com.rfl.snake.SnakeApp.TILE_SIZE;

public class GameEntityFactory implements EntityFactory {
    @Spawns("snakehead")
    public Entity newHead (SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityType.SNAKEHEAD)
                .viewWithBBox(new Rectangle(TILE_SIZE -2, TILE_SIZE -2, Color.BURLYWOOD))
                .collidable()
                .build();
    }
    @Spawns("snakeblock")
    public Entity newBlock(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityType.SNAKEBLOCK)
                .viewWithBBox(new Rectangle(TILE_SIZE -2, TILE_SIZE -2, Color.GREEN))
                .collidable()
                .build();
    }
    @Spawns("food")
    public Entity newFood(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.FOOD)
                .viewWithBBox(new Rectangle(TILE_SIZE -2, TILE_SIZE -2, Color.YELLOW))
                .collidable()
                .build();
    }
}
