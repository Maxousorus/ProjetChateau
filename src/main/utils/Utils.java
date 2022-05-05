package main.utils;

import main.locations.Castle;
import main.locations.Floor;
import main.locations.Passage;
import main.locations.Room;

/**
 * Utility class for various methods
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Utils {
    /**
     * This method set the player spawn in the groundfloor of the castle.
     * @param castle
     * @see Castle
     */
    public static void setCastleSpawn(Castle castle) {
            Floor stage0 = castle.getFloors()[0];
            int random = (int) (Math.random() * (Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE));
            Room[][] rooms = stage0.getRooms();
            int x = random/Parameters.FLOOR_SIZE;
            int y = random%Parameters.FLOOR_SIZE;
            rooms[x][y].setSpawn();
        }

    /**
     * This method set stairs in the castle.
     * One downstairs and one upstairs per floor.
     * @param castle
     * @see Castle
     */
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

    /**
     * This method set the exit of the castle at the last floor.
     * @param castle
     * @see Castle
     */
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

    /**
     * This method set the passages in the castle like a maze (kruskal algorithm).
     * @param castle
     * @see Castle
     */
    public static void setCastlePassages(Castle castle){
        Floor[] floors = castle.getFloors();
        for(Floor floor : floors){
            Passage[][] h_passages = new Passage[Parameters.FLOOR_SIZE][Parameters.FLOOR_SIZE-1];
            Passage[][] v_passages = new Passage[Parameters.FLOOR_SIZE-1][Parameters.FLOOR_SIZE];
            int[][][] passages = generateMaze();
            for(int i = 0; i < 2; i++){
                for(int x = 0; x < passages[i].length; x++){
                    for(int y = 0; y < passages[i][x].length; y++){
                        if(passages[i][x][y] == 0){
                            if(i == 0){
                                h_passages[x][y] = new Passage();
                            }else
                                v_passages[x][y] = new Passage();
                        }
                    }
                }
            }
            floor.setHorizontal_passage(h_passages);
            floor.setVertical_passage(v_passages);
        }
    }

    /**
     * This method generate a maze using kruskal algorithm.
     * @return the vertical and horizontal walls/passages of the maze.
     */
    private static int[][][] generateMaze(){
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
            int[] infosp;
            do {
                infosp = randomPassageDirection(grid, h_passages, v_passages);
            }while(infosp[0] == -1);
            if(infosp[0] == 0){
                h_passages[infosp[1]][infosp[2]] = 0;
            }else if(infosp[0] == 1){
                v_passages[infosp[1]][infosp[2]] = 0;
            }
            if(infosp[0] == 0){
                int x1 = infosp[1];
                int y1 = infosp[2];
                int x2 = infosp[1];
                int y2 = infosp[2]+1;
                mergeWays(grid,x1,y1,x2,y2);
            }else if(infosp[0] == 1){
                int x1 = infosp[1];
                int y1 = infosp[2];
                int x2 = infosp[1]+1;
                int y2 = infosp[2];
                mergeWays(grid,x1,y1,x2,y2);
            }
        }
        return new int[][][]{h_passages,v_passages};
    }

    /**
     * This method choose a random direction (horizontal or vertical) of the futur remove passage.
     * @param grid the grid of the maze (Room with index).
     * @param h_passages the horizontal passages of the maze.
     * @param v_passages the vertical passages of the maze.
     * @return the direction of the passage and the position of the passage.
     */
    private static int[] randomPassageDirection(int[][] grid, int h_passages[][], int v_passages[][]){
        if(Math.random() < 0.5) //mur horizontaux
            return randomPassage(grid,h_passages,true);

        else //mur verticaux
            return randomPassage(grid,v_passages,false);
    }

    /**
     * This method choose a random passage.
     * @param grid the grid of the maze (Room with index).
     * @param passages horizontal or vertical passages of the maze.
     * @param h true if the passages are horizontal, false if they are vertical.
     * @return the direction of the passage and the position of the passage.
     */
    private static int[] randomPassage(int[][] grid, int[][] passages, boolean h){
        int[] infos = new int[3]; //0 = h(0) ou v(1), 1 = x, 2 = y
        if(h)
            infos[0] = 0;
        else
            infos[0] = 1;

        int a, b, cooldown = -1;
        do {
            cooldown +=1;

            if (h) {
                a = (int) (Math.random() * Parameters.FLOOR_SIZE);
                b = (int) (Math.random() * Parameters.FLOOR_SIZE - 1);
            } else {
                a = (int) (Math.random() * Parameters.FLOOR_SIZE - 1);
                b = (int) (Math.random() * Parameters.FLOOR_SIZE);
            }
        }while(!isRemovablePassage(grid,passages,a,b,h) && cooldown >= 100);

        if(cooldown >= 100){
            return new int[]{-1,-1,-1};
        }

        if(h)
            return new int[]{0,a,b};
        else
            return new int[]{1,a,b};
    }

    /**
     * This method check if the passage is removable.
     * @param grid the grid of the maze (Room with index).
     * @param passages horizontal or vertical passages of the maze.
     * @param x the x position of the passage.
     * @param y the y position of the passage.
     * @param h true if the passages are horizontal, false if they are vertical.
     * @return true if the passage is removable, false otherwise.
     */
    private static boolean isRemovablePassage(int[][] grid, int[][] passages, int x, int y, boolean h){
        if(h){
            if(grid[x][y] == grid[x][y+1] )
                return false;
            else
                return true;
        }else {
            if(grid[x][y] == grid[x+1][y])
                return false;
            else
                return true;
        }
    }

    /**
     * This method merge two ways (a way is a group of room connected by passages).
     * This is the principle of the algorithm to create the maze.
     * @param grid the grid of the maze (Room with index).
     * @param x1 the x position of the first room to merge.
     * @param y1 the y position of the first room to merge.
     * @param x2 the x position of the second room to merge.
     * @param y2 the y position of the second room to merge.
     */
    private static void mergeWays(int[][] grid, int x1, int y1, int x2, int y2){
        int a = grid[x1][y1];
        int b = grid[x2][y2];

        for(int i = 0; i < Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE; i++){
            if(grid[i/Parameters.FLOOR_SIZE][i%Parameters.FLOOR_SIZE] == b)
                grid[i/Parameters.FLOOR_SIZE][i%Parameters.FLOOR_SIZE] = a;
        }
    }

    /**
     * This method check if the solving of the maze is finish or not.
     * A maze is finish if there is only one way in the maze.
     * @param grid the grid of the maze (Room with index).
     * @return true if the maze is finish, false otherwise.
     */
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

    /**
     * This method permite to generate a random integer between min and max.
     * @param min the minimum value of the random integer.
     * @param max the maximum value of the random integer.
     * @return the random integer.
     */
    public static int randomInt(int min, int max){
        //Formule mathématique qui retourne un chiffre aléatoire entre deux bornes passer en parametres
        return (int) (min + (Math.random() * (max - min)));
    }
}