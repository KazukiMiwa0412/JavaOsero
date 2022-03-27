package osero.view;

import java.util.Scanner;

import osero.Main;
import osero.config.StartConfig;
import osero.middleware.CheckInputValue;
import osero.middleware.CheckPutDown;
import osero.middleware.SearchSpace;
import osero.model.CountBlackWhite;
import osero.model.ReverseOsero;

public class OseroCui {

	// インスタンスの作成
	StartConfig SC = new StartConfig();
	ShowBoard SB = new ShowBoard();
	Scanner sc = new Scanner(System.in);
	ReverseOsero RO = new ReverseOsero();
	CheckInputValue CIV = new CheckInputValue();
	SearchSpace SS = new SearchSpace();
	CheckPutDown CPD = new CheckPutDown();
	CountBlackWhite CBW = new CountBlackWhite();
	// 入力値の情報を保持する
	private int inputX;
	private int inputY;

	// 入力値を一時保存する
	String x = "0";
	String y = "0";

	public void cuimode() {
		// キーボード入力でゲームをプレイ
		while (SS.searchSpace()) {
			if (Main.count % 2 == 0) {
				Main.userOsero = Main.black;
				Main.enemyOsero = Main.white;
			} else {
				Main.userOsero = Main.white;
				Main.enemyOsero = Main.black;
			}
			if (!CPD.checkPutDown()) {
				System.out.println("player" + ((Main.count % 2) + 1) + "の打てる手がありません");
				Main.count++;
				if (Main.subCount == Main.count - 1) {
					break;
				}
				Main.subCount = Main.count;
				continue;
			}
			// ボードを表示する
			SB.showBoard(Main.board);
			System.out.println("player" + ((Main.count % 2) + 1) + "の" + ((Main.count / 2) + 1) + "手目です。");
			while (true) {
				// x座標を入力する処理
				System.out.println("横の座標を1～8の半角数字で入力してください");
				x = sc.next();
				if (CIV.checkInt1_8(x)) {
					inputX = Integer.parseInt(x);
					break;
				} else {
					System.out.println("!!!!!!!!!!その値は無効です!!!!!!!!!!");
					System.out.println("1~8の半角数字を入力してください");
				}
			}

			while (true) {
				// Y座標を入力する処理
				System.out.println("縦の座標を1～8の半角数字で入力してください");
				y = sc.next();

				if (CIV.checkInt1_8(y)) {
					inputY = Integer.parseInt(y);
					break;
				} else {
					System.out.println("!!!!!!!!!!その値は無効です!!!!!!!!!!");
					System.out.println("1~8の半角数字を入力してください");
				}
			}
			if (!CPD.canPutDown(inputX, inputY)) {
				System.out.println("そのマスには置けません！");
				continue;
			}
			// 入力した座標にオセロを置く
			RO.putOsero(inputX, inputY);

			// オリジナルボードをコピーボードにコピーする
			RO.makeSaveBoard(Main.board);

			// ひっくり返す処理
			RO.reverseLeft(inputX, inputY);
			RO.reverseRight(inputX, inputY);
			RO.reverseTop(inputX, inputY);
			RO.reverseBottom(inputX, inputY);
			RO.reverseLeftTop(inputX, inputY);
			RO.reverseRightTop(inputX, inputY);
			RO.reverseLeftBottom(inputX, inputY);
			RO.reverseRightBottom(inputX, inputY);
			Main.count++;
		}
		judge();
	}

	public void judge() {
		// 勝敗出力
		SB.showBoard(Main.board);
		System.out.println("黒" + CBW.countBlack(Main.board) + "対白" + CBW.countWhite(Main.board));
		if (CBW.countBlack(Main.board) > CBW.countWhite(Main.board)) {
			System.out.println("player1の勝ち！");
		} else if (CBW.countBlack(Main.board) < CBW.countWhite(Main.board)) {
			System.out.println("player2の勝ち！");
		} else {
			System.out.println("引き分け！");
		}
	}
}
