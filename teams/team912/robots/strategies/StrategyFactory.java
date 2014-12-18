package team912.robots.strategies;

import team912.robots.communicator.ICommunicator;
import battlecode.common.RobotController;

public class StrategyFactory {
	public static IStrategy get(ICommunicator comm, IStrategy oldStrategy, RobotController rc){
		IStrategy strategy = oldStrategy;
		if(strategy == null){
			double rn = rc.getTeamPower() % 1;
			if(rn <= .2){
				strategy = new BaseDefenseStrategy();
			} else if (rn <= .3){
				strategy = new SaboteurStrategy();
			} else {
				strategy = new SwarmStrategy();
			}
		}
		return strategy;
	}
}
