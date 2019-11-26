import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font enterFont;
	Font spaceFont;
	Font gameOver;
	Font enemies;
	Font restart;
	Timer frameDraw;
	Timer alienSpawn;
	Rocketship r = new Rocketship(250, 700, 50, 50, 0, true);
	ObjectManager m = new ObjectManager(r);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 40);
		enterFont = new Font("Arial", Font.PLAIN, 20);
		spaceFont = new Font("Arial", Font.PLAIN, 20);
		gameOver = new Font("Arial", Font.PLAIN, 40);
		enemies = new Font("Arial", Font.PLAIN, 20);
		restart = new Font("Arial", Font.PLAIN, 20);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.addActionListener(this);
		frameDraw.start();
	}

	void updateMenuState() {

	}

	void updateGameState() {
		m.update();
		if (r.isActive == false) {
			currentState = END;
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 50, 100);
		g.setFont(enterFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to start", 175, 350);
		g.setFont(spaceFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press SPACE for instructions", 150, 450);
	}

	void drawGameState(Graphics g) {
		loadImage("space.png");
		g.drawImage(image, 0, 0, LeagueInvaders.width, LeagueInvaders.height, null);
		m.draw(g);
		g.drawString(" Score " + m.getScore(), 30, 30);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setFont(gameOver);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 200, 200);
		g.setFont(enemies);
		g.setColor(Color.BLACK);
		g.drawString("You killed enemies", 200, 400);
		g.setFont(restart);
		g.setColor(Color.BLACK);
		g.drawString("Press ENTER to restart", 150, 700);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		System.out.println("action");
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				r = new Rocketship(250, 700, 50, 50, 0, true);
				m = new ObjectManager(r);
 				currentState = MENU;
			} else {
				currentState++;
				if (currentState == GAME) {
					startGame();
				}
				if (currentState == END) {
					alienSpawn.stop();
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (r.y >= r.speed) {
				r.y -= 10;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (r.y <= (LeagueInvaders.height - r.height) - r.speed) {
				r.y += 10;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (r.x >= r.speed) {
				r.x -= 10;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (r.x <= (LeagueInvaders.width - r.width) - r.speed) {
				r.x += 10;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			m.addProjectile(r.getProjectile());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	void startGame() {
		alienSpawn = new Timer(1000, m);
		alienSpawn.start();
	}

}
