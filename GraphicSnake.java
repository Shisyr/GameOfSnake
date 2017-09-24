import javax.swing.*;
import javax.swing.Timer;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Stack;
public class GraphicSnake extends JFrame{
	static Stack<Snake> snakes = new Stack<>();
	private Random rnd = new Random();
	Head head = new Head(1, 0);
	static boolean isUp;
	static boolean isDown;
	static boolean isLeft;
	static boolean isRight;
	private final int EYES = 15;
	private int eyeY = 5;
	Food food = new Food(rnd.nextInt(SIZE), rnd.nextInt(SIZE));
	private int delay = 100;
	private int Location = 0;	
//	Snake snake = new Snake(0, 0);
	Snake setSnake;
	final static int SIZE = 25;
	CanvasPanel mainPanel = new CanvasPanel();
	Timer timer;
	GraphicSnake(){
		snakes.add(new Snake(0, 0));
		isUp = isDown = isLeft = false;
		isRight = true;
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.BLUE);
		mainPanel.setFocusable(true);
		mainPanel.addKeyListener(new KeyListen());
		add(mainPanel);
		timer = new Timer(delay, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent en) {
				update();
				repaint();
				
			}	
		});
		timer.start();
	}
	public static void main(String[] args) {
		GraphicSnake frame = new GraphicSnake();
		frame.setTitle("Snake");
		frame.setSize(700, 650);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void update(){
		switch(Location){
		case 0:
			head.moveUp();
			isUp = true;
			isDown = isLeft = isRight = false;
			break;
		case 1:
			head.moveDown();
			isDown = true;
			isUp = isLeft = isRight = false;
			break;
		case 2:
			head.moveRight();
			isRight = true;
			isDown = isLeft = isUp = false;
			break;
		case 3:
			head.moveLeft();
			isLeft = true;
			isDown = isUp = isRight = false;
			break;
		}
	}
	
	class KeyListen extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			if(KeyEvent.VK_UP == e.getKeyCode()){
				Location = 0;
				if(isDown){
					Location = 1;
				}
			}
			else if(KeyEvent.VK_DOWN == e.getKeyCode()){
				Location = 1;
				if(isUp){
					Location = 0;
				}
			}
			else if(KeyEvent.VK_RIGHT == e.getKeyCode()){
				Location = 2;
				if(isLeft){
					Location = 3;
				}
			}
			else if(KeyEvent.VK_LEFT == e.getKeyCode()){
				Location = 3;
				if(isRight){
					Location = 2;
				}
			}
			repaint();	
		}
	}

	
	class CanvasPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			int sX = 0;
			int sY = 0;
			int x = getWidth() / SIZE;
			int y = getHeight() / SIZE;
			for(int i = 0;i < SIZE;i++){
				for(int j = 0;j < SIZE;j++){
					g.setColor(Color.blue);
					g.drawRect(x * j, y * i, x, y);
				}
			}
			g.setColor(Color.yellow);
			g.fillOval(x * head.getX(), y * head.getY(), x, y);
			for(Snake s : snakes){
				s.setH(y);
				s.setW(x);
			}
			g.setColor(Color.red);
			if(isRight){
				g.fillOval(x * head.getX() + EYES, y * head.getY() + EYES / 3, x / 3, y / 3);
				g.fillOval(x * head.getX() + EYES, y * head.getY() + EYES, x / 3, y / 3);
			}
			if(isLeft){
				g.fillOval(x * head.getX(), y * head.getY() + EYES / 3, x / 3, y / 3);
				g.fillOval(x * head.getX(), y * head.getY() + EYES, x / 3, y / 3);
			}
			if(isDown){
				g.fillOval(x * head.getX() + EYES / 3, y * head.getY() + EYES, x / 3, y / 3);
				g.fillOval(x * head.getX() + EYES, y * head.getY() + EYES, x / 3, y / 3);
			}
			if(isUp){
				g.fillOval(x * head.getX() + EYES / 3, y * head.getY(), x / 3, y / 3);
				g.fillOval(x * head.getX() + EYES, y * head.getY(), x / 3, y / 3);
			}
			
			
			int fX = food.getX();
			int fY = food.getY();
			g.fillOval(fX * x, fY * y, x, y);
			if(fX == head.getX() && fY == head.getY()){
				food = new Food(rnd.nextInt(SIZE), rnd.nextInt(SIZE));
				sX = snakes.get(snakes.size() - 1).getX();
				sY = snakes.get(snakes.size() - 1).getY();
				if(isUp){
					sX = sX;
					sY = (sY < SIZE) ? sY + 1: sY;
				}
				if(isDown){
					sX = sX;
					sY = (sY > 0) ? sY - 1 : sY;
				}
				if(isLeft){
					sY = sY;
					sX = (sX < SIZE) ? sX + 1 : sX;
				}
				if(isRight){
					sY = sY;
					sX = (sX > 0) ? sX - 1 : sX;
				}
				snakes.add(new Snake(sX, sY));
				System.out.println(snakes.size());	
			}
			for(Snake s: snakes){
				s.draw(g);
			}		
		}
	}

}
