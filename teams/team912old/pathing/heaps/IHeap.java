package team912old.pathing.heaps;

import battlecode.common.MapLocation;

public interface IHeap {
	MapLocation pop();
	
	void put(MapLocation location);
}
