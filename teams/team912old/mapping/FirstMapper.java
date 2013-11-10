package team912old.mapping;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import team912old.BotComponent;

public class FirstMapper extends BotComponent implements Mapper {

	public FirstMapper(RobotController rc) {
		super(rc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MapLocation getEnemyHQ() {
		return this.getControl().senseEnemyHQLocation();
	}

}
