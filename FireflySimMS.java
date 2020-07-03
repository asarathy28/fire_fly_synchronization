import java.util.Random;

public class FireflySimMS implements SimulationInterface{

    private int height;
    private int width;
    private FireflyMS[][] grid; //row, col
    private Random r = new Random();

    public FireflySimMS(int h, int w) {
        height = h;
        width = w;
        grid = new FireflyMS[h][w];
        setUpSim();
    }

    private void setUpSim() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean alive = true;//r.nextBoolean();
                double timePeriod = 100;
                double internalTime = (timePeriod+1) * Math.random();
                grid[i][j] = new FireflyMS(alive,internalTime);
            }
        }
    }

    public int getUnitState (int row, int col) { // need to expand more
        return grid[row][col].getState();
    }

    public void printGrid(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(" " + grid[i][j].getState());
            }
            System.out.println();
        }
        System.out.println();
    }

    private FireflyMS nextState(int row, int col) {
        FireflyMS temp = grid[row][col].clone();
        if (temp.isAlive()) {
            temp.jumpFunction(isFlash(row,col-1));
            temp.jumpFunction(isFlash(row-1,col));
            temp.jumpFunction(isFlash(row,col+1));
            temp.jumpFunction(isFlash(row+1,col));
            temp.jumpFunction(isFlash(row-1,col-1));
            temp.jumpFunction(isFlash(row+1,col+1));
            temp.jumpFunction(isFlash(row+1,col-1));
            temp.jumpFunction(isFlash(row-1,col+1));
            temp.incrementInternalTime();
        }
        //System.out.println("row: " + row + " col: " + col); //for debugging
        //temp.print(); //for debugging
        return temp;
    }

    private boolean isFlash(int row, int col) {
        if (row < 0 || row >= height) {
            return false;
        }
        if (col < 0 || col >= width) {
            return false;
        }
        if (grid[row][col].getState() == -1) {
            //System.out.println("isFlash: TRUE"); //for debugging
            return true;
        } else {
            return false;
        }
    }

    public void step() {
        //System.out.println("NEW STEP"); //for debugging
        FireflyMS[][] tempGrid = new FireflyMS[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tempGrid[i][j] = nextState(i, j);
            }
        }
        grid = tempGrid;
    }
}
