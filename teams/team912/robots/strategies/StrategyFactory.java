package team912.robots.strategies;

import team912.robots.communicator.ICommunicator;

public class StrategyFactory {
	public static IStrategy get(ICommunicator comm, IStrategy oldStrategy){
		IStrategy strategy = oldStrategy;
		if(strategy == null){
			if(true){//Math.random() <= .25){
				strategy = new BaseDefenseStrategy();
			} else {
				strategy = new SwarmStrategy();
			}
		}
		return strategy;
	}
}
