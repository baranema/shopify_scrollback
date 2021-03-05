package game;

public class Tile {
    public Tile() {
        this.content = "";
        this.active = false;
        this.valid = true;
    }

    private String content;
    private boolean active;
    private boolean valid;
    private int x;
    private int y;

    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }

    public void setValid(boolean value){
        this.valid = value;
    }
    public boolean isValid(){
        return this.valid;
    }
    public void setActive(boolean value){
         this.active = value;
    }
    public boolean isActive(){
        return this.active;
    }

    public int getX(){
        return this.x;
    }
    public void setX(int value){
        this.x = value;
    }

    public int getY(){
        return this.y;
    }
    public void setY(int value){
        this.y = value;
    }
}
