import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	private int x;
	private int y;
	private int w;
	private int h;
	

	public Snake(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setW(int w){
		this.w = w;
	}
	public void setH(int h){
		this.h = h;
	}
	public int getW(){
		return w;
	}
	public int getH(){
		return h;
	}

	public void draw(Graphics g){
		g.setColor(Color.yellow);
		g.fillOval(w * x, h * y, w, h);	
	}
	
}
