package main.utils;

import main.locations.Castle;
import main.locations.Floor;
import main.locations.Passage;
import main.locations.Room;
import main.options.Parameters;

/**
 * Utility class for various methods.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias.
 */
public class Utils {
    /**
     * This method set the player spawn in the ground floor of the castle.
     *
     * @param castle the castle where the player will be spawn.
     */
    public static void setCastleSpawn(Castle castle) {
        Floor stage0 = castle.getFloors()[0]; //ground floor
        int random = (int) (Math.random() * (Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE));
        Room[][] rooms = stage0.getRooms(); //rooms of the ground floor
        int x = random / Parameters.FLOOR_SIZE;
        int y = random % Parameters.FLOOR_SIZE;
        rooms[x][y].setSpawn(); //Set the spawn at the random room of the ground floor
    }

    /**
     * This method set stairs in the castle.
     * One downstairs and one upstairs per floor.
     *
     * @param castle the castle where the stairs will be set
     */
    public static void setCastleStairs(Castle castle) {
        Floor[] floors = castle.getFloors(); //get floors of the castle
        for (int floor = 0; floor < castle.getFloors().length - 1; floor++) { //for each floor

            int x;
            int y;
            do { //choose a random room
                int random = (int) (Math.random() * (Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE));
                x = random / Parameters.FLOOR_SIZE;
                y = random % Parameters.FLOOR_SIZE;
            } while (floors[floor].getRooms()[x][y].isSpawn() || floors[floor].getRooms()[x][y].isUpStairs()); //while the room is the spawn or the upstairs

            floors[floor].getRooms()[x][y].setDownStairs(); //set the downstairs
            floors[floor + 1].getRooms()[x][y].setUpStairs(); //set the upstairs to the room above
        }
    }

    /**
     * This method set the exit of the castle at the last floor.
     *
     * @param castle the castle where the exit will be set.
     */
    public static void setCastleExit(Castle castle) {
        Floor[] floors = castle.getFloors(); //get floors of the castle
        int lastStageNumber = floors.length - 1;
        Floor lastStage = floors[castle.getFloors().length - 1]; //last floor

        int x;
        int y;
        do { //choose a random room
            int random = (int) (Math.random() * (Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE));
            x = random / Parameters.FLOOR_SIZE;
            y = random % Parameters.FLOOR_SIZE;
        } while (floors[lastStageNumber].getRooms()[x][y].isSpawn() || floors[lastStageNumber].getRooms()[x][y].isUpStairs()); //while the room is the spawn or the upstairs

        lastStage.getRooms()[x][y].setExit(true); //set the exit at the random room of the last floor
    }

    /**
     * This method set the passages in the castle like a maze (kruskal algorithm).
     *
     * @param castle the castle where the passages will be set.
     */
    public static void setCastlePassages(Castle castle) {
        Floor[] floors = castle.getFloors(); //get floors of the castle
        for (Floor floor : floors) { //for each floor
            Passage[][] h_passages = new Passage[Parameters.FLOOR_SIZE][Parameters.FLOOR_SIZE - 1];
            Passage[][] v_passages = new Passage[Parameters.FLOOR_SIZE - 1][Parameters.FLOOR_SIZE];
            int[][][] passages = generateMaze(); //generate a maze
            for (int i = 0; i < 2; i++) { //for each passage kind
                for (int x = 0; x < passages[i].length; x++) { //for each passage
                    for (int y = 0; y < passages[i][x].length; y++) {
                        if (passages[i][x][y] == 0) { //if the passage is not a wall
                            if (i == 0) {
                                h_passages[x][y] = new Passage(floor); //create a passage
                            } else v_passages[x][y] = new Passage(floor);
                        }
                    }
                }
            }
            floor.setHorizontal_passage(h_passages); //set the horizontal passages
            floor.setVertical_passage(v_passages); //set the vertical passages
        }
    }

    /**
     * This method generate a maze using kruskal algorithm.
     *
     * @return the vertical and horizontal walls/passages of the maze.
     */
    private static int[][][] generateMaze() {
        int FS = Parameters.FLOOR_SIZE;
        int[][] grid = new int[FS][FS]; //grid of the maze
        int[][] h_passages = new int[FS][FS - 1]; //horizontal passages
        int[][] v_passages = new int[FS - 1][FS]; //vertical passages

        //Number each cell in the grid
        for (int i = 0; i < FS * FS; i++) {
            grid[i / FS][i % FS] = i;
        }

        //Close all horizontal passages in the grid
        for (int i = 0; i < FS * FS; i++) {
            if ((i + 1) % FS != 0) h_passages[i / FS][i % FS] = 1;
        }

        //Close all vertical passages in the grid
        for (int i = 0; i < FS * FS; i++) {
            if (i / FS != FS - 1) v_passages[i / FS][i % FS] = 1;
        }

        //While the maze is not finished
        while (!isMazeFinish(grid)) {
            int[] infosp; //info of the passage
            do {
                infosp = randomPassageDirection(grid); //get a random passage
            } while (infosp[0] == -1);
            if (infosp[0] == 0) { //if the passage is horizontal
                h_passages[infosp[1]][infosp[2]] = 0; //open the passage
            } else if (infosp[0] == 1) { //if the passage is vertical
                v_passages[infosp[1]][infosp[2]] = 0; //open the passage
            }
            if (infosp[0] == 0) { //if the passage is horizontal
                int x1 = infosp[1];
                int y1 = infosp[2];
                int x2 = infosp[1];
                int y2 = infosp[2] + 1;
                mergeWays(grid, x1, y1, x2, y2); //merge the two cells or groups of cells
            } else if (infosp[0] == 1) {
                int x1 = infosp[1];
                int y1 = infosp[2];
                int x2 = infosp[1] + 1;
                int y2 = infosp[2];
                mergeWays(grid, x1, y1, x2, y2); //merge the two cells or groups of cells
            }
        }
        return new int[][][]{h_passages, v_passages}; //return the horizontal and vertical passages
    }

    /**
     * This method choose a random direction (horizontal or vertical) of the futur remove passage.
     *
     * @param grid       the grid of the maze (Room with index).
     * @return the direction of the passage and the position of the passage.
     */
    private static int[] randomPassageDirection(int[][] grid) {
        if (Math.random() < 0.5) //mur horizontaux
            return randomPassage(grid, true); //choose a random horizontal passage

        else //mur verticaux
            return randomPassage(grid, false); //choose a random vertical passage
    }

    /**
     * This method choose a random passage.
     *
     * @param grid     the grid of the maze (Room with index).
     * @param h        true if the passages are horizontal, false if they are vertical.
     * @return the direction of the passage and the position of the passage.
     */
    private static int[] randomPassage(int[][] grid, boolean h) {
        int a, b;
        int debug = 0;
        do { //choose a random passage
            if(debug > 100) h = !h;
            if (h) {
                a = (int) (Math.random() * Parameters.FLOOR_SIZE);
                b = (int) (Math.random() * Parameters.FLOOR_SIZE - 1);
            } else {
                a = (int) (Math.random() * Parameters.FLOOR_SIZE - 1);
                b = (int) (Math.random() * Parameters.FLOOR_SIZE);
            }
            debug+=1;
        } while (!isRemovablePassage(grid, a, b, h)); //while the passage is not removable

        if (h) return new int[]{0, a, b}; //return the horizontal passage
        else return new int[]{1, a, b}; //return the vertical passage
    }

    /**
     * This method check if the passage is removable.
     *
     * @param grid     the grid of the maze (Room with index).
     * @param x        the x position of the passage.
     * @param y        the y position of the passage.
     * @param h        true if the passages are horizontal, false if they are vertical.
     * @return true if the passage is removable, false otherwise.
     */
    private static boolean isRemovablePassage(int[][] grid, int x, int y, boolean h) {
        if (h) { //horizontal passage
            return grid[x][y] != grid[x][y + 1]; //if two cells have the same index the passage is not removable
        } else { //vertical passage
            return grid[x][y] != grid[x + 1][y]; //if two cells have the same index the passage is not removable
        }
    }

    /**
     * This method merge two ways (a way is a group of room connected by passages).
     * This is the principle of the algorithm to create the maze.
     *
     * @param grid the grid of the maze (Room with index).
     * @param x1   the x position of the first room to merge.
     * @param y1   the y position of the first room to merge.
     * @param x2   the x position of the second room to merge.
     * @param y2   the y position of the second room to merge.
     */
    private static void mergeWays(int[][] grid, int x1, int y1, int x2, int y2) {
        int a = grid[x1][y1]; //the index of the first cell
        int b = grid[x2][y2]; //the index of the second cell

        for (int i = 0; i < Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE; i++) { //for each cell
            if (grid[i / Parameters.FLOOR_SIZE][i % Parameters.FLOOR_SIZE] == b) //if the cell has the index of the second cell
                grid[i / Parameters.FLOOR_SIZE][i % Parameters.FLOOR_SIZE] = a; //change the index of the cell to the index of the first cell
        }
    }

    /**
     * This method check if the solving of the maze is finish or not.
     * A maze is finish if there is only one way in the maze.
     *
     * @param grid the grid of the maze (Room with index).
     * @return true if the maze is finish, false otherwise.
     */
    private static boolean isMazeFinish(int[][] grid) {
        //If all cells have the same numbers return TRUE
        int FS = Parameters.FLOOR_SIZE;
        int verif = grid[0][0]; //the index of the first cell
        for (int i = 0; i < FS * FS; i++) { //for each cell
            if (grid[i / FS][i % FS] != verif) return false; //if the cell has not the same index return false
        }
        return true; //if all cells have the same index return true
    }

    /**
     * This method permit to generate a random integer between min and max.
     *
     * @param min the minimum value of the random integer.
     * @param max the maximum value of the random integer.
     * @return the random integer.
     */
    public static int randomInt(int min, int max) {
        //Mathematical formula to return a random integer between two bounds passed as parameters.
        return (int) (min + (Math.random() * (max - min + 1)));
    }

    /**
     * This method permit to clear the console.
     */
    public static void clearConsole() {
        //This method permit to clear the console.
        System.out.print("\033[0;0H\033[0;0f\033[0J");
    }
}