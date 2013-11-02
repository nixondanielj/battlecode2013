package team912.robots;

import java.util.HashMap;

import team912.pathing.aStar.AStarPather;
import battlecode.common.RobotController;

public class RobotFactory {
	public static HashMap<Integer, BaseRobot> bots = 
			new HashMap<Integer, BaseRobot>();

	public static BaseRobot create(RobotController rc) {
		BaseRobot bot = null;
		bot = bots.get(rc.getRobot().getID());
		if (bot == null) {
			switch (rc.getType()) {
			case SOLDIER:
				bot = new Soldier(new AStarPather());
			case ARTILLERY:
				bot = new PlaceholderBot();
			case GENERATOR:
				bot = new PlaceholderBot();
			case HQ:
				bot = new HQ();
			case MEDBAY:
				bot = new PlaceholderBot();
			case SHIELDS:
				bot = new PlaceholderBot();
			case SUPPLIER:
				bot = new PlaceholderBot();
			}
			bots.put(rc.getRobot().getID(), bot);
		}
		return bot;
	}

}
