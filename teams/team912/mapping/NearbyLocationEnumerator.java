package team912.mapping;

import battlecode.common.MapLocation;

class NearbyLocationEnumerator {
	NearbyLocationEnumerator(MapLocation start){
		setCenter(start);
		MapLocation current = new MapLocation(start.x + delta, start.y + delta);
		setCurrent(current);
	}
	
	//TODO finish this
	public MapLocation getNext(){
		if(!yChanging){
			posX += xDir;
			if(Math.abs(posX - getCenter().x) > delta){
				posX -= xDir;
				xDir *= -1;
				yChanging = true;
			}
		} else {
			if(Math.abs(posY - getCenter().y) < delta){
				posY += yDir;
			} else {
				yDir *= -1;
				yChanging = false;
			}
		}
		MapLocation next = new MapLocation(posX, posY);
		setCurrent(next);
		return next;
	}
	
	boolean yChanging = false;
	int delta = 1;
	int posX = 0;
	int posY = 0;
	int xDir = 1;
	int yDir = 1;
	
	private MapLocation current;
	
	public MapLocation getCenter() {
		return center;
	}


	private void setCenter(MapLocation center) {
		this.center = center;
	}


	public MapLocation getCurrent() {
		return current;
	}

	private void setCurrent(MapLocation current) {
		this.posX = current.x;
		this.posY = current.y;
		this.current = current;
	}

	private MapLocation center;
	
}
