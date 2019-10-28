import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	GamePanel gp;
	JFrame frame=new JFrame();
	JPanel panel=new JPanel();
	public static final int width=500;
	public static final int height=800;
public static void main(String[] args) {
	LeagueInvaders li=new LeagueInvaders();
	li.setup();
}
LeagueInvaders(){
	this.frame=new JFrame();
	this.gp=new GamePanel();
}
void setup() {
	frame.add(gp);
	frame.setSize(width, height);
	frame.setVisible(true);
	frame.add(panel);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();
}
}

