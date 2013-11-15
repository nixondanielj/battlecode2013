package team912.robots;

import team912.mapping.Mapper;
import team912.pathing.Pather;
import team912.robots.strategies.IStrategy;
import team912.robots.strategies.StrategyFactory;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.Team;

public class Soldier extends BaseRobot {
	
	private Pather pather;
	private Mapper mapper;
	private IStrategy strategy;

	public Soldier(RobotController control, Pather pather, Mapper mapper) {
		super(control);
		this.setPather(pather);
		this.setMapper(mapper);
		this.setStrategy(null);
	}

	@Override
	public void run() throws GameActionException {
		RobotController c = this.getControl();
		this.setStrategy(StrategyFactory.get(null, this.getStrategy()));
		if(c.isActive()){
			this.getStrategy().doPreMoveAction(c);
			if(move(c)){
				this.getStrategy().doNoMoveAction(c);
			}
			this.getStrategy().doPostMoveAction(c);
		} else {
			this.getStrategy().doInactiveAction(c);
		}
	}

	private boolean move(RobotController c) throws GameActionException {
		boolean didMove = false;
		// reset location
		this.getPather().setCurrentLocation(c.getLocation());
		// TODO change from mapper.getEnemyHQ to strategy.getTarget()
		Direction direction = this.getPather().dirTo(mapper.getEnemyHQ());
		// eliminate mine if in the way
		Team mineTeam = c.senseMine(c.getLocation().add(direction));
		if(mineTeam != null && mineTeam != c.getTeam()){
			c.defuseMine(c.getLocation().add(direction));
		} else {
			c.move(direction);
			didMove = true;
		}
		return didMove;
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

	public IStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(IStrategy strategy) {
		this.strategy = strategy;
	}

}
