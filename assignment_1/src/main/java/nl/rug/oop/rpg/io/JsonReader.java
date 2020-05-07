package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.npcs.enemies.*;
import nl.rug.oop.rpg.npcs.healer.HighPriest;
import nl.rug.oop.rpg.npcs.healer.Priest;
import nl.rug.oop.rpg.npcs.trader.*;
import nl.rug.oop.rpg.objects.*;
import nl.rug.oop.rpg.objects.doors.*;
import nl.rug.oop.rpg.objects.items.GoldNugget;
import nl.rug.oop.rpg.objects.items.HealingPotion;
import nl.rug.oop.rpg.objects.items.MagicOrb;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

/**
 * Creates a json parsing class that allows custom map reading
 */
public class JsonReader {

    public static void main(String[] args) {
    }

    /**
     * Parses the rooms.json file and creates the corresponding rooms based off the json file
     * @param rooms
     * @throws IOException
     * @throws ParseException
     */
    public static void parseRoomJSON(ArrayList<Room> rooms) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream("rooms.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String json = "";
        String temp = "";
        while((temp = bufferedReader.readLine()) != null) {
            json = json + temp;
        }
        try {
            Object roomJSON = jsonParser.parse(json);
            JSONArray roomArray = (JSONArray) roomJSON;
            roomArray.forEach(room -> parseRooms((JSONObject) room, rooms));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a json object for each room item in the rooms array and creates custom rooms
     * @param room
     * @param rooms
     */
    public static void parseRooms(JSONObject room, ArrayList<Room> rooms) {
        JSONObject roomObj = (JSONObject) room.get("room");
        String description = roomObj.get("description").toString();
        Room newRoom = new Room(description);
        rooms.add(newRoom);
    }

    /**
     * Parses the doors.json file and creates an array of door json objects
     * @param rooms
     * @param doors
     * @throws IOException
     */
    public static void parseDoorJSON(ArrayList<Room> rooms, ArrayList<Door> doors) throws IOException {
        JSONParser jsonParser = new JSONParser();
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream("doors.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String json = "";
        String temp = "";
        while((temp = bufferedReader.readLine()) != null) {
            json = json + temp;
        }
        try {
            Object doorJSON = jsonParser.parse(json);
            JSONArray doorArray = (JSONArray) doorJSON;
            doorArray.forEach(door -> parseDoors((JSONObject) door, rooms, doors));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transforms each door json object into a type of door and sets up the connecting rooms
     * @param door
     * @param rooms
     * @param doors
     */
    public static void parseDoors(JSONObject door, ArrayList<Room> rooms, ArrayList<Door> doors) {
        JSONObject doorObj = (JSONObject) door.get("door");
        String description = doorObj.get("description").toString();
        String type = doorObj.get("type").toString();
        Room from = rooms.get(Integer.parseInt(doorObj.get("from").toString().replace("r", "")));
        if(type.equals("Trap")) {
            TrapDoor newDoor = new TrapDoor(description, from, 5);
            doors.add(newDoor);
            return;
        }
        Room to = rooms.get(Integer.parseInt(doorObj.get("to").toString().replace("r", "")));
        if(type.equals("Monster")) {
            MonsterDoor newDoor = new MonsterDoor(description, from, to);
            doors.add(newDoor);
        } else if(type.equals("MiniBoss")) {
            String wizardType = "Red";
            if(description.equals("Blue")) wizardType = "Blue";
            MiniBossDoor newDoor = new MiniBossDoor(description, from, to, wizardType, false);
            doors.add(newDoor);
        } else if(type.equals("FinalBoss")) {
            FinalBossDoor newDoor = new FinalBossDoor(description, from, to);
            doors.add(newDoor);
        } else {
            Door newDoor = new Door(description, from, to);
            doors.add(newDoor);
        }
    }

    /**
     * Parses the connections.json file and creates connection json objects
     * @param rooms
     * @param doors
     * @throws IOException
     */
    public static void parseConnectionJSON(ArrayList<Room> rooms, ArrayList<Door> doors) throws IOException {
        JSONParser jsonParser = new JSONParser();
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream("connections.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String json = "";
        String temp = "";
        while((temp = bufferedReader.readLine()) != null) {
            json = json + temp;
        }
        try {
            Object connectionJSON = jsonParser.parse(json);
            JSONArray connectionArray = (JSONArray) connectionJSON;
            connectionArray.forEach(connection -> parseConnections((JSONObject) connection, rooms, doors));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses each connection json object and adds the doors to the room arraylist of doors
     * @param connection
     * @param rooms
     * @param doors
     */
    public static void parseConnections(JSONObject connection, ArrayList<Room> rooms, ArrayList<Door> doors) {
        JSONObject connectionObj = (JSONObject) connection.get("room");
        String roomName = connectionObj.get("name").toString();
        JSONArray doorList = (JSONArray) connectionObj.get("doors");
        doorList.forEach(door -> {
            rooms.get(Integer.parseInt(roomName.replace("r", "")))
                    .addDoor((doors.get(Integer.parseInt(door.toString().replace("d", "")))));
        });
    }

    /**
     * Parses the npcs.json file and creates an array of npc json objects
     * @param rooms
     * @param file
     * @throws IOException
     */
    public static void parseNPCJSON(ArrayList<Room> rooms, String file) throws IOException {
        JSONParser jsonParser = new JSONParser();
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String json = "";
        String temp = "";
        while((temp = bufferedReader.readLine()) != null) {
            json = json + temp;
        }
        try {
            Object npcJSON = jsonParser.parse(json);
            JSONArray npcArray = (JSONArray) npcJSON;
            npcArray.forEach(npc -> parseNPCs((JSONObject) npc, rooms));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses each npc json object and creates a specific type of npc, either an enemy or a friendly npc
     * @param npc
     * @param rooms
     */
    public static void parseNPCs(JSONObject npc, ArrayList<Room> rooms) {
        JSONObject npcObj = (JSONObject) npc.get("npc");
        String type = npcObj.get("type").toString();
        String name = npcObj.get("name").toString();
        int roomNumber = Integer.parseInt(npcObj.get("room").toString().replace("r", ""));
        switch(type) {
            case "Spider":
                Spider spider = new Spider(name);
                rooms.get(roomNumber).addNPC(spider);
                break;
            case "Snake":
                Snake snake = new Snake(name);
                rooms.get(roomNumber).addNPC(snake);
                break;
            case "Knight":
                Knight knight = new Knight(name);
                rooms.get(roomNumber).addNPC(knight);
                break;
            case "Orc":
                Orc orc = new Orc(name);
                rooms.get(roomNumber).addNPC(orc);
                break;
            case "Rat":
                Rat rat = new Rat(name);
                rooms.get(roomNumber).addNPC(rat);
                break;
            case "HighPriest":
                HighPriest highPriest = new HighPriest(name);
                rooms.get(roomNumber).addNPC(highPriest);
                break;
            case "Priest":
                Priest priest = new Priest(name);
                rooms.get(roomNumber).addNPC(priest);
                break;
            case "Gambler":
                Gambler gambler = new Gambler(name);
                rooms.get(roomNumber).addNPC(gambler);
                break;
            case "ArmorSmith":
                ArmorSmith armorSmith = new ArmorSmith(name);
                rooms.get(roomNumber).addNPC(armorSmith);
                break;
            case "WeaponSmith":
                WeaponSmith weaponSmith = new WeaponSmith(name);
                rooms.get(roomNumber).addNPC(weaponSmith);
                break;
            case "StrongArmorSmith":
                StrongArmorSmith strongArmorSmith = new StrongArmorSmith(name);
                rooms.get(roomNumber).addNPC(strongArmorSmith);
                break;
            case "StrongWeaponSmith":
                StrongWeaponSmith strongWeaponSmith = new StrongWeaponSmith(name);
                rooms.get(roomNumber).addNPC(strongWeaponSmith);
                break;
            case "Enchanter":
                Enchanter enchanter = new Enchanter(name);
                rooms.get(roomNumber).addNPC(enchanter);
                break;
        }
    }

    /**
     * Parses the items.json file and creates an arraylist of json item objects
     * @param rooms
     * @throws IOException
     */
    public static void parseItemJSON(ArrayList<Room> rooms) throws IOException {
        JSONParser jsonParser = new JSONParser();
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream("items.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String json = "";
        String temp = "";
        while((temp = bufferedReader.readLine()) != null) {
            json = json + temp;
        }
        try {
            Object itemJSON = jsonParser.parse(json);
            JSONArray itemArray = (JSONArray) itemJSON;
            itemArray.forEach(item -> parseItems((JSONObject) item, rooms));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the item jsob object and creates a specific type of dungeon object item
     * @param item
     * @param rooms
     */
    public static void parseItems(JSONObject item, ArrayList<Room> rooms) {
        JSONObject itemObj = (JSONObject) item.get("item");
        String type = itemObj.get("type").toString();
        int roomNumber = Integer.parseInt(itemObj.get("room").toString().replace("r", ""));
        switch(type) {
            case "GoldNugget":
                GoldNugget goldNugget = new GoldNugget();
                rooms.get(roomNumber).addItem(goldNugget);
                break;
            case "HealingPotion":
                HealingPotion healingPotion = new HealingPotion();
                rooms.get(roomNumber).addItem(healingPotion);
                break;
            case "MagicOrb":
                int endRoomNumber = Integer.parseInt(itemObj.get("endRoom").toString()
                        .replace("r", ""));
                MagicOrb magicOrb = new MagicOrb(rooms.get(endRoomNumber));
                rooms.get(roomNumber).addItem(magicOrb);
                break;
        }
    }

}

