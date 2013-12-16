package team912.pathing;

import java.util.ArrayList;
import java.util.List;

import team912.BotComponent;
import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public abstract class Pather extends BotComponent {
	Pather(RobotController robot){
		super(robot);
	}
	
	public abstract Direction dirTo(MapLocation location);
	
	private List<MapLocation> PlacesToAvoid;

	public void avoid(MapLocation location) {
		this.getPlacesToAvoid().add(location);
	}

	public void clearAvoids() {
		this.getPlacesToAvoid().clear();
	}
	
	protected boolean canMove(Direction dir){
		if(this.getPlacesToAvoid().contains(
				this.getControl().getLocation().add(dir))){
			return false;
		}
		return this.getControl().canMove(dir);
	}

	protected List<MapLocation> getPlacesToAvoid() {
		if(this.PlacesToAvoid == null){
			this.PlacesToAvoid = new ArrayList<MapLocation>();
		}
		return PlacesToAvoid;
	}
}
