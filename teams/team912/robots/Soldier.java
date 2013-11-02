package team912.robots;

import team912.mapping.Mapper;
import team912.pathing.Pather;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

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
		this.getControl().move(this.getPather().dirTo(mapper.getEnemyHQ()));
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
