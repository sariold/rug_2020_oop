package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.npcs.enemies.*;
import nl.rug.oop.rpg.npcs.healer.HighPriest;
import nl.rug.oop.rpg.npcs.healer.Priest;
import nl.rug.oop.rpg.npcs.trader.*;
import nl.rug.oop.rpg.objects.Room;
import nl.rug.oop.rpg.objects.doors.*;
import nl.rug.oop.rpg.objects.items.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Parses JSON objects provided from JSONReader class
 */
@SuppressWarnings("unchecked")
public class JSONParser {

    /**
     * Parses the item jsob object and creates a specific type of dungeon object item
     * @param item Item
     * @param rooms Rooms
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
            case "AttackPotion":
                AttackPotion attackPotion = new AttackPotion();
                rooms.get(roomNumber).addItem(attackPotion);
                break;
            case "MonsterKey":
                MonsterKey monsterKey = new MonsterKey();
                rooms.get(roomNumber).addItem(monsterKey);
                break;
        }
    }

    /**
     * Parses each npc json object and creates a specific type of npc, either an enemy or a friendly npc
     * @param npc Npc
     * @param rooms Rooms
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
     * Parses each connection json object and adds the doors to the room arraylist of doors
     * @param connection Connection
     * @param rooms Rooms
     * @param doors Doors
     */
    public static void parseConnections(JSONObject connection, ArrayList<Room> rooms, ArrayList<Door> doors) {
        JSONObject connectionObj = (JSONObject) connection.get("room");
        String roomName = connectionObj.get("name").toString();
        JSONArray doorList = (JSONArray) connectionObj.get("doors");
        doorList.forEach(door -> rooms.get(Integer.parseInt(roomName.replace("r", "")))
                .addDoor((doors.get(Integer.parseInt(door.toString().replace("d", ""))))));
    }

    /**
     * Creates a json object for each room item in the rooms array and creates custom rooms
     * @param room Room
     * @param rooms Rooms
     */
    public static void parseRooms(JSONObject room, ArrayList<Room> rooms) {
        JSONObject roomObj = (JSONObject) room.get("room");
        String description = roomObj.get("description").toString();
        Room newRoom = new Room(description);
        rooms.add(newRoom);
    }

    /**
     * Transforms each door json object into a type of door and sets up the connecting rooms
     * @param door Door
     * @param rooms Rooms
     * @param doors Doors
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
        switch (type) {
            case "Monster": {
                MonsterDoor newDoor = new MonsterDoor(from, to, false);
                doors.add(newDoor);
                break;
            }
            case "MiniBoss": {
                String wizardType = "Red";
                if (description.equals("Blue")) wizardType = "Blue";
                MiniBossDoor newDoor = new MiniBossDoor(from, to, wizardType, false);
                doors.add(newDoor);
                break;
            }
            case "FinalBoss": {
                FinalBossDoor newDoor = new FinalBossDoor(from, to);
                doors.add(newDoor);
                break;
            }
            default: {
                Door newDoor = new Door(description, from, to);
                doors.add(newDoor);
                break;
            }
        }
    }
}
