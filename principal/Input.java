package principal;


import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.KeyEvent;

public class Input implements KeyListener{

	public  Map<String,Integer> keyNames = new HashMap<String,Integer>();
	private Map<Integer,Boolean> pressedKeys = new HashMap<Integer,Boolean>();
	public Map<Integer,Boolean> releasedKeys = new HashMap<Integer,Boolean>();
	
	private static final Input instance = new Input();
	
	private Input() {
		this.createKey("pause", KeyEvent.VK_SPACE);
		this.createKey("restart", KeyEvent.VK_R);
		
	}
	
	public static Input getInstance() {
		return instance;
	}
	
	public void createKey(String s, int i) {
		keyNames.put(s, i);
	}
	
	public boolean isKeyPressed(String s) {
			if(keyNames.containsKey(s) && pressedKeys.containsKey(keyNames.get(s)) && pressedKeys.get(keyNames.get(s))) {
				return true;
			}
		return false;
	}
	
	public boolean isKeyTyped(String s) {
			if (keyNames.containsKey(s) && releasedKeys.containsKey(keyNames.get(s)) && releasedKeys.get(keyNames.get(s))) {
				releasedKeys.put(keyNames.get(s), false);
				return true;
			}
		return false;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		pressedKeys.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	
		pressedKeys.put(e.getKeyCode(),false);
		releasedKeys.put(e.getKeyCode(),true);
	}
}
