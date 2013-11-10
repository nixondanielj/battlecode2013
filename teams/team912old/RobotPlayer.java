package team912old;

import team912old.robots.BaseRobot;
import team912old.robots.RobotFactory;
import battlecode.common.RobotController;

public class RobotPlayer {
	public static void run(RobotController rc){
		while(true) {
			try {
				BaseRobot bot = RobotFactory.create(rc);
				bot.run();
				rc.yield();
			} catch (Exception e) {
				e.printStackTrace();
				rc.yield();
			}
		}
	}
}
