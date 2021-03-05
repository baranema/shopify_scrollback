package game;

public class Grid {

    public Grid(String[][] grid){
        this.grid = new Tile[size][size];

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid.length; j++){
                this.grid[i][j] = new Tile();
            }
        }

        initiateGrid(grid);
    }

    private final int size = 5;
    private Tile[][] grid;

    public void initiateGrid(String[][] grid){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                this.grid[i][j].setContent(grid[i][j]);
                this.grid[i][j].setValid(true);
            }
        }
    }

    public Tile[][] getGrid(){
        return this.grid;
    }

    public int getSize(){
        return size;
    }

    public void crossTile(Tile tile){
        tile.setValid(false);
    }

    public void deactivateGrid() {
        for(int i=0; i<this.grid.length; i++){
            for(int j=0; j<this.grid.length; j++){
                this.grid[i][j].setValid(false);
            }
        }
    }
}
