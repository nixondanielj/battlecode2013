package team912old.robots;

import java.util.HashMap;

import team912old.mapping.FirstMapper;
import team912old.mapping.Mapper;
import team912old.pathing.BadPather;
import team912old.pathing.Pather;
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
				break;
			case ARTILLERY:
				bot = new PlaceholderBot(rc);
				break;
			case GENERATOR:
				bot = new PlaceholderBot(rc);
				break;
			case HQ:
				bot = new HQ(rc);
				break;
			case MEDBAY:
				bot = new PlaceholderBot(rc);
				break;
			case SHIELDS:
				bot = new PlaceholderBot(rc);
				break;
			case SUPPLIER:
				bot = new PlaceholderBot(rc);
				break;
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
