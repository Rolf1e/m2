package edu.ncsu.monopoly;

public interface Cell {
    String getName();

    Player getOwner();

    int getPrice();

    boolean isAvailable();

    boolean playAction(String msg);
}
