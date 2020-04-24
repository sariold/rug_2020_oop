package nl.rug.oop.rpg;

public class Player {

    private String name;
    private Room currentRoom;

    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
    }

    public Player(String name) {
        this.name = name;
        this.currentRoom = null;
    }

    public String[] getPossibleMoves(){
        String[] options = new String[1];

        return options;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}