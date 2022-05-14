package main.game;

import main.challenges.Challenge;
import main.entities.Monster;
import main.entities.Player;
import main.locations.Castle;
import main.locations.Passage;
import main.objects.Item;
import main.utils.Generate;
import main.utils.Parameters;
import main.visibles.Map;
import main.visibles.Menu;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Method to manage the game.
 */
public class Game {
    /**
     * This method run the game.
     * @throws IOException if the file is not found.
     */
    public void run() throws IOException {

        Castle castle = Generate.castle(Parameters.FLOOR_SIZE); // Generate the castle
        Player player = new Player(); // Generate the player
        Map map = new Map(castle, player); // Generate the map

        player.spawn(castle); // Spawn the player in the castle
        player.getRoom().setVisited(); // Set the room as visited
        for(Passage passage : player.getRoom().getFloor().getPassageOfRoom(player.getRoom())) { // Set the passages as visited
            if(passage != null)
                passage.setVisited();
        }

        while (true){ // While the game is running (player can stop this loop if he go to the exit and choose to quit)
            map.show(); // Show the map
            player.showStats(); // Show the player stats

            if(player.getRoom().getRoomEvent() != null){
                if(player.getRoom().getRoomEvent() instanceof Challenge){ // If the room has a challenge
                    //TODO action si challenge dans la salle
                }
                if(player.getRoom().getRoomEvent() instanceof Item){ // If the room has an item
                    //TODO action si item dans la salle
                }
                if(player.getRoom().getRoomEvent() instanceof Monster){ // If the room has a monster
                    //TODO action si monstre dans la salle
                }
                player.getRoom().setRoomEvent(null); // Remove the room event
            }

            if(player.getRoom().isUpStairs() || player.getRoom().isDownStairs()) { // If the room has stairs
                int[] thisRoomCoords = player.getRoom().getRoomCoordinates(); // Get the room coordinates
                if (player.getRoom().isUpStairs()) { // If the room has an up stairs
                    Menu descendre = new Menu("Voulez-vous descendre d'un étage ?", new String[]{"Oui", "Non"},map); // Generate the menu
                    // Choose the option
                    if (descendre.choose() == 0) {// If the player choose to go down
                        int nextFloor = player.getRoom().getFloor().getFloorNumber() - 1; // Get the previous floor
                        player.setRoom(castle.getFloors()[nextFloor].getRooms()[thisRoomCoords[0]][thisRoomCoords[1]]); // Set the player room
                    }
                }else if (player.getRoom().isDownStairs()) { // If the room has a down stairs
                    Menu monter = new Menu("Voulez-vous Monter d'un étage ?", new String[]{"Oui", "Non"},map); // Generate the menu
                    // Choose the option
                    if (monter.choose() == 0) {// If the player choose to go up
                        int nextFloor = player.getRoom().getFloor().getFloorNumber() + 1; // Get the next floor
                        player.setRoom(castle.getFloors()[nextFloor].getRooms()[thisRoomCoords[0]][thisRoomCoords[1]]); // Set the player room
                    }
                }
                player.getRoom().setVisited(); // Set the room as visited
                for(Passage passage : player.getRoom().getFloor().getPassageOfRoom(player.getRoom())) { // Set the passages as visited
                    if(passage != null)
                        passage.setVisited();
                }
                map.show(); // Show the map
            }

            if(player.getRoom().isExit()) { // If the room is the exit
                Menu quitter = new Menu("Voulez-vous sortir du chateau ?", new String[]{"Oui", "Non"}, map); // Generate the menu
                quitter.choose(); // Choose the option
                if (quitter.choose() == 0) { // If the player choose to quit
                    System.out.println("Vous avez quitté le chateau");
                    break;
                }
            }

            Menu bouger = new Menu("Voulez-vous bouger ?", new String[]{"Oui", "Non"}, map); // Generate the menu
            // Choose the option
            if (bouger.choose() == 0) {// If the player choose to move
                Passage[] passages = player.getRoom().getFloor().getPassageOfRoom(player.getRoom());
                ArrayList<String> directions = new ArrayList<>(); // Generate the list of directions
                if (passages[0] != null) { // If the room has a passage to the west
                    directions.add("Ouest");
                } else {
                    directions.add("Pas de passage");
                }
                if (passages[1] != null) { // If the room has a passage to the east
                    directions.add("Est"); // Add the direction to the list
                } else {
                    directions.add("Pas de passage");
                }
                if (passages[2] != null) { // If the room has a passage to the north
                    directions.add("Nord"); // Add the direction to the list
                } else {
                    directions.add("Pas de passage");
                }
                if (passages[3] != null) { // If the room has a passage to the south
                    directions.add("Sud"); // Add the direction to the list
                } else {
                    directions.add("Pas de passage");
                }
                String[] directionsArray = directions.toArray(new String[0]); // Convert the list to an array
                Menu choix = new Menu("Ou voulez-vous aller ?", directionsArray,map); // Generate the menu
                int direction;
                do { // Choose the direction
                    direction = choix.choose();
                } while (passages[direction] == null); // While the passage is null
                int[] thisRoomCoords = player.getRoom().getRoomCoordinates();
                if (direction == 0) { // If the direction is west
                    player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0]][thisRoomCoords[1] - 1]); // Set the player room
                }
                if (direction == 1) { // If the direction is east
                    player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0]][thisRoomCoords[1] + 1]); // Set the player room
                }
                if (direction == 2) { // If the direction is north
                    player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0] - 1][thisRoomCoords[1]]); // Set the player room
                }
                if (direction == 3) { // If the direction is south
                    player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0] + 1][thisRoomCoords[1]]); // Set the player room
                }
            }
            player.getRoom().setVisited(); // Set the room as visited
            for(Passage passage : player.getRoom().getFloor().getPassageOfRoom(player.getRoom())) { // For each passage of the room
                if(passage != null)
                    passage.setVisited(); // Set the passage as visited
            }

        }
        player.getRoom().setVisited(); // Set the room as visited
        map.show(); // Show the map
    }
}
