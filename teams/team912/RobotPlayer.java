package team912;

import team912.robots.BaseRobot;
import team912.robots.RobotFactory;
import battlecode.common.RobotController;

public class RobotPlayer {
	public static void run(RobotController rc){
		while(true) {
			try {
				BaseRobot bot = RobotFactory.create(rc);
				bot.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
