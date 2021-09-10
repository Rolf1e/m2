package edu.ncsu.monopoly;

public abstract class AbstractCell implements Cell {
	private boolean available = true;
	private String name;
	protected Player owner;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Player getOwner() {
		return owner;
	}
	
	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
    
    public String toString() {
        return name;
    }
}
