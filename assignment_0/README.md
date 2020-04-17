# A simple program

Writing your first program in a new programming language is always a big step
to take. The ability to create a program, compile it, run it, then see its
output shows that you set up everything correctly and that the programming
language works, which is obviously quite useful.

This exercise is, however, more than just a simple hello world (don't worry,
your first print statement will still be "Hello World!"). You are going to
write a small interactive program and we are going to guide you through this!
We tried to make all the explanations as extensive as possible. If you already
have some knowledge about Java, this may seem too elaborate and over-explained,
but we want everyone to understand it so please bear with us :).

If there are still unclarities, please do not hesitate to contact your TA!
The purpose of this assignment is to get you familiar with Git and Java.

## Before you begin

Please read through the `Reader`. Before you can start this assignment, you
should carefully read `Tools & Information` and follow the steps in the
`Getting Started` section. We would also highly recommend you to read through
the first two sections of `OOP Concepts`: `Objects` and `Classes`. This
information is important for understanding this assignment. We will also be
adding extra comments in the code we provide to make it more clear what is
happening. The format of these comments follows the Javadoc convention, which
you can read about in the `Javadoc` subsection under the `Additional
Information` section in the `Reader`.

Before you begin you must have done the following things:

1.  Set up Git: the GitHub repository should have been cloned.
    An explanation about what to do can be found in the `Setting up Git` section
    of the `Reader`. In short you should have done the following:
    - Downloaded and installed Git on your machine;
    - Cloned your group's GitHub repository to your local machine using `git
      clone <link that you copied from GitHub> [directory]`.

    There are a few steps you have to follow in order to add the assignments to
    your repository. This is also explained in the reader, but since this is
    assignment 0, we will shortly go over it:

    - The assignments will be automatically pushed to a new branch in your
      group's GitHub repository. These branches are called `__assignment_x` where
      `x` is the assignment number. So for the first assignment, the
      assignment will be in the branch `__assignment_0`;
    - Navigate to the directory of the assignment and make sure that you are on
      the `development` branch. You can do this with the command `git checkout
      development`;
    - To put the new files from assignment 0 in the `development` branch, call
      the following 2 commands:

      ```bash
      # pull the new branch to your machine
      git pull
      # merge the contents of the __assignment_0 branch to your current branch
      # (should be development!)
      git merge __assignment_0
      ```

    Now all the necessary files should be in the `development` branch and you
    are ready to start working on it.

2.  Set up IntelliJ, which you can also find in the `Reader` (although it is
    very straightforward).

For this assignment, start up IntelliJ and choose `Open or Import`. This will
open a file navigator. Navigate to directory of the assignment on your PC, open
the `pom.xml` file and select `Open as Project`. This will open and set up the
project for you. Note that it may take some time for IntelliJ to fully set up
Maven. Once the project is set up, it should display this `README` file in
IntelliJ. Since you most likely already have the `README` opened up somewhere
else, you can close it in IntelliJ.

## 1. The Main class

After having set up IntelliJ, you can find the project tool window, which
contains the directory tree, on the left. This directory tree provides an
overview of the files in our project, and will be quite useful down the line.
Java programs are organized in classes, which can be seen as blueprints. An
actual instance of such a class is called an object.

In order to start writing code, we first need to create such a class. Just as
in C, we need a starting point for our application. This is done in the `Main`
class. Note that the name of the class that contains the starting point for our
application need not be `Main`. Let's create a file called `Main.java` in the
`introduction` package (IntelliJ will compact this to
`nl.rug.oop.introduction`). In IntelliJ we can easily do this by right clicking
this package, selecting `new` and then selecting `Java class`. Now you can
enter the name and press `<enter>`.

Once we have created the file, we can add the following code in it:

```Java 
public class Main {

}
```

However, IntelliJ is already quite smart, so it will probably do this for you.
Nevertheless, you should pay attention to what it says. This is the basic
template of a class in Java. When the Java compiler find the keywords
**public** and **class** together, it expects the name of that class (`Main` in
this case) to be in a file called `Main.java`. A `public class Card` would be
in a file called `Card.java`. In other words, the class name should match the
file name exactly (note that capital letters should also match!).

At this point, a blue bar will appear on your screen where IntelliJ tells us to
set up a Software Development Kit (SDK) to use. IntelliJ should already come
with their own runtime environment (based on OpenJDK) pre-installed, so this is
very easy. Click on `Setup SDK`, choose `Java 11` and press OK. In some cases,
it will prompt you with a menu containing a few options. In this case, select
`Download JDK` and then `OpenJDK`.

Similar to C, we also define a main function. In object-oriented programming,
functions inside classes are referred to as methods. We can do this inside the
`Main` class by writing:

```Java 
public static void main(String[] args) {

}
```

This is what the main method looks like in Java. The program starts when this
main method is called. Let's try to compile and run our program. We can run
the program in IntelliJ by clicking on the green run button next to the main
method we just defined. Alternatively, we could also run it from the
command-line. We can do this as follows:

- Compile your class with `mvn compile`, generating `Main.class` inside the
  target/classes directory;
- Alternatively, you can compile your program using `javac -d target/classes
  -cp src/main/java src/main/java/nl/rug/oop/introduction/Main.java`;
- Run your program with: `java -cp target/classes nl.rug.oop.introduction.Main`.

More information about compilation can be found in `Tools & Environment` in the
reader.

Great, we just wasted a few seconds on running an empty program. Let's fix that!

## 2. Adding Print Statements

Now we finally get to the good stuff: printing! Printing "Hello World!" in
Java can be done by adding the following line to the main method:

```Java
System.out.println("Hello World!");
```

This will print the line that is provided in the arguments of `println(String)`
to the standard output, appending the newline character `\n` to it.

OK great! However, for this assignment we are going to do more than just
printing "Hello World!" (so you can remove the previous print statement). We
are going to create a very small interactive program. The program will be about
animals. An animal only has two actions: eat and run. Additionally, an animal
has an energy level. The energy level decreases when it runs and it increases
when the animal eats.

The idea is that our program responds to user input. Whenever the user enters
"eat", the animal should eat and the energy level should go up. Whenever the
user enters "run", the animal should run and the energy level should go down.

## 3. Input reading 

Since we want to make our program interactive, we start by writing some code
that allows us to read input from the user. First, we add the following line
in the main method:

```Java
Scanner scanner = new Scanner(System.in);
```

You will have noticed that after typing this, IntelliJ is now complaining that
it does not know what `Scanner` is. To fix this, you will have to add an
`import` statement at the top of your program (above your class definition,
underneath your package definition). IntelliJ will also propose a fix for you
underneath the red warning square.

This will create an instance of the `Scanner` class. This `Scanner` allows us
to read the next line from the input. It takes as a parameter `System.in`; this
indicates that the input should be read from the standard input. Next, we can
do the actual input reading. We can read a line from the input and store it in
a `String` with the following line:

```Java
String input = scanner.nextLine();
```

Here we call the method `nextLine()`. Since this method belongs to the
`Scanner` class, we call it with `scanner.nextLine()`. The result of this
method is a `String` that contains the input line. 

Great, now we have our input! Now we are going to make it respond to a
particular user input. Remember that the program should respond to the words
"eat" and "run". Therefore, we can simply check if our input equals one of
those words. We also temporarily add print statements so that we can verify it
works.

```java
/**
 * Just as you cannot compare strings in C using '==' (you will be
 * comparing pointers) you should also not compare objects (Strings are objects)
 * in Java using '==', because this will check if it is the same instance of the
 * class, rather than whether the contents are equal! To compare the contents of
 * two objects, use the .equals() method
 */
if (input.equals("eat")) {
    System.out.println("Animal is eating");
} else if (input.equals("run")) {
    System.out.println("Animal is running");
}
```

If we now run our program and enter "run" or "eat" in the same terminal where
"Hello World!" was printed, it will print the corresponding message. Note that
it will only respond to the very first line you enter, since it reads one input
line and then stops (we do not have any loops yet).

## 4. Animal

Now it is time to describe an Animal. How are we going to do this? It makes
sense to store all this in one place. In C you might do this with a `struct`,
but since we are now using OOP, we have a better way: objects. In order to
describe such an object we create a class `Animal`. So let us create a new
file called `Animal.java` and, similar to the `Main` class, we put the
following code in that file:

```Java
/**
 * Models an animal with an age and energy level
 */
public class Animal {

}
```

### Fields

In this case, our `Animal` will only have two properties: a name and an energy
level. In other words, the `state` of our `Animal` is described solely by a
name and energy level. To describe these properties, we will add fields for
them. We can do this by simply declaring them inside the class:

```Java
public class Animal {

    private String name;
    private int energy;

}
```

Every class should be treated as a black box as much as possible. This means
that if we have a method or field we do not want (or need) other classes to
see, we hide it from those other classes. We can do this by adding the
keyword `private`. Fields should always be either `private` or `protected`
to preserve encapsulation. More information on information hiding can be found
in `Encapsulation` under the `OOP Concepts` section in the `Reader`.

### Constructor

Great! Now that our `Animal` has the state we want it to have, it is time to
add a constructor. A constructor matching the parameters is called when we make
an instance of the class (an object). We have already seen this with our line
about `Scanner`. When we call `new Scanner(System.in)`, the constructor of
`Scanner` is called with the argument `System.in`. A constructor can be used
for setting all properties. 

We do not want all our animals to have the same name and energy level, so those
will be parameters of our constructor. A constructor always has the same name
as the class. The constructor of animal would in this case be:

```Java
public class Animal {

    private String name;
    private int energy;

    /**
     * Constructor
     * Note that the constructor is public: we want to be able to make an
     * instance of this class from other classes
     *
     * @param name   The name we want the animal to have
     * @param energy The amount of initial energy we want the animal to have
     */
    public Animal(String name, int energy) {
        
    }

}
```

Now we can assign our fields as we would assign values to variables in C.
However, we see an issue here: both the parameter of the constructor and the
corresponding field have the same name. We call this issue *shadowing*. If we
now refer to `name`, it will think you are talking about `name` from the
constructor parameters.

How do we fix this? There are two ways: change the name of the parameter, or
use the keyword `this`. Using `this.name` will refer to the field `name` of the
class. Which of these two you use is up to you, but we recommend you get
familiar with both. Below, we show the two options, but you should be
consistent in which one you choose.

```Java
public class Animal {

    private String name;
    private int energy;

    /**
     * Constructor
     * Note that the constructor is public: we want to be able to make an
     * instance of this object from other classes
     *
     * @param animalName    The name we want the animal to have, note that we
     *                      changed the name to prevent shadowing
     * @param energy        The amount of initial energy we want the animal to have
     */
    public Animal(String animalName, int energy) {
        /* option 1: change the parameter name */
        name = animalName
        /* option 2: use the keyword 'this' */
        this.energy = energy;
    }
}
```

### Methods

Now that we have introduced the state of our class let us create the
`behaviour` part. As mentioned earlier, an `Animal` can do two things: running
and eating. We can do this by adding two method: `run()` and `eat()`.

- When `run()` is called, it should print "name: Running!" and decrease the
  energy level by 1. If the energy level is below 0, it should print "name: Out of energy!";
- When `eat()` is called, it should print "name: Eating!" and increase the
  energy level by 1.

Finally we also create a method `printHungerLevel()` that prints the energy
level to the screen.

So let us add these methods:

```Java
public class Animal {

    private String name;
    private int energy;

    /**
     * Constructor
     * Note that the constructor is public: we want to be able to make an
     * instance of this object from other classes
     *
     * @param name   The name we want the animal to have
     * @param energy The amount of initial energy we want the animal to have
     */
    public Animal(String name, int energy) {
        this.name = name
        this.energy = energy;
    }

    /** 
     * Makes the animal run 
     * Note that the method is public: we want to be able to call this method
     * from other classes 
     */
    public void run() {
        if (energy > 0) {
            System.out.println(name + ": Running!");
            energy--;
        } else {
            System.out.println(name + ": Out of energy!");
        }
    }

    /** 
     * Makes the animal eat 
     * Note that the method is public: we want to be able to call this method
     * from other classes 
     */
    public void eat() {
        System.out.println(name + ": Eating!");
        energy++;
    }

    /** 
     * Prints the hunger level
     * Note that the method is public: we want to be able to call this method
     * from other classes 
     */
    public void printHungerLevel() {
        System.out.println(name + " energy level: " + energy);
    }

}
```

Notice that the string concatenation is done using `+`. The line
`println(name + ": Eating!");"` would be written in C as
`printf("%s: Eating!\n", name);`.

Awesome! We just created the first proper class of this project.

## 5. Using the Animal class

Now let's move back to the main method in our `Main` class. We can create an
instance of an `Animal` with the following line:

```Java
Animal dog = new Animal("Dog", 6);
```

In Java you should always use the keyword `new` when instantiating objects.
This will create an `Animal` object with as its name "Dog" and an energy level
of 6. We are now able to access the class's methods using its variable name,
which is `dog` in this case. We can finally update the temporary print
statements now to what we actually want it to do:

```Java
if (input.equals("eat")) {
    /* Calls the eat() method of the object dog */
    dog.eat()
} else if (input.equals("run")) {
    /* Calls the run() method of the object dog */
    dog.run()
}
/* Print the hunger level to verify whether something changed */
dog.printHungerLevel();
```

## 6. Continuous input reading

Our program still only responds to the first line the user inputs. Let's fix
that! We can use a while loop to continuously scan the input:


```Java
while (scanner.hasNextLine()) {

    String input = scanner.nextLine();

    if (input.equals("eat")) {
        /* Calls the eat() method of the object dog */
        dog.eat()
    } else if (input.equals("run")) {
        /* Calls the run() method of the object dog */
        dog.run()
    }
    /* Print the hunger level to verify whether something changed */
    dog.printHungerLevel();

}

```

The `.hasNextLine()` method of `Scanner` will return true if there is a new
line in the input of `scanner`. Note that it waits for user input to arrive.
Now our program will keep reading our input. Verify that it works by running
the program and entering "eat" and "run" a few times. If everything works so
far, nice job!

## 7. Multiple animals

Since we do not want to clutter our main method with too much code, we are
going to create a method in the `Main` class that will instantiate a number of
animals. The method should return an array of `Animal`s.

The will lead to the following function definition:

```Java
private Animal[] initAnimals() {

}
```

Since we are only going to call this method from the `Main` class, we do not
need other classes to see this method. Therefore we can hide it from other
class by making it `private`. 

If we were to call this method from our main function now, IntelliJ will give
an error. The error will be along the lines of: 

> *non-static method cannot be referenced from static context*. 

Now what does this mean? Usually, methods can only be called on objects.
Remember that an object is an instance of a class. When our program runs, there
is no instance created of the `Main` class. This is also the reason why the
main method is always `static`. 
`static` methods are methods that can be called even if there is no instance of the
class. Since there is no instance of the class, `static` methods cannot operate
on the internal state of this class.

If this still sounds confusing, think of it as follows: dogs can run. The
following might seem obvious, but for a dog to run we actually need a dog.
Calling a regular method from a `static` context would be like making a dog run
without there being a dog. In other words, not possible :).

In this case, the method we want to create is only used for initialing, so we
can simply make this `static`. This leads us to the following method
definition:

```Java
private static Animal[] initAnimals() {

}
```

Great! Now we can do the actual implementation of this method. Similar to how
we defined a dog, we can also define other animals. When we have done this, we
create an array and put all the `Animal` objects in there. This means your
function should look something like this:

```Java
/**
 * Initialises an array of animals hardcoded in the program
 *
 * @return An array of animals
 */
private static Animal[] initAnimals() {
    /* Create a few different animals*/
    /* you can be very creative here */
    Animal dog = new Animal("Dog", 8);
    Animal ant = new Animal("Ant", 2);
    Animal lion = new Animal("Mufasa", 600);

    /* Put the animals in an array and return this array */
    Animal[] animals = {dog, ant, lion};
    return animals;
}
```

We can call this function in our main method using:

```Java
Animal[] animals = initAnimals();
```

What's left to do is loop over these animals so that whenever we enter "eat", all
the animals will eat (and similarly for run).

#### Looping over an array of animals

To do this we could use a simple for-loop, using the same syntax as the one you
are used to in C:

```Java
/* 
 * Loop over all the animals 
 * .length is a built-in property that returns an int number that matches the
 * number of items in an array
 */
for (int i = 0; i < animals.length; i++) {

    /* Retrieve the Animal at index i */
    Animal animal = animals[i];

    if (input.equals("eat")) {
        /* Calls the eat() method of the object Animal */
        animal.eat()
    } else if (input.equals("run")) {
        /* Calls the run() method of the object Animal */
        animal.run()
    }
    /* Print the hunger level to verify whether something changed */
    animal.printHungerLevel();
}
```

However, there is also a more sophisticated way of doing this. Many data
structures in Java support the Iterable-interface: Arrays, Lists, Sets and a
lot of others. In Java, and many other object-oriented languages, there's a
special loop that you can use for these Iterable data structures: the
`for-each` loop:

```Java
for (Animal animal : animals) {

    if (input.equals("eat")) {
        /* Calls the eat() method of the object Animal */
        animal.eat()
    } else if (input.equals("run")) {
        /* Calls the run() method of the object Animal */
        animal.run()
    }
    /* Print the hunger level to verify whether something changed */
    animal.printHungerLevel();
}
```

Now it will loop over the array. We can access the object at a specific
iteration with the variable name `animal`. IntelliJ will also give suggestions
about converting a classic `for` loop to a `for-each` loop if this is possible.
As you can see, this looks a lot nicer and, since it is equivalent, we will be
using this.

## Initialising animals with command-line arguments

> "The only constant in software development is change."

So let's create a new requirement: 

- The program must read names from the command-line arguments and initialise
  animals with those.

So how do we do this?

### Passing command-line arguments

If you compile and run from the command-line, this is very straightforward:

```bash
java -cp target/classes nl.rug.oop.introduction.Main Argument1 Argument2 "Argument 3"
```

However, if you want to run your program with command-line arguments in
IntelliJ, it is a bit less obvious:

- Right click on the green run button next to the main method in the top-right
  corner;
- Select `Edit 'Main.main()'...`;
- `Program arguments` specifies the arguments your program should be run with.
  Enter a few names here.

### Initialising with the argument names

Rather than rewriting the `initAnimals()` method, we want to create a new
method. This way, we can always use the other `initAnimals()` should this be
necessary.

However, the name `initAnimals()` is still a good name for our method. Lucky
for us, Java supports `overloading`!  "_What does this mean?_ 乁( ◔ ౪◔)ㄏ" I
hear you thinking. `Overloading` simply means that we can declare multiple
methods with the same name, but with different arguments. If we call the method
with a specific set of arguments, it will find which method you meant. 

It basically boils down to changing the behaviour details of a method based on
the arguments it receives. In the real world we could compare this to a person
talking. Obviously the way we talk to a friend is different from how we talk to
a stranger, but ultimately the process is still talking. This is incredibly
useful!

So let's define another `initAnimals()` method, but this time with `String[]
args` as its argument:

```Java
/**
 * Overloaded method initAnimals
 * Will initialise an array of Animals with the names provided in the arguments
 *
 * @param args The command-line arguments. Contains the names of the animals we
 *             want to initialise
 * @return An array of animals with names specified in args
 */
private static Animal[] initAnimals(String[] args) {
    
}
```

Let's first declare an array to store the animals in that we will be creating:

```Java
/* Initialise an array with as its size the number of Strings in the args */
Animal[] animals = new Animal[args.length];
```

Now that we have our array, we can loop over the arguments. Observe that we are
using a simple array. Therefore, we need to keep track of the index of where the `Animal` should be put in the array.
Because of this, a classic `for` loop serves us a bit better than a `for-each` loop, as the indices are directly given by the loop variable:

```Java
/* Use a regular for loop, since we need an index */
for (int i = 0; i < args.length; i++) {
    String name = args[i];
    /* initialise a new Animal with the name and ener.. wait, what do we do now? */
    animals[i] = new Animal(name, ...);
}
```

As you can see, there is a tiny issue here. We do not know the energy level of
the `Animal`...

For this assignment we will simply initialise it with a default value:

```Java
/* Use a regular for loop, since we need an index */
for (int i = 0; i < args.length; i++) {
    String name = args[i];
    /* initialise a new Animal with the name and an energy of 5 (That's better) */
    animals[i] = new Animal(name, 5);
}
```

What if, in the case that we only know the name, we always want to initialise
an `Animal` with energy level 5?  It would be quite annoying to always add that
5 to the arguments. Additionally, in the case that we do not want the energy
level to always be 5, but rather 10, we would have to change this everywhere in
our program. Which is surprisingly annoying. 

Lucky for us (again), Java supports not only overloading methods, but also
`constructor overloading`. Awesome! That appears to be exactly what we need!

So let's add an extra constructor in our `Animal` class. This constructor
should only take a name. There are two ways of implementing this. The first
option is by simply hardcoding 5 as the energy level:

```Java
    /**
     * Overloaded constructor
     *
     * @param name The name we want the animal to have
     */
    public Animal(String name) {
        this.name = name;
        this.energy = 5;
    }
```

The second option is by calling the first constructor (the constructor with
arguments `name` and `energy`) with the value we want it to have:

```Java
    /**
     * Overloaded constructor
     *
     * @param name The name we want the animal to have
     */
    public Animal(String name) {
        /* Calls the constructor above this one with a default energy level of 5 */
        this(name, 5);
    }
```

This is called `constructor chaining`: one constructor calls another
constructor. Whenever possible, pick the second option. Right now it may seem
as if it doesn't make much of a difference, but if your constructors start to
become a bit more complex, you will likely have a lot of code duplication if
you pick option 1.

So now we can call this constructor in the `Main` class: 
```Java
/* Use a regular for loop, since we need an index */
for (int i = 0; i < args.length; i++) {
    String name = args[i];

    /* initialise a new Animal with only a name; the energy level is defined in
       the constructor */
    animals[i] = new Animal(name);
}
```

The final thing that is left to do is actually calling the
`initAnimals(String[] args)` method. Now let's make use of the fact that we
overloaded our method:

- In the case that no arguments are provided, we call `initAnimals()` without
  arguments
- Otherwise, we call `initAnimals()` with the command-line arguments. 

Checking whether the user provided command-line arguments can be done by
looking at the length of argument list. If this is 0, no arguments were
provided.

So this means we have to alter the code in our main method slightly:

```Java
Animal[] animals;

if (args.length == 0) {
    /* No arguments were provided */
    animals = initAnimals();
} else {
    /* Initialise animals with names given in the command-line arguments */
    animals = initAnimals(args);
}
```

Now the entire `Main` class should look something like this: 

```Java
package nl.rug.oop.introduction;

import java.util.Scanner;

/**
 * Main class, used for reading input and initialising
 */
public class Main {

    /**
     * Initialises an array of animals hardcoded in the program
     *
     * @return An array of animals
     */
    private static Animal[] initAnimals() {
        /* Create a few different animals*/
        Animal dog = new Animal("Dog", 8);
        Animal ant = new Animal("Ant", 2);
        Animal lion = new Animal("Mufasa", 600);

        /* Put the animals in an array and return this array */
        Animal[] animals = {dog, ant, lion};
        return animals;
    }

    /**
     * Overloaded method initAnimals
     * Will initialise an array of Animals with the names provided in the
     * arguments
     *
     * @param args The command-line arguments. Contains the names of the
     *             animals we want to initialise
     * @return An array of animals with names specified in args
     */
    private static Animal[] initAnimals(String[] args) {
        /* Initialise an array with as its size the number of Strings in the args */
        Animal[] animals = new Animal[args.length];
        /* Use a regular for loop, since we need an index */
        for (int i = 0; i < args.length; i++) {
            String name = args[i];
            animals[i] = new Animal(name);
        }
        return animals;
    }

    /**
     * Main method
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Animal[] animals;

        if (args.length == 0) {
            /* No arguments were provided */
            animals = initAnimals();
        } else {
            /* Initialise animals with names given in the command-line arguments */
            animals = initAnimals(args);
        }

        while (scan.hasNextLine()) {

            String line = scan.nextLine();

            for (Animal animal : animals) {
                if (line.equals("eat")) {
                    /* Calls the eat() method of the object Animal */
                    animal.eat();
                } else if (line.equals("run")) {
                    /* Calls the run() method of the object Animal */
                    animal.run();
                }
                /* Print the hunger level to verify whether something changed */
                animal.printHungerLevel();
            }
        }
    }
}
```

Run it a few times and verify that it works. If so, well done!

One important note about this assignment:
We have done the majority of the work in the `Main` class. Normally, we want to
keep the `Main` class short and use it only for initialising. Therefore, if you
want to improve this code, you could start introducing extra classes (for
example, one for input reading). We do not expect you to do this, but it is
important to keep in mind.

# Handing in

That was it! By now, you should already have knowledge of quite a few OOP concepts!

If there are still things unclear, we would highly advise you to go over this
document again and read through the `OOP Concepts` section of the reader.
Obviously, you can also always ask your TA :).

Before handing in your program on GitHub, check that it can do the following:

- Continuous input scanning;
- Initialise animals with names and energy levels hardcoded in the `Main`
  class;
- Initialise animals with names given in the command-line arguments and a
  default energy level specified in a second constructor;
- If the user inputs "eat", the `eat()` method of all animals should be called;
  Eating should increase the energy level;
- If the user inputs "run", the `run()` method of all animals should be called.
  Running should decrease the energy level (not below 0!);
- For each input line, the hunger level of the animals should be printed.

If it can do all this, you can now hand in your program on GitHub. 

To do this, navigate to the directory of this assignment and execute the following
commands in your terminal:

- `git add .`
- `git commit -m "think of a useful commit message here"`
- `git push`

Your program should now be on GitHub. All that is left is to create a pull
request from `development` into the `master` branch and you are done. You can
do this by going to your repository on GitHub and selecting `New pull request`.
Make sure you set the comparison correctly. It should look like this:

`base: Master <- compare: development`

You can now (optionally) leave a comment and click on `Create pull request`.
Also make sure that your program passes CircleCI.

More information about this (with pictures!) can be found in the `Git & GitHub`
section of the `Reader`.
