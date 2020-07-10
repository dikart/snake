package com.rfl.snake;

import com.almasb.fxgl.core.math.Vec2;

public enum Direction {
    DOWN(new Vec2(0, 1)),
    LEFT(new Vec2(-1, 0)),
    RIGHT(new Vec2(1, 0)),
    UP(new Vec2(0, -1));

    final Vec2 vector;

    Direction(Vec2 vector) {
        this.vector = vector;
    }

}
