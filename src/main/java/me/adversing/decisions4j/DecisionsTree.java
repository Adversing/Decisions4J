package me.adversing.decisions4j;

import me.adversing.decisions4j.cases.Case;
import me.adversing.decisions4j.cases.DefaultCase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DecisionsTree<T> {

    private final List<Case<T>> cases = new ArrayList<>();
    private DefaultCase<T> defaultCase;
    private final T subject;
    private boolean isDecisionMade = false;

    public DecisionsTree(T subject) {
        this.subject = subject;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!isDecisionMade) {
                throw new IllegalStateException("DecisionTree decision not made.");
            }
        }));
    }

    public DecisionsTree<T> caseCondition(Predicate<T> condition, Consumer<T> action) {
        cases.add(new Case<>(condition, action));
        return this;
    }

    public DecisionsTree<T> defaultCase(Consumer<T> action) {
        if (action == null) throw new NullPointerException("Default case action cannot be null.");

        defaultCase = new DefaultCase<>(action);
        return this;
    }

    public void decide() {
        if (cases.isEmpty()) throw new IllegalStateException("At least one case is required in a DecisionTree.");

        for (Case<T> c : cases) {
            if (c.getCondition().test(subject)) {
                c.getAction().accept(subject);
                isDecisionMade = true;
                return;
            }
        }

        if (defaultCase != null) {
            defaultCase.getAction().accept(subject);
            isDecisionMade = true;
            return;
        }

        throw new IllegalStateException("DecisionTree decision not made.");
    }
}
