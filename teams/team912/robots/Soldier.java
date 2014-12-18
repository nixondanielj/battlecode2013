package team912.robots;

import team912.mapping.Mapper;
import team912.pathing.Pather;
import team912.robots.strategies.IStrategy;
import team912.robots.strategies.StrategyFactory;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
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
		this.setStrategy(StrategyFactory.get(null, this.getStrategy(), c));
		if (c.isActive()) {
			this.getStrategy().doPreMoveAction(c);
			MapLocation target = this.getStrategy().getTarget(this.getMapper());
			if (target.equals(c.getLocation())) {
				this.getStrategy().doAtTargetAction(c);
			} else {
				move(c, target);
			}
			this.getStrategy().doPostMoveAction(c);
		} else {
			this.getStrategy().doInactiveAction(c);
		}
	}

	private void move(RobotController c, MapLocation target)
			throws GameActionException {
		IStrategy strategy = this.getStrategy();
		Direction dir = this.getPather().dirTo(target);
		MapLocation proposedLoc = c.getLocation().add(dir);
		Team mineTeam = c.senseMine(proposedLoc);
		while (strategy.shouldAvoid(mineTeam, c.getTeam()) && !strategy.shouldDefuse(mineTeam, c.getTeam())) {
			this.getPather().avoid(proposedLoc.add(dir));
			dir = this.getPather().dirTo(target);
			proposedLoc = c.getLocation().add(dir);
			mineTeam = c.senseMine(proposedLoc);
		}
		if (mineTeam != null && strategy.shouldDefuse(mineTeam, c.getTeam())) {
			c.defuseMine(c.getLocation().add(dir));
		} else {
			c.move(dir);
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

	public IStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(IStrategy strategy) {
		if (strategy != this.strategy) {
			this.getPather().clearAvoids();
		}
		this.strategy = strategy;
	}

}
