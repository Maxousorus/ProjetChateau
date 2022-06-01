package main.game;

import main.challenges.Sage;
import main.challenges.Trap;
import main.entities.Boss;
import main.entities.Entity;
import main.entities.Player;
import main.interfaces.CanBeInPassage;
import main.interfaces.CanBeInRoom;
import main.locations.Castle;
import main.locations.Passage;

import main.objects.Potion;
import main.objects.Weapon;
import main.options.GameStatistics;
import main.utils.Generate;
import main.options.Parameters;
import main.visibles.Map;
import main.visibles.Menu;
import main.visibles.Notification;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Method to manage the game.
 */
public class Game {

    /**
     * The Castle.
     */
    private Castle castle;
    /**
     * The Player.
     */
    private Player player;
    /**
     * The Map.
     */
    private Map map;

    private GameStatistics game_statistics;

    /**
     * Instantiates a new Game.
     */
    public Game(){
        this.castle = Generate.castle(Parameters.NUMBER_OF_FLOOR); // Generate the castle
        this.player = new Player(); // Generate the player
        this.map = new Map(player); // Generate the map*
        this.game_statistics = new GameStatistics(); // Generate the game statistics
    }

    public GameStatistics getGame_statistics() {
        return game_statistics;
    }

    /**
     * This method run the game.
     *
     * @throws IOException if the file is not found.
     */
    public void run() throws IOException {

        player.spawn(castle); // Spawn the player in the castle
        player.getRoom().setVisited(); // Set the room as visited
        game_statistics.addRoomVisited(); // Add the room visited to the statistics
        for(Passage passage : player.getRoom().getFloor().getPassageOfRoom(player.getRoom())) { // Set the passages as visited
            if(passage != null)
                passage.setVisited();
        }

        while (true){ // While the game is running (player can stop this loop if he goes to the exit and choose to quit)
            player.showInfos(); // Show the player stats

            if(player.getRoom().getRoomEvent() != null){
                CanBeInRoom event = player.getRoom().getRoomEvent();
                boolean runAway = false;
                switch (event.getClass().getSimpleName()){
                    case "Weapon" -> {
                        new Notification("You have found a Weapon, it's a " + ((Weapon)event).getName() +
                                " it deal " + ((Weapon)event).getDamage() + " damage", map).choose();
                        Menu weapon = new Menu("Do you want to take it ?", new String[]{"Yes", "No"}, map);
                        if(weapon.choose() == 0){
                            player.setWeapon((Weapon)event);
                            game_statistics.addWeaponsTaken();
                        }
                    }
                    case "Potion" -> {
                        player.setPv(player.getPv() + ((Potion)event).getPv());
                        if(player.getPv() > Parameters.PLAYER_MAX_HP) player.setPv(Parameters.PLAYER_MAX_HP);
                        new Notification("You have found a Potion, You received " + ((Potion)event).getPv() + "hp.", map).choose();
                        game_statistics.addPotionFound();
                    }
                    case "Entity" -> {
                        new Notification("You have encountered a Monster, it's a " + ((Entity)event).getName(), map).choose();
                        Fight fight = new Fight(player, (Entity)event, this);
                        switch (fight.fight()) {
                            case -1 -> { // Player died
                                new Notification("You died", map).choose();
                                game_statistics.addDeath();
                                game_statistics.scoreCalculation();
                                game_statistics.showGameStatistics();
                                return;
                            }
                            case 0 -> { // Player run away
                                new Notification("You run away", map).choose();
                                runAway = true;
                            }
                            case 1 -> { // Player won
                                new Notification("You won", map).choose();
                                game_statistics.addEnemyKilled();
                            }
                        }
                    }
                }
                if(!runAway) player.getRoom().setRoomEvent(null); // Remove the room event
                if(runAway) player.runAway(); // Run away
            }

            if(player.getRoom().isUpStairs() || player.getRoom().isDownStairs()) { // If the room has stairs
                int[] thisRoomCoords = player.getRoom().getRoomCoordinates(); // Get the room coordinates
                if (player.getRoom().isUpStairs()) { // If the room has an up stairs
                    Menu descendre = new Menu("Do you want to go at the previous floor ?", new String[]{"Yes", "No"},map); // Generate the menu
                    // Choose the option
                    if (descendre.choose() == 0) {// If the player choose to go down
                        int nextFloor = player.getRoom().getFloor().getFloorNumber() - 1; // Get the previous floor
                        player.setRoom(castle.getFloors()[nextFloor].getRooms()[thisRoomCoords[0]][thisRoomCoords[1]]); // Set the player room
                    }
                }else if (player.getRoom().isDownStairs()) { // If the room has a down stairs
                    Menu monter = new Menu("Do you want to go at the next floor ?", new String[]{"Yes", "No"},map); // Generate the menu
                    // Choose the option
                    if (monter.choose() == 0) {// If the player choose to go up
                        int nextFloor = player.getRoom().getFloor().getFloorNumber() + 1; // Get the next floor
                        player.setRoom(castle.getFloors()[nextFloor].getRooms()[thisRoomCoords[0]][thisRoomCoords[1]]); // Set the player room
                    }
                }
                if(!player.getRoom().isVisited()) {
                    player.getRoom().setVisited(); // Set the room as visited
                    game_statistics.addRoomVisited(); // Add the room visited to the statistics
                }
                for(Passage passage : player.getRoom().getFloor().getPassageOfRoom(player.getRoom())) { // Set the passages as visited
                    if(passage != null)
                        passage.setVisited();
                }
            }

            if(player.getRoom().isExit()) { // If the room is the exit
                Menu quitter = new Menu("Do you want to go out of the castle ?", new String[]{"Yes", "No"}, map); // Generate the menu
                if (quitter.choose() == 0) { // If the player choose to quit
                    new Notification("You are out of the castle !").choose(); // Display the notification

                    player.setPv(Parameters.PLAYER_MAX_HP);
                    new Notification("A fairy in a bottle is on your way and heal you !").choose();
                    new Notification("You received a book \"JAVA : By Hervé Martinez\", this book permit you to attack with Java Code !").choose();
                    player.initFinalsAttacks();

                    if(bossRoom()){
                        new Notification("You have defeated the boss !").choose();
                        game_statistics.addBossKilled();
                    }else {
                        new Notification("You have failed to defeat the boss !").choose();
                        game_statistics.addDeath();
                    }
                    game_statistics.scoreCalculation();
                    game_statistics.showGameStatistics();
                    return;
                }
            }

            Menu bouger = new Menu("Do you want to move ?", new String[]{"Yes", "No"}, map); // Generate the menu
            // Choose the option
            if (bouger.choose() == 0) {// If the player choose to move
                Passage[] passages = player.getRoom().getFloor().getPassageOfRoom(player.getRoom());
                ArrayList<String> directions = new ArrayList<>(); // Generate the list of directions
                if (passages[0] != null) { // If the room has a passage to the west
                    directions.add("West");
                } else {
                    directions.add("No Passage");
                }
                if (passages[1] != null) { // If the room has a passage to the east
                    directions.add("East"); // Add the direction to the list
                } else {
                    directions.add("No Passage");
                }
                if (passages[2] != null) { // If the room has a passage to the north
                    directions.add("North"); // Add the direction to the list
                } else {
                    directions.add("No Passage");
                }
                if (passages[3] != null) { // If the room has a passage to the south
                    directions.add("South"); // Add the direction to the list
                } else {
                    directions.add("No Passage");
                }
                String[] directionsArray = directions.toArray(new String[0]); // Convert the list to an array
                Menu choix = new Menu("Where do you want to go ?", directionsArray,map); // Generate the menu
                int direction;
                do { // Choose the direction
                    direction = choix.choose();
                } while (passages[direction] == null); // While the passage is null
                int[] thisRoomCoords = player.getRoom().getRoomCoordinates();
                if (direction == 0) { // If the direction is west
                    if(eventPassage(passages[direction])) { //Do the event of the passage
                        player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0]][thisRoomCoords[1] - 1]); // Set the player room
                    }
                }
                if (direction == 1) { // If the direction is east
                    if(eventPassage(passages[direction])) { //Do the event of the passage
                        player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0]][thisRoomCoords[1] + 1]); // Set the player room
                    }
                }
                if (direction == 2) { // If the direction is north
                    if (eventPassage(passages[direction])) { //Do the event of the passage
                        player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0] - 1][thisRoomCoords[1]]); // Set the player room
                    }
                }
                if (direction == 3) { // If the direction is south
                    if (eventPassage(passages[direction])) { //Do the event of the passage
                        player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0] + 1][thisRoomCoords[1]]); // Set the player room
                    }
                }
            }
            if(!player.getRoom().isVisited()) {
                player.getRoom().setVisited(); // Set the room as visited
                game_statistics.addRoomVisited(); // Add the room visited to the statistics
            }
            for(Passage passage : player.getRoom().getFloor().getPassageOfRoom(player.getRoom())) { // For each passage of the room
                if(passage != null)
                    passage.setVisited(); // Set the passage as visited
            }

        }
    }

    /**
     * Do the event of the passage if it has one
     * @return true if the player access to the next room, false otherwise
     */
    private boolean eventPassage(Passage passage) throws IOException {
        CanBeInPassage event = passage.getEvent(); // Get the event of the passage
        boolean passageAccess = false;
        if(event == null) { // If the passage has no event
            passageAccess = true; // Player can access to the next room
        }else {
            if(event instanceof Trap trap) { // If the event is a trap
                player.setPv(player.getPv() - (trap.getDamage()));
                new Notification("You have encountered a Trap, You received " + trap.getDamage() + "damages.",map).choose();
                game_statistics.addTrapFound();
                game_statistics.addDamageTaken(trap.getDamage());
                passageAccess = true;
            }else if(event instanceof Sage sage) { // If the event is a sage
                new Notification("You have encountered " +  sage.getName() + " the Sage !",map).choose();
                if(sage.questionSage() == 1) { // If the player have the good answer
                    new Notification("You have answered correctly !",map).choose();
                    game_statistics.addSageQuestionSolved(); // Add the question solved to the statistics
                    passageAccess = true;
                } else {
                    new Notification("You have answered incorrectly !",map).choose();
                }
            }
        }

        if(passageAccess) { // If the player can access to the next room
            passage.setEvent(null); // The event is removed in the passage
        }
        return passageAccess;
    }

    private Boolean bossRoom() throws IOException {

        Boss civodul = new Boss();
        return civodul.fight(player);

    }
}
