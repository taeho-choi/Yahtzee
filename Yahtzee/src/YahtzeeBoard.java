import java.util.Scanner;
public class YahtzeeBoard {
	private Scanner scanner;
	private int numPlayers;
	private Player[] players;
	private Dice[] dice;			// �ֻ��� �迭
	private boolean[] chosenDice;	// ������ �ֻ��� �迭
	private static int player;		// ��Ƽ�÷��̾ �������� �� � �÷��̾� �������� ��Ÿ��
	private static int roll;		// 0,1,2 �ֻ��� �� �� ������ ���� �����ϴ� ����
	private static int round;		// 0,1,2, ... ,12,13�� ���带 �����ϴ� ����
	public YahtzeeBoard() {
		scanner = new Scanner(System.in);
		numPlayers = 1;
		players = new Player[numPlayers];
		players[0] = new Player("����ȣ");
		dice = new Dice[5];
		for(int i = 0; i < 5; i++)
			dice[i] = new Dice();
		chosenDice = new boolean[5];
	}
	public void run() {
		System.out.println("=================== 1�ο� YAHTZEE GAME =================");
		for(int i = 0; i < 13; i++) {
			players[0].setScore(0, i); 		// 13�� �������� 0���� �ʱ�ȭ
			players[0].setAtUsed(i, false); // 13�� �������� �������� ���� used false�� �ʱ�ȭ
		}
		for(round = 0; round < 13; round++) {
			for(int i = 0; i < 5; i++)
				chosenDice[i] = false;		// �ֻ��� 5���� �������� ���� false�� �ʱ�ȭ
			for(roll = 0; roll < 3; roll++) { // 5�� �ֻ��� �߿��� �������� ���� �͵��� 3�� ������.
				System.out.println("\t===== "+(roll+1)+"��° �ֻ����� ������!!");
				for(int i = 0; i < 5; i++) {
					if(chosenDice[i] == false)	// �������� ���� �ֻ����� ������.
						dice[i].rollDie();
					System.out.println("\t"+(i+1) + "��° �ֻ��� : " + dice[i].getRoll() + ", ��������: " + (chosenDice[i]?"O":"X"));
				}
				if(roll != 2) { // roll == 0, 1, �� ù ��°�� �� ��° ���� ���� �ֻ����� �����ؼ� �����Ѵ�.
					for(int i = 0; i < 5; i++) {
						System.out.print("\t >>>>������ �ֻ�������(1-5), �����ǳ�(0) :");
						int n = scanner.nextInt();
						if(n == 0)
							break;
						chosenDice[n - 1] = true;
					}
				}
			}
			printCategory();
			chooseCategory();
			printCategory();
		}
	}
	public void printCategory() {
		System.out.println("======================= ������ =====================");
		System.out.println("\t\t[[ī�װ�]]\t\t[[����]]");
		for(int i = 0; i < 6; i++) {
			if(i < 2)
				System.out.print("[" + (i + 1) + "]" + Configuration.getConfigs()[i] + "\t\t\t\t\t");
			else
				System.out.print("[" + (i + 1) + "]" + Configuration.getConfigs()[i] + "\t\t\t\t");
			if(players[0].getUsed()[i])
				System.out.println(players[0].getScores()[i]);
			else
				System.out.println();
		}
		System.out.println("   "+Configuration.getConfigs()[6]+"\t\t\t\t"+players[0].getUpperScore());
		System.out.print("   "+Configuration.getConfigs()[7]+"\t\t\t");
		if (players[0].getUpperScore() > 63)
			System.out.println(35);
		else
			System.out.println(0);
		
		for(int i = 0; i < 7; i++) {
			if(i == 5 || i == 6)
				System.out.print("[" + (i + 7) + "]" + Configuration.getConfigs()[i + 8] + "\t\t\t\t");
			else
				System.out.print("[" + (i + 7) + "]" + Configuration.getConfigs()[i + 8] + "\t\t\t");
			if(players[0].getUsed()[i + 6])
				System.out.println(players[0].getScores()[i + 6]);
			else
				System.out.println();
		}
		System.out.println("    "+Configuration.getConfigs()[15]+"\t\t\t"+players[0].getLowerScore());
		System.out.println("    "+Configuration.getConfigs()[16]+"\t\t\t\t"+players[0].getTotalScore());	
	}
	public void chooseCategory() {
		System.out.println("     ----------------------------");
		System.out.print("     ī�װ� ���� (1-13)>>");
		int n = scanner.nextInt();
		players[0].setAtUsed(n - 1, true);
		int score = Configuration.score(n-1, dice);
		players[0].setScore(score, n-1);
	}
	public static void main(String[] args) {
		YahtzeeBoard yb = new YahtzeeBoard();
		yb.run();

	}

}
