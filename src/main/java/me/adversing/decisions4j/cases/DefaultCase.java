package me.adversing.decisions4j.cases;

import java.util.function.Consumer;

public class DefaultCase<T> {
    private final Consumer<T> action;

    public DefaultCase(Consumer<T> action) {
        this.action = action;
    }

    public Consumer<T> getAction() {
        return action;
    }
}
