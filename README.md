# Decisions4J
`Decisions4J` is a Java API that allows you to define a set of rules to apply to an object and execute the appropriate action based on the first matching rule. It's useful when you need to make a decision based on a complex set of conditions. 

---

## Usage
To use `Decisions4J`, create a new instance of it by passing an object of the type that you want to evaluate as its argument:
```java
DecisionTree<String> tree = new DecisionTree<>("test"); // in this case we're evaluating a String
```

### Adding rules
Once you have a `Decisions4J` instance, you can add rules to it. A rule consists of a condition and an action. The condition is a `Predicate` that evaluates to true if the rule should be applied, and the action is a `Consumer` that specifies what should be done if the rule is applied.

To add a rule to the `DecisionTree`, call the `caseCondition()` method with the condition and the action as arguments. Here's an example:
```java
tree.caseCondition(s -> s.equals("test"), s -> System.out.println("test"));
```
In this example, we're adding a rule that says "if the string is 'test', then print 'test'".

### Adding a default rule
If none of the rules match, you can specify a default action to be executed by calling the `defaultCase()` method:
```java
tree.defaultCase(s -> System.out.println("default"));
```
In this example, we're specifying that if none of the other rules match, the action should be to print "default".

### Applying the rules
Once you've added all the rules, you can apply them to the object by calling the `decide()` method:
```java
tree.decide();
```
This will evaluate the object and execute the first matching rule.

---

## Example
Here's an example of using `Decisions4J` to evaluate a `Person` object:
```java
// Create a new DecisionTree with a Person object
Person person = new Person("Alice", 30);
DecisionTree<Person> tree = new DecisionTree<>(person);

// Add a rule
tree.caseCondition(p -> p.getAge() > 18, p -> System.out.println(p.getName() + " is an adult"));

// Add a default rule
tree.defaultCase(p -> System.out.println(person.getName() + " is a minor"));

// Apply the rules
tree.decide();
```
In this example, we're creating a new `DecisionTree` instance with a `Person` object. We're adding a rule that says "if the person's age is greater than 18, then print 'Alice is an adult'" (where "Alice" is the person's name). We're also adding a default rule that says "print 'Alice is a minor' if none of the other rules match". Finally, we're applying the rules to the person object by calling `decide()`.

You may also use this syntax:
```java
// Create a Person object
Person person = new Person("Alice", 30);

// Create a DecisionTree object and apply rules
DecisionTree<Person> tree = new DecisionTree<>(person)
        .caseCondition(p -> p.getAge() > 18, p -> System.out.println(p.getName() + " is an adult"))
        .defaultCase(p -> System.out.println(person.getName() + " is a minor"))
        .decide();
```

---

## Error handling
`Decisions4J` provides some basic error handling. If you don't add any rules to the `DecisionTree`, calling `decide()` will result in an `IllegalStateException`. Similarly, if you don't call `decide()` after adding rules, you'll get an `IllegalStateException` as well. If you call `defaultCase()` with a null action, you'll get a `NullPointerException`.

---
###### check out the javascript version [here](https://github.com/Adversing/decisions.js/) and the python version [here](https://github.com/Adversing/decisions.py/) :)   
