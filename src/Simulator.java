import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

public class Simulator {
    public static final int EMPTY = 0;
    public static final int LIVING = 1;
    public static final int ON_FIRE = 2;
    public static final int ASH = 3;

    int[][] grid;
    int[][] copyGrid;

    public Simulator(int r, int c) {
        grid = new int[r][c];
    }

    public void fullRun(int treeDensity){
        initializeTrees(treeDensity);
        startFire(1);
        while (!isFireOnScreen()){
            propagateFire();
        }
    }

    public void createDeadTreeRow(){
        for (int r = (grid.length/2)-2; r < (grid.length/2)+2; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if(grid[c][r] == LIVING) grid[c][r] = EMPTY;
            }
        }
    }

    public int numTrees(){
        int trees = 0;
        for (int r = 0; r < this.getHeight(); r++) {
            for (int c = 0; c < this.getWidth(); c++) {
                if(grid[r][c]==LIVING) trees++;
            }
        }
        return trees;
    }

    public boolean isFireOnScreen(){
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == ON_FIRE) return false;
            }
        }
        return true;
    }

    public void initializeTrees(double density){
        double counter = 0;
        double totalTrees = (density/100)*(grid.length*grid[0].length);
        while (counter < totalTrees) {
            int randomRow = (int)(Math.random()*grid.length);
            int randomCol = (int)(Math.random()*grid[0].length);
            if(grid[randomRow][randomCol] == EMPTY) {
                grid[randomRow][randomCol] = LIVING;
                counter++;
            }
        }
    }

    public void startFire(int numFires){
        int counter = 0;
        while (counter < numFires){
            int randomRow = (int)(Math.random()*grid.length);
            int randomCol = (int)(Math.random()*grid[0].length);
            if(grid[randomRow][randomCol] == LIVING) {
                grid[randomRow][randomCol] = ON_FIRE;
                counter++;
            }
        }
    }

    public void propagateFire() {
        makeCopyOfGrid();
        for (int Row = 0; Row < copyGrid.length; Row++) {
            for (int Col = 0; Col < copyGrid[0].length; Col++) {
                if (copyGrid[Row][Col] == ON_FIRE) {
                    spreadFireAroundTree(Row, Col);
                }
            }
        }
    }

    public void spreadFireAroundTree(int Row, int Col){
        for (int r = Row-1; r <= Row+1; r++) {
            for (int c = Col-1; c <= Col+1; c++) {
                if(isInBounds(r, c) && grid[r][c] == LIVING) {
                    grid[r][c] = ON_FIRE;
                }
            }
        }
        grid[Row][Col] = ASH;
    }

    public void makeCopyOfGrid() {
        copyGrid = new int[this.getHeight()][this.getWidth()];
        for (int row = 0; row < copyGrid.length; row++) {
            for (int col = 0; col < copyGrid[0].length; col++) {
                copyGrid[row][col] = grid[row][col];
            }
        }
    }

    public boolean isInBounds(int r, int c) {
        return (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length);
    }

    public int getWidth() {
        return grid[0].length;
    }

    public int getHeight() {
        return grid.length;
    }

    public int[][] getDisplayGrid() {
        return grid;
    }
}