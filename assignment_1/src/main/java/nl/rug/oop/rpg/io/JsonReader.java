package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.objects.*;
import nl.rug.oop.rpg.objects.doors.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

import static nl.rug.oop.rpg.io.JSONParser.*;

/**
 * Creates a json parsing class that allows custom map reading
 */
@SuppressWarnings("unchecked")
public class JsonReader {

    /**
     * Parses the rooms.json file and creates the corresponding rooms based off the json file
     * @param rooms Rooms
     * @throws IOException Wrong format
     */
    public static void parseRoomJSON(ArrayList<Room> rooms) throws IOException {
        JSONParser jsonParser = new JSONParser();
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream("rooms.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
        StringBuilder json = new StringBuilder();
        String temp;
        while((temp = bufferedReader.readLine()) != null) {
            json.append(temp);
        }
        bufferedReader.close();
        try {
            Object roomJSON = jsonParser.parse(json.toString());
            JSONArray roomArray = (JSONArray) roomJSON;
            roomArray.forEach(room -> parseRooms((JSONObject) room, rooms));
        } catch (ParseException e) {
            System.out.println("Unable to parse json file!");
        }
    }

    /**
     * Parses the doors.json file and creates an array of door json objects
     * @param rooms Rooms
     * @param doors Doors
     * @throws IOException Wrong format
     */
    public static void parseDoorJSON(ArrayList<Room> rooms, ArrayList<Door> doors) throws IOException {
        JSONParser jsonParser = new JSONParser();
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream("doors.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
        StringBuilder json = new StringBuilder();
        String temp;
        while((temp = bufferedReader.readLine()) != null) {
            json.append(temp);
        }
        bufferedReader.close();
        try {
            Object doorJSON = jsonParser.parse(json.toString());
            JSONArray doorArray = (JSONArray) doorJSON;
            doorArray.forEach(door -> parseDoors((JSONObject) door, rooms, doors));
        } catch (ParseException e) {
            System.out.println("Unable to parse json file!");
        }
    }

    /**
     * Parses the connections.json file and creates connection json objects
     * @param rooms Rooms
     * @param doors Doors
     * @throws IOException Wrong format
     */
    public static void parseConnectionJSON(ArrayList<Room> rooms, ArrayList<Door> doors) throws IOException {
        JSONParser jsonParser = new JSONParser();
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream("connections.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
        StringBuilder json = new StringBuilder();
        String temp;
        while((temp = bufferedReader.readLine()) != null) {
            json.append(temp);
        }
        bufferedReader.close();
        try {
            Object connectionJSON = jsonParser.parse(json.toString());
            JSONArray connectionArray = (JSONArray) connectionJSON;
            connectionArray.forEach(connection -> parseConnections((JSONObject) connection, rooms, doors));
        } catch (ParseException e) {
            System.out.println("Unable to parse json file!");
        }
    }

    /**
     * Parses the npcs.json file and creates an array of npc json objects
     * @param rooms Rooms
     * @param file File
     * @throws IOException Wrong format
     */
    public static void parseNPCJSON(ArrayList<Room> rooms, String file) throws IOException {
        JSONParser jsonParser = new JSONParser();
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
        StringBuilder json = new StringBuilder();
        String temp;
        while((temp = bufferedReader.readLine()) != null) {
            json.append(temp);
        }
        bufferedReader.close();
        try {
            Object npcJSON = jsonParser.parse(json.toString());
            JSONArray npcArray = (JSONArray) npcJSON;
            npcArray.forEach(npc -> parseNPCs((JSONObject) npc, rooms));
        } catch (ParseException e) {
            System.out.println("Unable to parse json file!");
        }
    }

    /**
     * Parses the items.json file and creates an arraylist of json item objects
     * @param rooms Rooms
     * @throws IOException Wrong format
     */
    public static void parseItemJSON(ArrayList<Room> rooms) throws IOException {
        JSONParser jsonParser = new JSONParser();
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream("items.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
        StringBuilder json = new StringBuilder();
        String temp;
        while((temp = bufferedReader.readLine()) != null) {
            json.append(temp);
        }
        bufferedReader.close();
        try {
            Object itemJSON = jsonParser.parse(json.toString());
            JSONArray itemArray = (JSONArray) itemJSON;
            itemArray.forEach(item -> parseItems((JSONObject) item, rooms));
        } catch (ParseException e) {
            System.out.println("Unable to parse json file!");
        }
    }

}

