package renderer.input;

public class UserInput {
	
	public Mouse mouse;
	public Keyboard keyboard;
	
	public UserInput() {
		this.mouse = new Mouse();
		this.keyboard = new Keyboard();
	}
	
	public UserInput(Mouse mouse, Keyboard keyboard) {
		this.mouse = mouse;
		this.keyboard = keyboard;
	}

}
