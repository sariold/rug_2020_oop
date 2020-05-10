# RPG + IO

In this assignment, you will be extending the RPG assignment with extra functionality. Before you start, make sure that you have finished all the requirements for RPG so far.
If your program already does everything it needs to do, try to improve the quality of the design and code as much as possible. It will be far easier to build upon a program with good design and well written code, than realising later on that you need to rewrite a large chunk of it.

We created a new `__assignment1_1` branch which you can merge into `development`. The only file that we added here is the new README for RPG+IO.

## 0 About Saving

In many programs we can save data. The program does this by writing certain information to a file. Abstractly speaking, it is converting program data into a structured form, so that it can be converted back into the program data later.
This conversion of program data to structured data (saving) is referred to as serialization. The reverse process (loading) is called de-serialization. There are some formats available that store information in an organized manner such as YAML, JSON or XML. However, many programming languages also support the saving or loading of data natively, among those is Java.

In this lab, we will make use of this native support for serialization to make save files. This way we can have (quick)saving and (quick)loading in our game (which is kinda awesome right?)!

## 1 Serialization in Java

Before we get started with saving, think about which class(es) you need to save in order to properly save the user's progress. 
In order to save our classes, we will need to use the `Serializable` interface. Later on, this allows us to convert Java objects into a stream of bytes that can be saved in a file. This stream of bytes can then be reconstructed to Java objects later on.
More specifically, we are saving the state of our objects, since this is all we are interested in.

Any class that we want to save should implement this `Serializable` interface. If these classes already implement an interface, do not worry, since Java supports having multiple interfaces (unlike multiple inheritance).
The nice thing about the `Serializable` interface is that we do not have to implement any methods of this interface. It acts as a "marker" or "flag" for our classes that indicates to the JVM that this class can be serialized.

When our class implements `Serializable` we can eventually use the `ObjectOutputStream` class to write the data of our object.
However, before we can do this, each class that implements `Serializable` should have a `serialVersionUID` field:

```java
/* 42L is just an example, this ID should be different for each class! */
private static final long serialVersionUID = 42L;
```

This field is stored together with the other data of our object whenever we save. It is then used during loading to verify the class we try to load is indeed the same class as the one we saved. Therefore, this ID **must be unique**. 

If we make a class implement `Serializable`, all of its fields should be `Serializable`. If we would try to serialize a class with fields that cannot be serialized (for example `Scanner`), it will throw a `NotSerializableException`:

```
java.io.NotSerializableException: java.util.Scanner
```

How can we prevent this error? Whenever there is a field that we do not want or need to serialize, we can define this field as `transient`:

```Java
/* This field will not be serialized */
private transient Scanner scanner;
```

Fields that refer to local resources such as IO streams and `Scanner` are not serialized, because the serialized objects can also be used for network communication. If we then de-serialize these IO related fields, they would not be valid on a different machine.

The serialization process is fully recursive. This means that if the class you want to save contains a (non-transient) field that references another class, you need to ensure this class implements `Serializable` as well!

**Requirements:**

- Make every class you want to save implement `Serializable`.
- Give every class that implements `Serializable` a unique `serialVersionUID`.

### 1.1 New interact options

Before we start with the actual saving and loading, we are first going to add two options to the interaction menu: `QuickSave` and `QuickLoad`.
This will look something like this:

```
What do you want to do? 
  (0) Look around 
  (1) Look for a way out 
  (2) Look for company 
  (3) QuickSave 
  (4) QuickLoad 
```

For now these actions do not need to do anything, but we are going to use them in a bit.

**Requirements:**

- Add an option `QuickSave` to the interaction menu.
- Add an option `QuickLoad` to the interaction menu.

### 1.1 Quicksaving and Quickloading

Now that we have set up our classes to be saved, it is time to move on to the actual saving part. You will have to decide for yourself where to put the code for this. Here you should keep in mind that your classes should not be responsible for too many things at once.

The very first thing that we have to do is set up a directory in which we will place our files. However, we do not want to do this manually, but rather let our program take care of this. That way, our program does not break if the user removes this directory. 
We want to store our saves in a folder called `savedgames` and this folder should be located in the root of this assignment folder.
For this we can use Java's `File` class. This class is an abstract representation of file and directory pathnames, so we can use this as a representation for both files and directories.
We can create such an object using the following:

```Java
/* will create a File object that refers to the location "savedgames" in the root of your project folder */
File saveDirectory = new File("savedgames");
```

Now we need to ensure that this directory is created if it does not exist yet. We can use the following for this:

```Java
saveDirectory.mkdir();
```

This will create the directory if it does not exist and do nothing if it already exists. This method returns a boolean value that indicates whether the creation of the directory was successful. If the directory already exists, it will return false. Since we do not really care about this, we can ignore this value for now.

Whenever the user chooses the option `QuickSave`, the program should save the state of the game in a file called `quicksave.ser` inside of this `savedgames` directory. 
For this you will need the classes [`FileOutputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/FileOutputStream.html) and [`ObjectOutputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectOutputStream.html).

Whenever the user chooses the option `QuickLoad`, the program should load the state of the game as it was saved in the file `quicksave.ser`.
For this you will need the classes [`FileInputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html) and [`ObjectInputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectInputStream.html).

The result of this serialization process is a stream of bytes (VERY DIFFICULT TO READ FOR US HUMANS [-c°▥°]-c). Therefore we cannot easily verify whether saving was successful, so we will have to implement both saving and loading before we can verify that it works.

We need to make sure that we give appropriate feedback to the user if something went wrong (for example if the file does not exist). For this a simple print statement should be enough. While printing the stack trace might be very useful for debugging, we do not want this in our final program, since we do not want our users to see this. Always give proper feedback to the user if something goes wrong.

The details about how to save and load Objects are explained in the lecture about serialization.

**Requirements:**

- Save games should be stored in a directory called `savedgames` at the root of the assignment directory.
- The `savedgames` directory should be created if it does not exist yet.
- Choosing `QuickSave` should save the game in a file called `quicksave.ser`.
- Choosing `QuickLoad` should load the game as it was saved in the file `quicksave.ser`
- If something goes wrong during saving or loading, the program should print an informative message to the user.

## 1.2 Functionality Warning

It could very well be that something broke because of the saving and loading process. Therefore, be sure to verify that all your program features still work after loading a game.

## 2 Named save and load files

Obviously we also want to give have the option to give our savegames custom names. Similarly, we also want to load these files.
So let's add two more options to our interaction menu: `Save` and `Load` (an example of the extended user interface can be found below).

Choosing the option `Save` should ask the user for a name for their new savegame. Note that the `.ser` extension should not be included in this filename. 
Since the user can enter basically anything, we need to ensure that the provided filename satisfies some constraints (think of some yourself, just make sure the filename itself cannot produce an error). If there is an error, we need to give the user appropriate feedback about the file name (e.g. does the filename already exist? is it too long? forbidden characters?). 
If there is no error with the filename, the program should save the game into the savefile with the name the user provided.

Choosing the option `Load` should display a list of savefiles in the `savedgames` directory. The user can then pick one of these options and the program should load that savefile. 
It should only list the files with the `.ser` extension. The `quicksave.ser` file should also be included in this list.

When implementing these two options, try to prevent code duplication with quicksave and quickload.

The interaction menu now could look something like this:

```
What do you want to do? 
  (0) Look around 
  (1) Look for a way out 
  (2) Look for company 
  (3) QuickSave 
  (4) QuickLoad 
  (5) Save 
  (6) Load 
5 
Filename? 
room1 
Save successful
What do you want to do?
  (0) Look around 
  (1) Look for a way out 
  (2) Look for company 
  (3) QuickSave 
  (4) QuickLoad 
  (5) Save 
  (6) Load 
3 
Quicksave successful
What do you want to do? 
  (-1) Give up 
  (0) Look around 
  (1) Look for a way out 
  (2) Look for company 
  (3) QuickSave 
  (4) QuickLoad 
  (5) Save 
  (6) Load 
6 
Which file? (-1 : none) 
  (0) room1.ser 
  (1) quickSave.ser
0
Load successful
```

**Requirements:**

- Add an option `Save`.
- Add an option `Load`.
- Choosing `Save` should save the game in a file whose name is provided by the user.
- Choosing `Load` should display a list of savefiles to the user. It should then load the file selected by the user.

## 3 Input Configuration

A lot of games use initialisation files to initialise the games in a particular way. We are going to do something similar, although a bit simpler.
There are all kinds of file types that we could use for this such as YAML, JSON or XML. However, for this assignment we will be using Java Properties, since we do not need any (external)libraries for it.
The downside to using Java Properties is that it does not allow us to customise everything, since it does not natively support nested values (you could emulate this by adding prefixes to the key names). However, for the purpose of the assignment, we will not need that.

## 3.1 More Interact Options

Before we get started, we are going to add a small separate menu that should be shown to the user when they start the game.
This menu should consist of three options: `Play normally`, `Initialise from config`, `Set default config`. This will look something like this:

```
You are about to start the game, what do you want to do?
  (0) Play Normally
  (1) Initialise from config
  (2) Set default config
```

Choosing option 0 should initialise the game as before
Choosing option 1 should initialise the game using the properties in the config file
Choosing option 2 should set the properties in the config file.

**Requirements:**

- Add an interaction menu that is shown to the user before starting a game.
- This menu should contain three options: `Play normally`, `Initialise from config`, `Set default config`.

## 3.2 Java Properties

Java Properties are configuration values in the form of key-value pairs. In each pair, both the key and the value are String values. We can use a key to retrieve the corresponding value. Java Properties can be nicely saved to a stream, which in turn allows us to save them to a file.

We are going to create a file in which we can store some properties of the `Player` class. By now, your `Player` class should have at least the fields `name`, `health` and `damage`. We are going to initialise these fields using our configuration file. Note that this is simply an example. You can choose any class (or multiple classes) for which you want to initialise properties, as long as at least 5 properties can be initialised from this configuration file.

Similar to the savefiles, we want to store our configuration files in a separate directory. This directory should be called `config` and should also be stored in the root of this assignment directory.

**Requirements:**

- Config files be stored in a directory called `config` at the root of the assignment directory.
- The `config` directory should be created if it does not exist yet.

## 3.3 Setting Properties

We do not want to create this config file manually, so we are going to write a method that can do this for us.

The first thing we need to do when creating this config file is create a `Properties` object:

```Java
Properties rpgProperties = new Properties();
```

We will use this object to store our key-value pairs.

Next we can set a property, such as the name of a player, using:

```Java
rpgProperties.setProperty("playerName", "Howard the Duck")
```

Since Java Properties only uses Strings, we can add integers by simply doing it in String notation (e.g. "2").

Once we are done with setting all our properties, we can store it to a file (for example `rpgConfig.properties`). 
For this, we will have to initialise a `FileOutputStream` (similar to saving). We can then save the properties to this stream using the `store()` method of the `Properties` class. This method takes as its first argument a `FileOutputStream` and as a second argument a `String`. This `String` will be a comment that appears at the top of the config file:

```Java
rpgProperties.store(fileOutputStream, "These are the properties of a player in the RPG game");
```

Now we are done with saving our properties! When the user selects option 2 from the start menu, it should save these properties. When this method runs, it should create a `config` directory(if it does not exist yet) and add a file called `rpgConfig.properties` in it. You can open this file and see if everything was stored correctly.

**Requirements:**

- Create a method that stores properties that can be used to initialise a class.
- At least 5 different properties should be stored in the properties file.

## 3.4 Loading Properties

Now that we have saved the properties, we can move on to loading them ٩(◕‿◕｡)۶.

Since we first need to read from the config file, we are going to need a `FileInputStream` (similar to loading).
Once we have this, we can load the contents from this file into our `Properties` object using the `load()` method:

```Java
rpgProperties.load(fileInputStream);
```

Now all the properties should be in our `rpgProperties` object. We can retrieve these properties using:

```Java
String playerName = rpgProperties.getProperty("playerName");
```

Here the argument of `getProperty()` is the key of the key-value pair.
If you stored any integers, these will also be retrieved as strings, so they will have to be converted to integers manually. This can be done using:

```
int n = Integer.parseInt(numberString);
```

Now we have all our fancy properties and we can use these to initialise a `Player` object!
When the user selects option 1 from the start menu, it should run this method.
As always, ensure proper error handling.

**Requirements:**

- Create a method that loads properties from a config file.
- The properties should in the config file should be used to initialise a class (or multiple classes).

## 3.5 Extra

You can obviously go completely ham with this and create a way bigger configuration file. You could also create multiple config files so that different types of games can be played. Feel free to play around with this!

## 4 Handing in

Before you hand in, remember that you should not have empty try/catch statements! An empty try/catch is extremely bad practice, because you are hiding the fact that an error occurred and continuing execution as normal. It is basically equivalent to putting a piece of tape on an engine warning light. If something goes wrong, you have no idea where it went wrong and why, so you are strongly recommended to inform the user if something goes wrong. In the case that an exception does not represent a bug, you should likely not be using a try/catch for it. 

When you are finished, create a pull request from the development branch into the master branch and confirm that your program passes CircleCI.