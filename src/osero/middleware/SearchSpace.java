package osero.middleware;

import osero.Main;

public class SearchSpace {
	public boolean searchSpace() {
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				if (Main.board[i][j] == Main.blankSpace) {
					return true;
				}
			}
		}
		return false;

	}
}
