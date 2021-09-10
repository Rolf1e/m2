package edu.ncsu.monopoly;

public class JailCell extends AbstractCell {
	public static int BAIL = 50;
	
	public JailCell() {
		setName("Jail");
	}
	
	public boolean playAction(String msg) {
		return false;
	}
}
