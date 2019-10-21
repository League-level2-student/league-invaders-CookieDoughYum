import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	JFrame frame=new JFrame();
	final int width=500;
	final int height=800;
public static void main(String[] args) {
	LeagueInvaders li=new LeagueInvaders();
	li.setup();
}
LeagueInvaders(){
	this.frame=new JFrame();
}
void setup() {
	frame.setVisible(true);
	JPanel panel=new JPanel();
	frame.add(panel);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();
}
}

