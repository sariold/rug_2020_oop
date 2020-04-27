package nl.rug.oop.rpg;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class JsonReader {

    public static void main(String[] args) {
    }

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

    public static void parseRooms(JSONObject room, ArrayList<Room> rooms) {
        JSONObject roomObj = (JSONObject) room.get("room");
        String description = roomObj.get("description").toString();
        Room newRoom = new Room(description);
        rooms.add(newRoom);
    }

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

    public static void parseDoors(JSONObject door, ArrayList<Room> rooms, ArrayList<Door> doors) {
        JSONObject doorObj = (JSONObject) door.get("door");
        String description = doorObj.get("description").toString();
        Room from = rooms.get(Integer.parseInt(doorObj.get("from").toString().replace("r", "")));
        Room to = rooms.get(Integer.parseInt(doorObj.get("to").toString().replace("r", "")));
        Door newDoor = new Door(description, from, to);
        doors.add(newDoor);
    }

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

    public static void parseConnections(JSONObject connection, ArrayList<Room> rooms, ArrayList<Door> doors) {
        JSONObject connectionObj = (JSONObject) connection.get("room");
        String roomName = connectionObj.get("name").toString();
        JSONArray doorList = (JSONArray) connectionObj.get("doors");
        doorList.forEach(door -> {
            rooms.get(Integer.parseInt(roomName.replace("r", "")))
                    .addDoor((doors.get(Integer.parseInt(door.toString().replace("d", "")))));
        });
    }

}

