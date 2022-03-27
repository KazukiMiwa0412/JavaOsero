package osero;

import java.util.Scanner;

import osero.config.StartConfig;
import osero.middleware.CheckInputValue;
import osero.view.OseroCui;
import osero.view.OseroGui;

public class Main {
	// オセロの情報
	public static String white = "○";
	public static String black = "●";
	public static String blankSpace = "  ";

	// ユーザのオセロ情報
	public static String userOsero = black;
	public static String enemyOsero = white;

	// ボードの情報を保存する([横の座標、縦の座標])
	public static String board[][] = new String[9][9];

	// 入力値を一時保存する
	static String x = "0";

	// 手数
	public static int count = 0;
	public static int subCount = 0;

	public static int mode = 0;

	public static void main(String[] args) {
		StartConfig SC = new StartConfig();
		Scanner sc = new Scanner(System.in);
		CheckInputValue CIV = new CheckInputValue();

		System.out.println("オセロを開始します.");
		// ボードに初期値をセットする
		SC.boardInitValue();
		SC.boardSetup();

		// 遊び方を選択
		while (true) {
			System.out.println("遊び方（１：キーボード入力　２：GUI）入力してください");
			x = sc.next();
			if (CIV.checkInt1_2(x)) {
				mode = Integer.parseInt(x);
				break;
			} else {
				System.out.println("!!!!!!!!!!その値は無効です!!!!!!!!!!");
				System.out.println("1~2の半角数字を入力してください");
			}
		}

		switch (mode) {
		case 1:
			OseroCui OC = new OseroCui();
			OC.cuimode();
			break;
		case 2:
			// GUIでゲームをプレイ
			OseroGui DG = new OseroGui();
			DG.guiMode();
			break;
		default:
			break;
		}

	}

}
