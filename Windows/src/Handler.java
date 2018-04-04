package src;

import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();

	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject _tempObject_ = object.get(i);

			_tempObject_.tick();
		}
	}

	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject _tempObject_ = object.get(i);

			_tempObject_.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public GameObject getObject(int i) {
		return this.object.get(i);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

}