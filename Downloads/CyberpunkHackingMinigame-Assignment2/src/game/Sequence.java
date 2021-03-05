package game;

public class Sequence {

    public Sequence(String[] sequence){
        this.tiles = new Tile[sequence.length];
        for(int i=0; i<sequence.length; i++){
            tiles[i] = new Tile();
        }
        this.reward = sequence.length;
        setSequence(sequence);
    }

    private Tile[] tiles;
    private int reward;

    public void setSequence(String[] tiles){
        for(int i=0; i < tiles.length; i++){
            this.tiles[i].setContent(tiles[i]);
        }
    }

    public Tile[] getSequence(){
        return this.tiles;
    }

    public int getReward(){
        return this.reward;
    }
}
