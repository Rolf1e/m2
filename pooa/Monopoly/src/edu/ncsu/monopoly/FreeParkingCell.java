package edu.ncsu.monopoly;

public class FreeParkingCell extends AbstractCell {

	public FreeParkingCell() {
		setName("Free Parking");
	}

	public boolean playAction(String msg) {
		return false;
	}
}
