package sample;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public abstract class GameObject {

	protected int x, y;
	protected ID id;

	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		System.out.println("(GameObject.java:17) => " + this.id);
	}

	public abstract void tick();
	public abstract void render(Graphics g);

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int[] getCasePosition() {
	    int[] _temp_ = {(this.x + 1), (this.y  + 1)};
	    return _temp_;
	}

	public String getStringCasePosition() {
		return "(" + this.getCasePosition()[0] + ", " + this.getCasePosition()[0] + ")";
	}

	public void setId(ID id) {
		this.id = this.id;
	}

	public ID getId() {
		return this.id;
	}

}