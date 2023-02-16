package me.adversing.decisions4j.cases;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Case<T> {
    private final Predicate<T> condition;
    private final Consumer<T> action;

    public Case(Predicate<T> condition, Consumer<T> action) {
        this.condition = condition;
        this.action = action;
    }

    public Predicate<T> getCondition() {
        return condition;
    }

    public Consumer<T> getAction() {
        return action;
    }
}
