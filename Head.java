
public class Head {
	private int x;
	private int y;
	boolean isRight;
	boolean isLeft;
	boolean isUp;
	boolean isDown;
	private int prevX;
	private int prevY;
	private int speed;
	public Head(int x, int y){
		speed = 1;
		this.x = x;
		this.y = y;
	}
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int sp){
		speed = sp;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void moveUp(){
		setLocation();
		y--;
		if(y < 0){
			y = GraphicSnake.SIZE;
		}
	}
	
	public void moveDown(){
		setLocation();
		y++;
		if(y > GraphicSnake.SIZE){
			y = 0;
		}
	}
	public void moveRight(){
		setLocation();
		x++;
		if(x > GraphicSnake.SIZE){
			x = 0;
		}
	}
	public void moveLeft(){
		setLocation();
		x--;
		if(x < 0){
			x = GraphicSnake.SIZE;
		}
	}
	
	public void setLocation(){
		prevX = x;
		prevY = y;
		for(Snake s : GraphicSnake.snakes){
			int tmpX = s.getX();
			int tmpY = s.getY();
			s.setX(prevX);
			s.setY(prevY);
			prevX = tmpX;
			prevY = tmpY;	
		}
	}
}
