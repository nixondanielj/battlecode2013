package team912;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.Team;

public abstract class BotComponent {
	public BotComponent(RobotController rc){
		this.setControl(rc);
	}
	
	protected boolean isLocationMineable(MapLocation location){
		Team team = this.getControl().senseMine(location);
		return team == this.getControl().getTeam();
	}
	
	private RobotController control;
	protected RobotController getControl() {
		return control;
	}
	protected void setControl(RobotController control) {
		this.control = control;
	}
}
