package osero.model;

import osero.Main;

public class ReverseOsero {
	CopyBoard CB = new CopyBoard();

	// ひっくり返す座標を保持するボードの作成
	public String copyBoard[][] = CB.copySaveBoard(Main.board);

	// 入力した座標にオセロをおく
	public void putOsero(int x, int y) {
		if (Main.count % 2 == 0) {
			Main.board[x][y] = Main.black;
		} else {
			Main.board[x][y] = Main.white;
		}

	}

	// オセロボードの情報をcopyBoardに保存する
	public void makeSaveBoard(String[][] board) {
		copyBoard = CB.copySaveBoard(Main.board);
	}

	// 左をひっくり返す
	public void reverseLeft(int x, int y) {
		while (true) {

			// 端に到達したらループを抜ける
			if (x < 2) {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}

			// 隣が相手の色だった場合
			if (Main.board[x - 1][y] == Main.enemyOsero) {
				// ひっくり返す座標をコピーボードに保存する
				copyBoard[x - 1][y] = Main.userOsero;

				// 隣が自分の色だった場合
			} else if (Main.board[x - 1][y] == Main.userOsero) {
				// 変更内容をオリジナルのボードに反映する
				Main.board = CB.copySaveBoard(copyBoard);
				break;

				// 隣が空白だった場合
			} else {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}
			x--;
		}
	}

	// 右をひっくり返す
	public void reverseRight(int x, int y) {
		// 座標がボードの端に到達した場合ループを抜ける
		while (true) {
			if (x > 7) {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}

			// 隣が相手の色だった場合
			if (Main.board[x + 1][y] == Main.enemyOsero) {
				// ひっくり返す座標をコピーボードに保存する
				copyBoard[x + 1][y] = Main.userOsero;

				// 隣が自分の色だった場合
			} else if (Main.board[x + 1][y] == Main.userOsero) {
				// 変更内容をオリジナルのボードに反映する
				Main.board = CB.copySaveBoard(copyBoard);
				break;

				// 隣が空白だった場合
			} else {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}
			x++;
		}
	}

	// 上をひっくり返す
	public void reverseTop(int x, int y) {
		while (true) {
			// 座標がボードの上端に到達した場合の処理
			if (y < 2) {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}

			// 隣が相手の色だった場合
			if (Main.board[x][y - 1] == Main.enemyOsero) {
				// ひっくり返す座標をコピーボードに保存する
				copyBoard[x][y - 1] = Main.userOsero;

				// 隣が自分の色だった場合
			} else if (Main.board[x][y - 1] == Main.userOsero) {
				// 変更内容をオリジナルのボードに反映する
				Main.board = CB.copySaveBoard(copyBoard);
				break;

				// 隣が空白だった場合
			} else {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}
			y--;
		}
	}

	// 下をひっくり返す
	public void reverseBottom(int x, int y) {
		while (true) {
			// 座標がボードの端に到達した場合の処理
			if (y > 7) {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}

			// 隣が相手の色だった場合
			if (Main.board[x][y + 1] == Main.enemyOsero) {
				// ひっくり返す座標をコピーボードに保存する
				copyBoard[x][y + 1] = Main.userOsero;

				// 隣が自分の色だった場合
			} else if (Main.board[x][y + 1] == Main.userOsero) {
				// 変更内容をオリジナルのボードに反映する
				Main.board = CB.copySaveBoard(copyBoard);
				break;

				// 隣が空白だった場合
			} else {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}
			y++;
		}
	}

	// 左上をひっくり返す
	public void reverseLeftTop(int x, int y) {
		while (true) {
			// 座標がボードの上端または左端に到達した場合の処理
			if (x < 2 || y < 2) {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}

			// 隣が相手の色だった場合
			if (Main.board[x - 1][y - 1] == Main.enemyOsero) {
				// ひっくり返す座標をコピーボードに保存する
				copyBoard[x - 1][y - 1] = Main.userOsero;

				// 隣が自分の色だった場合
			} else if (Main.board[x - 1][y - 1] == Main.userOsero) {
				// 変更内容をオリジナルのボードに反映する
				Main.board = CB.copySaveBoard(copyBoard);
				break;

				// 隣が空白だった場合
			} else {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}
			x--;
			y--;
		}
	}

	// 右上をひっくり返す
	public void reverseRightTop(int x, int y) {
		while (true) {
			// 座標がボードの上端または右端に到達した場合の処理
			if (x > 7 || y < 2) {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}

			// 隣が相手の色だった場合
			if (Main.board[x + 1][y - 1] == Main.enemyOsero) {
				// ひっくり返す座標をコピーボードに保存する
				copyBoard[x + 1][y - 1] = Main.userOsero;

				// 隣が自分の色だった場合
			} else if (Main.board[x + 1][y - 1] == Main.userOsero) {
				// 変更内容をオリジナルのボードに反映する
				Main.board = CB.copySaveBoard(copyBoard);
				break;

				// 隣が空白だった場合
			} else {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}
			x++;
			y--;
		}
	}

	public void reverseLeftBottom(int x, int y) {
		while (true) {

			// 座標がボードの端に到達した場合の処理
			if (x < 2 || y > 7) {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}

			// 隣が相手の色だった場合
			if (Main.board[x - 1][y + 1] == Main.enemyOsero) {
				// ひっくり返す座標をコピーボードに保存する
				copyBoard[x - 1][y + 1] = Main.userOsero;

				// 隣が自分の色だった場合
			} else if (Main.board[x - 1][y + 1] == Main.userOsero) {
				// 変更内容をオリジナルのボードに反映する
				Main.board = CB.copySaveBoard(copyBoard);
				break;

				// 隣が空白だった場合
			} else {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}
			x--;
			y++;
		}
	}

	// 右下をひっくり返す
	public void reverseRightBottom(int x, int y) {
		while (true) {

			// 座標がボードの端に到達した場合の処理
			if (x > 7 || y > 7) {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}

			// 隣が相手の色だった場合
			if (Main.board[x + 1][y + 1] == Main.enemyOsero) {
				// ひっくり返す座標をコピーボードに保存する
				copyBoard[x + 1][y + 1] = Main.userOsero;

				// 隣が自分の色だった場合
			} else if (Main.board[x + 1][y + 1] == Main.userOsero) {
				// 変更内容をオリジナルのボードに反映する
				Main.board = CB.copySaveBoard(copyBoard);
				break;

				// 隣が空白だった場合
			} else {
				// コピーボードをリセットする
				copyBoard = CB.copySaveBoard(Main.board);
				break;
			}
			x++;
			y++;
		}
	}
}
