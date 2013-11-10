package team912;

import battlecode.common.RobotController;

public abstract class BotComponent {
	public BotComponent(RobotController rc){
		this.setControl(rc);
	}
	
	private RobotController control;
	protected RobotController getControl() {
		return control;
	}
	protected void setControl(RobotController control) {
		this.control = control;
	}
}
