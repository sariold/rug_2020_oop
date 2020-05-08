# Text-based RPG

In this assignment, you'll make your own text-based RPG. For the people who don't know what a text-based RPG is; it is an RPG that is played by interacting with a virtual world through text (`System.in` and `System.out`). This assignment will help you get familiar with inheritance and polymorphism. 

In this RPG you'll be acting as the player. The game will be a sort of dungeon crawler where you make your way through all kinds of rooms, and you'll run into some of the most fantastic creatures (un)known to mankind. If you manage to reach the final room, you win eternal fame and glory (if you know how to program that).

Note that we will not explain everything in as much detail as the first assignment. You will have to decide most of the implementation details for yourself.  Quite some things that you need for this assignment are explained in the reader, so please read through the entire chapter `OOP Concepts` before you start this assignment. It will make it significantly easier to understand and make. 

This is probably one of your first projects using OOP, so don't be afraid to ask help from your TA! We are there to help :)

## 0. Before we begin

### Setting up
Just as the previous assignment, you will find the necessary files in the newly created `__assignment1` branch. Make sure that you successfully merge the `__assignment1` branch into `development`. For instructions on how to do this, see the reader and/or the previous assignment.

### Incremental Maintenance
This assignment has been divided into multiple section so that we can focus on a small subproblem at the time. This way it is easier to manage the changes. The programs we will be writing in this course will be a lot larger than the programs written so far in, for example, Imperative Programming. Therefore it is important to maintain a pleasant working environment. We can do this by doing the following after each step:

- Adding comments(mainly Javadoc) to the code;
- Integrating the changes into the main program so they are visible and usable;
- Verifying that the code still compiles;
- Refactoring the code (remove duplicate code, ensure proper variable names etc.);
- Committing the changes;
- Pushing the commit to the `development` branch.
- Giving yourself a pat on the back to stay motivated.

## 1. Rooms

Let's start with creating the most important part of our RPG: the player. We can do this by creating a class `Player`. Since we want our `Player` to have a magnificent name, the `Player` should have a field `name`.

Before we can start playing, we need something to walk around in. As mentioned before, our RPG world consists of rooms, so let's implement this by creating a class `Room`. A `Room` should have a field `description` which will be used to `inspect` a `Room`. Whenever a `Room` is inspected, it should print this descriptive piece of text to `System.out` using the `System.out.println` method.

Note that the point of having classes is (among others) reusability. Therefore, don't hardcode the room descriptions in `Room`, since this does not allow us to easily create rooms with different descriptions.
Instead, have the description string as a parameter in the constructor of `Room`. This means that wherever you initialise the `Room`, you can specify a description. This holds for almost all the classes we will be creating. In general it is good practice to hardcode as little as possible, so try to conform to that. 

Now that we have rooms and a player, we can connect the two. Since we play from the perspective of a `Player`, we do not let a `Room` keep track of the `Player`, but rather we let the `Player` keep track of the `Room` they are in.

**Requirements:**

- Create a `Player` class with a `name`.
- Create a `Room` class with a `description`.
- You should be able to `inspect` a `Room`. Doing so will print its description.
- The `Player` should keep track of what `Room` they are in.

## 1.1 A Simple Interaction Menu

We now have all the ingredients to create a basic program. Let's use the two classes mentioned above to create a simple program that can do some basic interaction. For now you are allowed to do this in the `Main` class, but keep in mind that the `Main` class should ideally only be used for initalising. Later on, you will have to change this.

The program should print a simple options menu. The user can then enter a number that matches one of the options. Just as in assignment 0, we can read the input from the user with `Scanner`. More specifically, we want to read an integer from the input line, so once we have created a `Scanner` object, we can call the `nextInt()` method.

For now, let's add an option to "look around". Whenever the player looks around, the `Room` the `Player` is in should be inspected. This will look something like this:

```
What do you want to do?
  (0) Look around
0
You see: A rather dusty room full with computers and two doors.
What do you want to do?
 (0) Look around
```

As you can see, this interaction menu should keep repeating itself. For now you can use an infinite while loop for this. Later on, we can add conditions so that the loop stops if the `Player` wins (or dies).

**Requirements:**

- A simple interaction menu with the option to look around
- When the the option "look around" is chosen, the `Room` the `Player` is in should be inspected.

## 2. Doors
Are you able to look around in the `Room`? Well done! So far, so good. It's starting to get boring in this particular `Room` after a while though, so we decide we want to escape it and go to another `Room`. This means we will need to have doors, so let's create a class for this.

**Requirements:**

- Create a `Door` class that is very similar to a `Room`: it has a description, and you should be able to `inspect` it.

### 2.1 Inspectable

By now, both classes probably have a field `private String description` and a method `inspect()`.
At this point there might be a small voice in the back of your head that says: "_Psst, I smell duplicate code_ (◕ᴥ◕)". If this is the case, contact a doctor as soon as possible.
Nevertheless, the `behaviour` of the `Door` and `Room` classes is indeed very similar. They both share one property: they are inspectable. Let's generalise that property!

The OOP-way to indicate that a class has certain `behaviour` is via an interface. Interfaces are used to describe that a class **must** have a few methods, which can then be used by the outside world. The implementation of those methods, however, is not provided in the interface. 
This means that the method(s) that are described in the interface can potentially not do what you expect them to do. Therefore be very concise with how you name your interface and the methods in your interface. 

The way that you specify these methods in interfaces is extremely similar to the way that you write forward declarations for functions in C header files. In short, a basic interface is simply a list of unimplemented methods. In this case, there is only method in there: `inspect()`. This means that our interface will look something like this:

```java
/**
 * An interface that classes can implement so that they can be inspected
 */
interface Inspectable {

    void inspect();

}
```

Our newly created interface `Inspectable` is now ready to be used! The way that we apply interfaces to classes is by using the `implements` keyword. Using our new interface in `Room` would be as easy as changing

```java
public class Room { 
```

to 

```java
public class Room implements Inspectable {
```

Let's apply the same changes to `Door`. These changes enforce the programmer to give `Door` and `Room` an `inspect()` method. Note that since we don't have those methods yet at this point, you will not be able to compile your program anymore. We will fix this in the next section.

**Requirements:**

- Create an interface `Inspectable` with an `inspect()` method.
- `Room` should implement `Inspectable`.
- `Door` should implement `Inspectable`.

### 2.2 Adding Doors

It is obviously nothing short of amazing that we have added a `Door` class, but we are not doing anything with it now. That is a bit sad, so let's fix that.

A `Room` can have multiple doors. A first intuition might be to add an array of `Door`s to `Room`. However, arrays are really annoying to use in this case, since we need a length, keep track of indices and all that stuff.
Instead, we are going to use a List. Lists are very nice, because they allow us to add and remove items very easily. In Java, we use a special type of list: `ArrayList` (There are other List types, but `ArrayList` is almost always a good choice). 

We can add a list of `Door`s as follows: `private ArrayList<Door> doors`. The part between the angle brackets indicates what items should be in our `ArrayList`. In this case, we want a list containing `Door`s, so we added `Door`. 

However, just as any other object, we first need to initialise our list before we can use it. We can do this by adding the following to our constructor:

```java
doors = new ArrayList<>();
```

Let's also create a method `addDoor(Door door)` so that we can add doors to this list. Adding an item to an `ArrayList` can be done by calling `add(...)` on the our List. This will add the door provided in the arguments to the end of the List.

**Requirements:**

- Add a field `doors` to `Room`. The field should be an `ArrayList`.
- Initialise the List of doors in the constructor of `Room`.
- Add a method `addDoor(Door door)` to `Room` that adds a `Door` to the list of doors.

### 2.3 More Gameplay Options

We are also going to add another gameplay option that allows us to see all the doors in a room. The option will be "look for a way out" and, if selected, shows all the doors in the room. The user can then select a door to interact with.

Suppose that we are in a room that has two doors, this would look something like this:

```
What do you want to do? 
  (0) Look around 
  (1) Look for a way out 
1 
You look around for doors. You see: 
  (0) A mysterious red door 
  (1) A black door 
What do you want to do? 
  (0) Look around 
  (1) Look for a way out
```

As you can see, the interaction is now two levels deep. Whenever the user selects "look for a way out", it will show some new interactive possibilities. We will be adding more to this interaction menu, so be sure to use separate methods to handle all these different possibilities. This will make it a lot easier to read and (potentially) debug your code.

If you have done the main game loop in `Main` so far, now would be a good time to move this to a more dedicated class. You could, for example, create a class `Game` that executes the main game loop. Remember: `Main` should only be used for initialising.

An important thing to pay attention to is that calling `inspect()` on `null` will cause a `NullPointerException`. This is rather undesirable, so add a check to the `addDoor()` function that prevents us from adding `null` to the list of doors.

**Requirements:**

- Add an option to the interaction menu that allows the `Player` to "look for a way out".
- When the option to "look for a way out" is selected, all the `Door`s of the room should be inspected.
- Add a `null` check in the `addDoor()` method that prevents us from adding `null` to the list of doors.

### 2.4 Entering Doors

Obviously we want to do more than just inspecting doors. So now we are going to add something that allows the `Player` to go through a `Door`. Whenever a `Player` `interacts` with a `Door` they should go through it. This means that the `Door` will need a method `interact()`.

This means that we first need to connect a `Door` to another `Room`. We can do this by adding a field to the `Door` for the `Room` behind it. In this case, our doors are one-way. So once we go through it, we cannot go back (you are free to change this later on). 

In the `interact()` method of `Door`, we need to somehow tell our `Player` to change rooms. However, this is a bit difficult, since the `interact()` method belongs to `Door` and not to `Player`. Therefore, you should add a parameter `Player` to the `interact()` method, so that we can tell the player to switch rooms. There is no need to make the `Player` a field everywhere or to make it a global variable, which would be even worse. 

Once you added the ability for a `Player` to go through a `Door` the interaction menu should look something like this:

```
What do you want to do? 
  (0) Look around
  (1) Look for a way out
0
You see: A rather dusty room full with computers and two doors.
What do you want to do? 
  (0) Look around 
  (1) Look for a way out 
1 
You look around for doors. 
You see: 
  (0) A mysterious red door 
  (1) A black door 
Which door do you take? (-1 : stay here) 
1 
You go through the door 
What do you want to do? 
  (0) Look around 
  (1) Look for a way out 
0 
You see: A dark room with dark doors
```

**Requirements:**

- A `Door` should be connected to a `Room` behind it.
- Add an `interact()` method to `Door`. When this method is called, the player should move to the `Room` behind that `Door`.
- The `Player` should be an argument of the `interact()` method.
- The interaction menu should be augmented with an option to select a `Door` to go through after looking around.

### 2.5 Interactable

We have now made a `Door` interactable. However, we will also be adding more things that we can interact with(one of them being NPCs). If we wanted to expand on this program even more, we could also have chests or items to interact with. In other words, a lot of these classes would share `behaviour`. And just as before, whenever we see a lot of these (unrelated) classes sharing `behaviour`, we use an interface. 
So let's move this method into a new interface `Interactable`.

Now `Door` can implement this interface. The nice thing is, as mentioned before, that we can also use this for NPCs.

**Requirements:**

- Create an interface `Interactable` that contains the method `void interact(Player player)`.
- `Door` should implement this `Interactable` interface.

Before we continue, let's make sure that everything works so far. Test your program to to see if there are any bugs in there. As you know, if you have a lot of bugs, they will start to affect each other and everything will be one big mess. So take it slow and you'll have an easier time.


## 3 NPCs

Now it is finally time to add some more exciting interactions to our game!

We are going to add some NPCs. To do this, we create an `NPC` class. For now, the `NPC` should only have a field `String description`. The `Player` should be able to `inspect` and `interact` with an `NPC`, so make sure `NPC` implements both `Inspectable` and `Interactable`. 

An `NPC` belongs to a `Room`, so let's also create a field to keep track of all the `NPC`s in a `Room`. Note that there can be multiple `NPC`s in a room, so we can use an `ArrayList` for this again.

We will later add more specific NPCs, but for now let's use an example implementation with a simple print statement in the `interact()` method to verify that `NPC` is working. The interaction menu should be augmented with an option "look for company" which should list all the `NPC`s. This should look something like this:

```
What do you want to do? 
  (0) Look around 
  (1) Look for a way out 
  (2) Look for company
2 
You look if there’s someone here. 
You see: 
  (0) A suspiciously happy looking orc  
  (2) The kerstman
  (3) A dancing strawberry
Interact ? (-1 : do nothing) 
1 
The creature is asleep so you can’t interact with it. 
What do you want to do? 
  (0) Look around 
  (1) Look for a way out 
  (2) Look for company
```
If this works, awesome! We can now start to implement some more interesting `NPC`s.

**Requirements:**

- Create an `NPC` class with a field `description`.
- An `NPC` should be `Inspectable`.
- An `NPC` should be `Interactable`.
- A `Room` should have contain a number of `NPC`s.
- Create a new option in the interaction menu: "Look for company".
- When the option "look for company" is chosen, it should inspect all the NPCs in the room.

## 4 A Simple Combat System

What fun is an RPG without a little combat? So let's add this!
In order to have combat, we first need something to fight. So let's create an `Enemy` class. Since `Enemy` is an `NPC`, it should extend from `NPC`.

From now on, you will have to make the design decisions yourself. We will only give some requirements that your program should have and you are free to decide how to implement this. Try to make use of inheritance, abstract classes and interfaces to create a proper design here!

Don't forget that you can add methods to `Player` which can be called by, for example, the `NPC`'s `interact()` method. This allows you to add a lot more besides the combat system. Think about things such as a money system or status effect. However, make sure that you have enough time to finish the other parts of this assignment. If you finished all that, you can go ham!

**Requirements:**

- `Player` should have damage and health.
- All `NPC`s should have damage and health.
- Only `Player` and `Enemy` should be attackable.
- The `Player` should be able to attack an `Enemy` and by doing this deal damage to that `Enemy`.
- An `Enemy` should be able to attack the `Player` and by doing this deal damage to the `Player`.
- The `Player` or an `NPC` should die when its health drops to or below 0.
- The game stops when the `Player` dies. When the game stops, it should print something along the lines of "Game Over!".
- A simple interaction menu for the combat. For example, when the `Player` interacts with an `Enemy` it should give the option to attack that enemy. The `Enemy` then attacks back etc.

## 5 Multiple NPCs 

Since we want our game to be a bit more interesting, we do not want boring generic NPCs in there. Therefore, make the class `NPC` abstract. 
Now you can get as creative as you want!
Try to come up with another class that extends from `NPC`. Make sure it does something interesting! Some ideas could be:

- A healer that heals the `Player`
- A trader that gives the `Player` an item (in exchange for money?)
- A wizard that buffs the `Player` and permanently increases their damage

Note that, since `NPC` is now abstract, we no longer have to give specific implementation of `interact()` and `inspect()` in `NPC`. The details of these methods are specific to the classes that extend from `NPC` and should therefore rather be implemented in those specific subclasses.

**Requirements:**

- Make `NPC` an abstract class.
- Create at least 1 other class (besides `Enemy`) that extends from `NPC`.
- The class(es) should have distinct behaviour (different descriptions do not count; be creative!).

## 6 Multiple Doors

Just as with NPCs, we also want to make the doors a bit more interesting. So create at least two classes that extend from `Door` and make sure that they do something interesting! Some examples could be:
- An AttackDoor that the `Player` has to defeat before entering.
- A MonsterDoor that requires all enemies to be dead before entering.
- A TrapDoor that damages the player when going through.

**Requirements:**

- Create at least 2 classes that extend from `Door`.
- The class(es) should have distinct behaviour (different descriptions do not count; be creative!).


## 7 Final Requirements

As you might have noticed, we have not implemented anything that allows us to win the game. Therefore, now might be a good time to do this. We leave it up to you how to implement this. The idea is that the `Player` wins when he reaches a certain "winning" room. However, if you have another good idea, you are free to do something else!

On top of all the previous requirements, we only have a few more requirements.

**Requirements:**

- You should have at least 2 different abstract super classes.
- You should have at least 2 different interfaces (besides `Inspectable` and `Interactable`).
- Allow the `Player` to win the game.

## 8 Be Creative!

You are encouraged to add your own twist to the RPG! Create a real story or make the experience more enjoyable in whatever way you wish. Once you get the hang of it, it will be a lot of fun to think of new things and functionalities to add! 
Think of fairies, trolls, weird items, characters from Lord of the Rings - the more interesting the story and the game, the more chance you have at getting bonus points! Just make sure that your program can at least do what is specified in the requirements. Also be sure to keep paying attention to the design of your program. While a very nice game is obviously amazing, we will be mainly looking at your code/design. 

## 9 Free Tip

We obviously want you to get the highest grade possible, so here is a free tip to **bump up your grade**: go through the section `Common Mistakes` in the reader. You can increase the quality of your code **a lot** by going through this list and checking your program for every single one of these mistakes. It really can make quite the difference!

## 10 Handing in

When you are finished, create a pull request from the `development` branch into the `master` branch and confirm that your program passes CircleCI.
