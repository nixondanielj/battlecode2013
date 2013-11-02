package team912.robots;

import java.util.HashMap;

import team912.mapping.FirstMapper;
import team912.mapping.Mapper;
import team912.pathing.BadPather;
import team912.pathing.Pather;
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
				bot = buildSoldier(rc);
			case ARTILLERY:
				bot = new PlaceholderBot(rc);
			case GENERATOR:
				bot = new PlaceholderBot(rc);
			case HQ:
				bot = new HQ(rc);
			case MEDBAY:
				bot = new PlaceholderBot(rc);
			case SHIELDS:
				bot = new PlaceholderBot(rc);
			case SUPPLIER:
				bot = new PlaceholderBot(rc);
			}
			bots.put(rc.getRobot().getID(), bot);
		}
		return bot;
	}

	private static BaseRobot buildSoldier(RobotController rc) {
		Pather pather = new BadPather(rc);
		Mapper mapper = new FirstMapper(rc);
		return new Soldier(rc, pather, mapper);
	}

}
