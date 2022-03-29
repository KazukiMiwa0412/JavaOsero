package osero.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import osero.Main;
import osero.config.StartConfig;
import osero.middleware.CheckInputValue;
import osero.middleware.CheckPutDown;
import osero.middleware.SearchSpace;
import osero.model.CountBlackWhite;
import osero.model.ReverseOsero;

public class OseroGui extends JPanel {
	static final int WIDTH = 1000; // 画面サイズ（幅）
	static final int HEIGHT = 800; // 画面サイズ（高さ）
	int lm = 50; // 左側余白
	int tm = 100; // 上側余白
	int cs = 50; // マスのサイズ
	int turn = 1; // 手番（1:黒，2:白)
	int winner = 0;// 勝者
	int StartCoordinateX = 65;// マス番号の左上のX座標
	int StartCoordinateY = 140;// マス番号の左上のY座標

	long startTime = System.currentTimeMillis();
	long sumTime;
	long startTime1 = System.currentTimeMillis();
	long endTime1 = System.currentTimeMillis();
	long sumTime1 = endTime1 - startTime1;
	long startTime2 = System.currentTimeMillis();
	long endTime2 = System.currentTimeMillis();
	long sumTime2 = endTime2 - startTime2;
	// インスタンス
	StartConfig SC = new StartConfig();
	ShowBoard SB = new ShowBoard();
	Scanner sc = new Scanner(System.in);
	ReverseOsero RO = new ReverseOsero();
	CheckInputValue CIV = new CheckInputValue();
	SearchSpace SS = new SearchSpace();
	CheckPutDown CPD = new CheckPutDown();
	CountBlackWhite CBW = new CountBlackWhite();

	// コンストラクタ（初期化処理）
	public OseroGui() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addMouseListener(new MouseProc());
	}

	// 画面描画
	@Override
	public void paintComponent(Graphics g) {

		// 背景
		g.setColor(new Color(239, 234, 234));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		Font font = new Font("ＭＳ 明朝", Font.BOLD, 32);
		g.setFont(font);

		// 盤面描画
		for (int i = 1; i < 9; i++) {

			// マス番号
			g.setColor(Color.black);
			g.drawString(String.valueOf(i), StartCoordinateX + cs * i, StartCoordinateY);
			g.drawString(String.valueOf(i), StartCoordinateX, StartCoordinateY + cs * i);

			int y = tm + cs * i;// マスのY座標
			for (int j = 1; j < 9; j++) {

				int x = lm + cs * j;// マスのX座標
				// マスの形と色の設定と描画
				g.setColor(new Color(0, 170, 0));
				g.fillRect(x, y, cs, cs);
				g.setColor(Color.black);
				g.drawRect(x, y, cs, cs);

				// 石の描画
				if (Main.board[j][i] != Main.blankSpace) {
					if (Main.board[j][i] == Main.black) {
						g.setColor(Color.black);
					} else {
						g.setColor(Color.white);
					}
					g.fillOval(x + cs / 10, y + cs / 10, cs * 8 / 10, cs * 8 / 10);
				}

				// (i,j)に石が置ける場合は□を表示
				if (CPD.canPutDown(j, i)) {
					g.setColor(Color.pink);
					g.drawRect(x + cs / 3, y + cs / 3, cs / 3, cs / 3);
				}
			}
		}

		// 上段のメッセージ表示
		g.setColor(Color.black);
		sumTime = (System.currentTimeMillis() - startTime) / 100;

		g.drawString("思考時間" + (sumTime / 10) + "." + (sumTime % 10) + "秒", 550, 70);

		if (winner == 0) {
			// 手番の表示
			if (Main.count % 2 == 0) {
				g.drawString("黒の" + ((Main.count / 2) + 1) + "手目です", 230, 70);
			} else {
				g.drawString("白の" + ((Main.count / 2) + 1) + "手目です", 230, 70);
			}
		} else if (winner == 1) {
			g.drawString("黒の勝ちです", 230, 70);
		} else if (winner == 2) {
			g.drawString("白の勝ちです", 230, 70);
		} else {
			g.drawString("引き分けです", 230, 70);
		}
		g.drawString("黒" + CBW.countBlack(Main.board) + " 白" + CBW.countWhite(Main.board), 230, 620);
		// 時間の表示
		g.drawString("黒の経過時間  " + (sumTime1 / 10) + "." + (sumTime1 % 10) + "秒", 550, 270);
		g.drawString("白の経過時間  " + (sumTime2 / 10) + "." + (sumTime2 % 10) + "秒", 550, 420);
		repaint();
	}

	// ゲームの終了判定を行う
	public int judge() {
		// 石を置けるマスがないか判定
		if (!CPD.checkPutDown()) {
			Main.count++;
			// 次の相手の手番に置けるマスがないか判定
			if (!CPD.checkPutDown()) {
				// ゲーム終了していた場合
				int nb = CBW.countBlack(Main.board); // 黒石の数
				int nw = CBW.countWhite(Main.board); // 白石の数
				if (nb > nw) {
					return 1; // 黒の勝ち
				} else if (nb < nw) {
					return 2; // 白の勝ち
				} else {
					return 3; // 引き分け
				}
			} else {
				Main.count--;
			}
		}
		// ゲーム続行
		return 0;
	}

	// クリックされた時の処理用のクラス
	class MouseProc extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();// クリックされたX座標
			int y = e.getY();// クリックされたY座標

			// 盤の外側がクリックされたときは何もしないで終了
			if (x < lm)
				return;
			if (x >= lm + cs * 9)
				return;
			if (y < tm)
				return;
			if (y >= tm + cs * 9)
				return;

			// クリックされたマスを特定
			int row = (y - tm) / cs;
			int col = (x - lm) / cs;

			if (CPD.canPutDown(col, row)) {
				// 入力した座標にオセロを置く
				RO.putOsero(col, row);
				// オリジナルボードをコピーボードにコピーする
				RO.makeSaveBoard(Main.board);

				// ひっくり返す処理
				RO.reverseLeft(col, row);
				RO.reverseRight(col, row);
				RO.reverseTop(col, row);
				RO.reverseBottom(col, row);
				RO.reverseLeftTop(col, row);
				RO.reverseRightTop(col, row);
				RO.reverseLeftBottom(col, row);
				RO.reverseRightBottom(col, row);
				Main.count++;
				if (Main.count % 2 == 0) {
					Main.userOsero = Main.black;
					Main.enemyOsero = Main.white;
					endTime2 = System.currentTimeMillis();
					startTime1 = System.currentTimeMillis();
					sumTime2 += (endTime2 - startTime2) / 100;
				} else {
					Main.userOsero = Main.white;
					Main.enemyOsero = Main.black;
					endTime1 = System.currentTimeMillis();
					startTime2 = System.currentTimeMillis();
					sumTime1 += (endTime1 - startTime1) / 100;
				}
				startTime = System.currentTimeMillis();
				winner = judge();
			}

			// 再描画
			repaint();
		}
	}

	// 起動時
	public static void guiMode() {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new FlowLayout());
		f.getContentPane().add(new OseroGui());
		f.pack();
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}