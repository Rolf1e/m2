package edu.ncsu.monopoly;

public class CardCell extends AbstractCell {
    private int type;
    
    public CardCell(int type, String name) {
        setName(name);
        this.type = type;
    }
    
    public boolean playAction(String msg) {
        return false;
    }
    
    public int getType() {
        return type;
    }
}
