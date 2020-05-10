# IMPORTANT READ ME

If the project is run in intelliJ with the project being the team folder, the savedgames and config folders will be generated outside the assignment_1 folder as result of 2020_Team_053 being the root folder.

This RPG project uses a maven dependency called
Google Simple JSON. In order to run this project with the maven dependency, it is crucial to either run the project inside of intelliJ where it will grab the external jars or to type

```
mvn clean package
```

This will generate a fat jar (jar with dependencies) under the folder target/ with the name rpg-jar-with-dependencies.jar

```
java -jar target/rpg-jar-with-dependencies.jar
```

# Have fun :D
