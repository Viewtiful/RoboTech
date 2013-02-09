package listener;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Input;

import personnages.Robot;

public class RobotHandlers {

	ArrayList<KeyHandler> List_Handler;

	public RobotHandlers() {
		List_Handler = new ArrayList<KeyHandler>();
		List_Handler.add(new KeyLEFTHandler());
		List_Handler.add(new KeyRIGHTHandler());
		List_Handler.add(new KeyUPHandler());
		List_Handler.add(new KeyQHandler());
		List_Handler.add(new KeyZHandler());
		List_Handler.add(new KeyDHandler());
	}

	public ArrayList<KeyHandler> get_ListHandler() {
		return List_Handler;
	}

	public void handle(Input input, Robot robot) {
		Iterator<KeyHandler> it = List_Handler.iterator();
		while (it.hasNext())
			it.next().handle(input, robot);
	}
}
