package listener;

import org.newdawn.slick.Input;

import personnages.Robot;

public class KeyDHandler extends KeyHandler {

	public KeyDHandler() {
		super(Input.KEY_D);
	}

	@Override
	public void handle(Input input, Robot robot) {

	}

	@Override
	public int get_key() {
		return key;
	}

}
