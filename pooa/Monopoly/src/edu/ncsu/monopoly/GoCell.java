package edu.ncsu.monopoly;

public class GoCell extends AbstractCell {
	public GoCell() {
		super.setName("Go");
		setAvailable(false);
	}

	public boolean playAction(String msg) {
		return false;
	}
	
	public void setName(String name) {
	}
}
