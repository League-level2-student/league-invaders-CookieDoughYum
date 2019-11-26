import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
Rocketship r;
ArrayList<Projectile>projectiles=new ArrayList<Projectile>();
ArrayList<Alien>aliens=new ArrayList<Alien>();
Random a=new Random();
int score=0;

int getScore() {
	return score;
}

ObjectManager(Rocketship r){
	this.r=r;
}

void addProjectile(Projectile p) {
	projectiles.add(p);
}

void addAlien() {
	aliens.add(new Alien(a.nextInt(LeagueInvaders.width),0,50,50));
}

void update() {
	for(int i=0; i<aliens.size(); i++) {
		aliens.get(i).update();
	}
	
	for(int i=0; i<projectiles.size(); i++) {
		projectiles.get(i).update();
	}
	r.update();
	checkCollision();
	purgeObjects();
}

void draw(Graphics g) {
	r.draw(g);
	for(int i=0; i<aliens.size(); i++) {
		aliens.get(i).draw(g);
	}
	
	for(int i=0; i<projectiles.size(); i++) {
		projectiles.get(i).draw(g);
	}
}

void purgeObjects() {
	for(int i=0; i<aliens.size(); i++) {
		if(aliens.get(i).isActive==false) {
			aliens.remove(i);
		}
	}
	
	for(int i=0; i<projectiles.size(); i++) {
		if(projectiles.get(i).isActive==false) {
		   projectiles.remove(i);
		}
	}
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	addAlien();
}

void checkCollision() {
	for(int i=0; i<aliens.size(); i++) {
		if(r.collisionBox.intersects(aliens.get(i).collisionBox)) {
			aliens.get(i).isActive=false;
			r.isActive=false;
		}
	for(int i1=0; i1<projectiles.size(); i1++) {
		if(projectiles.get(i1).collisionBox.intersects(aliens.get(i).collisionBox)) {
			aliens.get(i).isActive=false;
			score+=1;
		}
	}
	}
	
}

}
