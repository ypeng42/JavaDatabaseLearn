package interview.RobotRoomCleaner;

import java.util.HashSet;
import java.util.Set;

interface Robot {
      // Returns true if the cell in front is open and robot moves into the cell.
	  // Returns false if the cell in front is blocked and robot stays in the current cell.
	  public boolean move();

	  // Robot will stay in the same cell after calling turnLeft/turnRight.
	  // Each turn will be 90 degrees.
	  public void turnLeft();
      public void turnRight();

	  // Clean the current cell.
	  public void clean();
 }

public class WrongSolution {
	public void cleanRoom(Robot robot) {
		// use 'x@y' mark visited nodes, where x,y are integers tracking the coordinates
		dfs(robot, new HashSet<>(), 0, 0); // 0: up, 90: right, 180: down, 270: left
	}

	/**
	 * This is wrong because direction info is not included.
	 * ex. when we go move right and then it will try to move up, which is wrong,
	 * the next 4 steps should be down -> left -> up
	 */
	public void dfs(Robot robot, Set<String> visited, int x, int y) {
		String key = x + "@" + y;
		if (visited.contains(key)) return;

		visited.add(key);
		robot.clean();

		// move up
		if(robot.move()) {
			dfs(robot, visited, x - 1, y);
			backtrack(robot); // have to backtrack since we moved already
		}
		robot.turnRight();

		// move right
		if(robot.move()) {
			dfs(robot, visited, x, y + 1);
			backtrack(robot);
		}
		robot.turnRight();

		// move down
		if(robot.move()) {
			dfs(robot, visited, x + 1, y);
			backtrack(robot);
		}
		robot.turnRight();

		// move left
		if(robot.move()) {
			dfs(robot, visited, x, y - 1);
			backtrack(robot);
		}
		robot.turnRight();
	}

	private void backtrack(Robot robot) {
		// turn to oppsite dir
		robot.turnLeft();
		robot.turnLeft();
		// move back
		robot.move();

		// reverse 2 left turns
		robot.turnRight();
		robot.turnRight();
	}
}
