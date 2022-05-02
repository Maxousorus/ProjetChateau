package main.utils;

import main.locations.Castle;
import main.locations.Floor;
import main.locations.Room;

public class Utils {

    public static void setCastleSpawn(Castle castle) {
            Floor stage0 = castle.getFloors()[0];
            int random = (int) (Math.random() * (Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE));
            Room[][] rooms = stage0.getRooms();
            int x = random/Parameters.FLOOR_SIZE;
            int y = random%Parameters.FLOOR_SIZE;
            rooms[x][y].setSpawn();
        }

    public static void setCastleStairs(Castle castle){
        Floor[] floors = castle.getFloors();
        for(int floor = 0; floor < castle.getFloors().length-1; floor++){

            int x = 0;
            int y = 0;
            do{
                int random = (int) (Math.random() * (Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE));
                x = random/Parameters.FLOOR_SIZE;
                y = random%Parameters.FLOOR_SIZE;
            }while(floors[floor].getRooms()[x][y].isSpawn() || floors[floor].getRooms()[x][y].isUpStairs());

            floors[floor].getRooms()[x][y].setDownStairs();
            floors[floor+1].getRooms()[x][y].setUpStairs();
        }
    }

    public static void setCastleExit(Castle castle){
        Floor[] floors = castle.getFloors();
        int dernieretage = floors.length-1;
        Floor laststage = floors[castle.getFloors().length-1];

        int x = 0;
        int y = 0;
        do{
            int random = (int) (Math.random() * (Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE));
            x = random/Parameters.FLOOR_SIZE;
            y = random%Parameters.FLOOR_SIZE;
        }while(floors[dernieretage].getRooms()[x][y].isSpawn() || floors[dernieretage].getRooms()[x][y].isUpStairs());

        laststage.getRooms()[x][y].setExit(true);
    }

    public static void generateMaze(){
        int FS = Parameters.FLOOR_SIZE;
        int[][] grid = new int[FS][FS];
        int[][] h_passages = new int[FS][FS-1];
        int[][] v_passages = new int[FS-1][FS];

        //Numérotation de chaque case de la grille
        for(int i = 0; i < FS * FS; i++){
            grid[i/FS][i%FS] = i;
        }

        //Bloquer tout les murs (mettre à 1) horizontaux
        for(int i = 0; i < FS * FS; i++){
            if((i+1)%FS != 0)
                h_passages[i/FS][i%FS] = 1;
        }

        //Bloquer tout les murs (mettre à 1) verticaux
        for(int i = 0; i < FS * FS; i++){
            if(i/FS != FS-1)
                v_passages[i/FS][i%FS] = 1;
        }

        //Boucle tant que le labyrinthe n'est pas terminé.
        while(!isMazeFinish(grid)){

        }
    }

    private static boolean isMazeFinish(int[][] grid){
        //Si toutes les grilles ont le même numéro retourne TRUE
        int FS = Parameters.FLOOR_SIZE;
        int verif = grid[0][0];
        for(int i = 0; i < FS * FS; i++) {
            if (grid[i / FS][i % FS] != verif)
                return false;
        }
        return true;
    }

    public static int randomInt(int min, int max){
        //Formule mathématique qui retourne un chiffre aléatoire entre deux bornes passer en parametres
        return (int) (min + (Math.random() * (max - min)));
    }
}
