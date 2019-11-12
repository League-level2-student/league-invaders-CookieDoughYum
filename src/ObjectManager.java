import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
Rocketship r;
ArrayList<Projectile>projectiles=new ArrayList<Projectile>();
ArrayList<Alien>aliens=new ArrayList<Alien>();
Random a=new Random();
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
		if(isActive==false) {
			aliens.get(i).remove();
		}
	}
}

}
