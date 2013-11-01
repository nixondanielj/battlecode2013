package team912.robots;

import team912.pathing.IPather;

public class Soldier extends BaseRobot {
	
	private IPather pather;

	public Soldier(IPather pather) {
		this.setPather(pather);
	}

	@Override
	public void run() {
		// TODO
	}
	
	public IPather getPather() {
		return pather;
	}

	public void setPather(IPather pather) {
		this.pather = pather;
	}

}
