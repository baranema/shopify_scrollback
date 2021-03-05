package game;

public class Buffer {

    public Buffer(int size){
        this.buffer = new Tile[size];
        for(int i=0; i<size; i++){
            this.buffer[i] = new Tile();
        }
    }

    private Tile[] buffer;

    public void receiveTile(Tile tile){
        if(this.isFull()){
            return;
        }
        buffer[nextEmpty()].setContent(tile.getContent());
        buffer[nextEmpty()].setX(tile.getX());
        buffer[nextEmpty()].setY(tile.getY());
    }

    public int nextEmpty(){
        for(int i=0; i<= buffer.length; i++){
            if(buffer[i].getContent().equals("")){
                return i;
            }
        }
        return -1;
    }

    public void removeTile(){
        if(this.isFull()){
            buffer[buffer.length-1].setContent("");
        }
        else {
            buffer[nextEmpty()-1].setContent("");
        }
    }

    public Tile[] getbuffer(){
        return buffer;
    }

    public boolean isFull(){
        return !(buffer[buffer.length-1].getContent().equals(""));
    }
}



