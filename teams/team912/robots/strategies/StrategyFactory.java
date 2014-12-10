package team912.robots.strategies;

import team912.robots.communicator.ICommunicator;
import battlecode.common.RobotController;

public class StrategyFactory {
	public static IStrategy get(ICommunicator comm, IStrategy oldStrategy, RobotController rc){
		IStrategy strategy = oldStrategy;
		if(strategy == null){
			double rn = rc.getTeamPower() % 1;
			if(rn <= .15){
				strategy = new BaseDefenseStrategy();
			} else {
				strategy = new SwarmStrategy();
			}
		}
		return strategy;
	}
}
