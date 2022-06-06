package main.utils;

import main.exception.UntraceableRemovablePassageException;
import main.locations.Castle;
import main.locations.Floor;
import main.locations.Passage;
import main.locations.Room;
import main.options.Parameters;

/**
 * The type Utils.
 */
public class Utils {
    /**
     * Sets castle spawn.
     *
     * @param castle the castle
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
     * Sets castle stairs.
     *
     * @param castle the castle
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
     * Sets castle exit.
     *
     * @param castle the castle
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
     * Sets castle passages.
     *
     * @param castle the castle
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
            int[] infosp; //infos about the passage
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

    private static int[] randomPassageDirection(int[][] grid) {
        if (Math.random() < 0.5) //mur horizontaux
        {
            try {
                return randomPassage(grid, true); //choose a random horizontal passage
            } catch (UntraceableRemovablePassageException e) {
                System.out.println("Error : " + e.getMessage());
            }
            try {
                return randomPassage(grid, false); //choose a random horizontal passage
            } catch (UntraceableRemovablePassageException ignored) {}
        }

        else //mur verticaux
        {
            try {
                return randomPassage(grid, false); //choose a random vertical passage
            } catch (UntraceableRemovablePassageException e) {
                System.out.println("Error : " + e.getMessage());
            }
            try {
                return randomPassage(grid, true); //choose a random vertical passage
            } catch (UntraceableRemovablePassageException ignored) {}
        }
        return new int[0];
    }

    private static int[] randomPassage(int[][] grid, boolean h) throws UntraceableRemovablePassageException {
        int a, b;
        int debug = 0;
        do { //choose a random passage
            if(debug > 100) throw new UntraceableRemovablePassageException();
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

    private static boolean isRemovablePassage(int[][] grid, int x, int y, boolean h) {
        if (h) { //horizontal passage
            return grid[x][y] != grid[x][y + 1]; //if two cells have the same index the passage is not removable
        } else { //vertical passage
            return grid[x][y] != grid[x + 1][y]; //if two cells have the same index the passage is not removable
        }
    }

    private static void mergeWays(int[][] grid, int x1, int y1, int x2, int y2) {
        int a = grid[x1][y1]; //the index of the first cell
        int b = grid[x2][y2]; //the index of the second cell

        for (int i = 0; i < Parameters.FLOOR_SIZE * Parameters.FLOOR_SIZE; i++) { //for each cell
            if (grid[i / Parameters.FLOOR_SIZE][i % Parameters.FLOOR_SIZE] == b) //if the cell has the index of the second cell
                grid[i / Parameters.FLOOR_SIZE][i % Parameters.FLOOR_SIZE] = a; //change the index of the cell to the index of the first cell
        }
    }

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
     * Random int int.
     *
     * @param min the min
     * @param max the max
     * @return the int
     */
    public static int randomInt(int min, int max) {
        //Mathematical formula to return a random integer between two bounds passed as parameters.
        return (int) (min + (Math.random() * (max - min + 1)));
    }

    /**
     * Clear console.
     */
    public static void clearConsole() {
        //This method permit to clear the console.
        System.out.print("\033[0;0H\033[0;0f\033[0J");
    }
}