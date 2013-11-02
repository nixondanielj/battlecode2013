package team912.robots;

import team912.mapping.Mapper;
import team912.pathing.Pather;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.Team;

public class Soldier extends BaseRobot {
	
	private Pather pather;
	private Mapper mapper;

	public Soldier(RobotController control, Pather pather, Mapper mapper) {
		super(control);
		this.setPather(pather);
		this.setMapper(mapper);
	}

	@Override
	public void run() throws GameActionException {
		RobotController c = this.getControl();
		if(c.isActive()){
			move(c);
		}
	}

	private void move(RobotController c) throws GameActionException {
		// reset location
		this.getPather().setCurrentLocation(c.getLocation());
		Direction direction = this.getPather().dirTo(mapper.getEnemyHQ());
		// eliminate mine if in the way
		Team mineTeam = c.senseMine(c.getLocation().add(direction));
		if(mineTeam != null && mineTeam != c.getTeam()){
			c.defuseMine(c.getLocation().add(direction));
		} else {
			c.move(direction);
		}
	}
	
	public Pather getPather() {
		return pather;
	}

	public void setPather(Pather pather) {
		this.pather = pather;
	}

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

}
