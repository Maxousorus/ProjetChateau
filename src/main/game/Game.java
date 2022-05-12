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
     * @throws IOException
     */
    public void run() throws IOException {

        Castle castle = Generate.castle(Parameters.FLOOR_SIZE);
        Map map = new Map(castle);
        Player player = new Player();

        player.spawn(castle);
        player.getRoom().setVisited();
        for(Passage passage : player.getRoom().getFloor().getPassageOfRoom(player.getRoom())) {
            if(passage != null)
                passage.setVisited();
        }

        while (!player.getRoom().isExit()){
            map.show(player.getRoom().getFloor().getFloorNumber(),player);
            player.showStats();
            if(player.getRoom().getRoomEvent() != null){
                if(player.getRoom().getRoomEvent() instanceof Challenge){
                    //TODO action si challenge dans la salle
                }
                if(player.getRoom().getRoomEvent() instanceof Item){
                    //TODO action si item dans la salle
                }
                if(player.getRoom().getRoomEvent() instanceof Monster){

                }
                player.getRoom().setRoomEvent(null);
            }
            if(player.getRoom().isUpStairs() || player.getRoom().isDownStairs()) {
                int[] thisRoomCoords = player.getRoom().getRoomCoordinates();
                if (player.getRoom().isUpStairs()) {
                    Menu descendre = new Menu("Voulez-vous descendre d'un étage ?", new String[]{"Oui", "Non"});
                    switch(descendre.choose()) {
                        case 0 -> {
                            int nextFloor = player.getRoom().getFloor().getFloorNumber() -1 ;
                            player.setRoom(castle.getFloors()[nextFloor].getRooms()[thisRoomCoords[0]][thisRoomCoords[1]]);
                        }
                    }
                }else if (player.getRoom().isDownStairs()) {
                    Menu monter = new Menu("Voulez-vous Monter d'un étage ?", new String[]{"Oui", "Non"});
                    switch(monter.choose()) {
                        case 0 -> {
                            int nextFloor = player.getRoom().getFloor().getFloorNumber() + 1;
                            player.setRoom(castle.getFloors()[nextFloor].getRooms()[thisRoomCoords[0]][thisRoomCoords[1]]);
                        }
                    }
                }
                player.getRoom().setVisited();
                for(Passage passage : player.getRoom().getFloor().getPassageOfRoom(player.getRoom())) {
                    if(passage != null)
                        passage.setVisited();
                }
                map.show(player.getRoom().getFloor().getFloorNumber(),player);
            }
            Menu bouger = new Menu("Voulez-vous bouger ?", new String[]{"Oui", "Non"});
            switch(bouger.choose()) {
                case 0 -> {
                    Passage[] passages = player.getRoom().getFloor().getPassageOfRoom(player.getRoom());
                    ArrayList<String> directions = new ArrayList<>();
                    if(passages[0] != null) {
                        directions.add("Ouest");
                    }else {
                        directions.add("Pas de passage");
                    }
                    if(passages[1] != null) {
                        directions.add("Est");
                    }else {
                        directions.add("Pas de passage");
                    }
                    if(passages[2] != null) {
                        directions.add("Nord");
                    }else {
                        directions.add("Pas de passage");
                    }
                    if(passages[3] != null) {
                        directions.add("Sud");
                    }else {
                        directions.add("Pas de passage");
                    }
                    String[] directionsArray = directions.toArray(new String[directions.size()]);
                    Menu choix = new Menu("Ou voulez-vous aller ?", directionsArray);
                    int direction;
                    do{
                        direction = choix.choose();
                    } while(passages[direction] == null);
                    int[] thisRoomCoords = player.getRoom().getRoomCoordinates();
                    if(direction == 0){
                        player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0]][thisRoomCoords[1]-1]);
                    };
                    if(direction == 1){
                        player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0]][thisRoomCoords[1]+1]);
                    }
                    if(direction == 2){
                        player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0]-1][thisRoomCoords[1]]);
                    }
                    if(direction == 3){
                        player.setRoom(player.getRoom().getFloor().getRooms()[thisRoomCoords[0]+1][thisRoomCoords[1]]);
                    }
                }
            }
            player.getRoom().setVisited();
            for(Passage passage : player.getRoom().getFloor().getPassageOfRoom(player.getRoom())) {
                if(passage != null)
                    passage.setVisited();
            }

        }
        player.getRoom().setVisited();
        map.show(player.getRoom().getFloor().getFloorNumber(),player);
    }
}
