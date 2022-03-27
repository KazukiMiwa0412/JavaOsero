package osero.middleware;

import osero.Main;

public class CheckPutDown {
	public boolean checkPutDown() {
		int countSpace = 0;
		System.out.print("置ける座標は");
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				if (canPutDown(i, j)) {
					System.out.print("(" + i + "," + j + ")");
					countSpace++;
				}
			}
		}
		if (countSpace == 0) {
			System.out.println("ありません");
			return false;
		} else {
			System.out.println("です");
			return true;
		}
	}

	public boolean canPutDown(int x, int y) {
		// (x,y)が盤面の外だったら打てない
		if (x > 8 || y > 8)
			return false;
		// (x,y)にすでに石が打たれてたら打てない
		if (Main.board[x][y] != Main.blankSpace)
			return false;
		// 8方向のうち一箇所でもひっくり返せればこの場所に打てる
		// ひっくり返せるかどうかはもう1つのcanPutDownで調べる
		if (canPutDown(x, y, 1, 0))
			return true; // 右
		if (canPutDown(x, y, 0, 1))
			return true; // 下
		if (canPutDown(x, y, -1, 0))
			return true; // 左
		if (canPutDown(x, y, 0, -1))
			return true; // 上
		if (canPutDown(x, y, 1, 1))
			return true; // 右下
		if (canPutDown(x, y, -1, -1))
			return true; // 左上
		if (canPutDown(x, y, 1, -1))
			return true; // 右上
		if (canPutDown(x, y, -1, 1))
			return true; // 左下

		// どの方向もだめな場合はここには打てない
		return false;
	}

	public boolean canPutDown(int x, int y, int vecX, int vecY) {

		// 隣の場所へ。どの隣かは(vecX, vecY)が決める。
		x += vecX;
		y += vecY;
		// 盤面外だったら打てない
		if (x < 1 || x > 8 || y < 1 || y > 8)
			return false;
		// 隣が自分の石の場合は打てない
		if (Main.board[x][y] == Main.userOsero)
			return false;
		// 隣が空白の場合は打てない
		if (Main.board[x][y] == Main.blankSpace)
			return false;

		// さらに隣を調べていく
		x += vecX;
		y += vecY;
		// となりに石がある間ループがまわる
		while (x >= 1 && x <= 8 && y >= 1 && y <= 8) {
			// 空白が見つかったら打てない（１1つもはさめないから）
			if (Main.board[x][y] == Main.blankSpace)
				return false;
			// 自分の石があればはさめるので打てる
			if (Main.board[x][y] == Main.userOsero)
				return true;
			x += vecX;
			y += vecY;
		}
		// 相手の石しかない場合はいずれ盤面の外にでてしまうのでこのfalse
		return false;
	}

}
