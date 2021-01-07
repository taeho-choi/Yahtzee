import java.util.Scanner;
public class YahtzeeBoard {
	private Scanner scanner;
	private int numPlayers;
	private Player[] players;
	private Dice[] dice;			// 주사위 배열
	private boolean[] chosenDice;	// 간직한 주사위 배열
	private static int player;		// 멀티플레이어를 가정했을 때 어떤 플레이어 차례인지 나타냄
	private static int roll;		// 0,1,2 주사위 세 번 던지는 것을 관리하는 변수
	private static int round;		// 0,1,2, ... ,12,13번 라운드를 관리하는 변수
	public YahtzeeBoard() {
		scanner = new Scanner(System.in);
		numPlayers = 1;
		players = new Player[numPlayers];
		players[0] = new Player("최태호");
		dice = new Dice[5];
		for(int i = 0; i < 5; i++)
			dice[i] = new Dice();
		chosenDice = new boolean[5];
	}
	public void run() {
		System.out.println("=================== 1인용 YAHTZEE GAME =================");
		for(int i = 0; i < 13; i++) {
			players[0].setScore(0, i); 		// 13개 점수판을 0으로 초기화
			players[0].setAtUsed(i, false); // 13개 점수판을 선택하지 않은 used false로 초기화
		}
		for(round = 0; round < 13; round++) {
			for(int i = 0; i < 5; i++)
				chosenDice[i] = false;		// 주사위 5개를 선택하지 않은 false로 초기화
			for(roll = 0; roll < 3; roll++) { // 5개 주사위 중에서 선택하지 않은 것들을 3번 던진다.
				System.out.println("\t===== "+(roll+1)+"번째 주사위를 던진다!!");
				for(int i = 0; i < 5; i++) {
					if(chosenDice[i] == false)	// 간직하지 않은 주사위만 던진다.
						dice[i].rollDie();
					System.out.println("\t"+(i+1) + "번째 주사위 : " + dice[i].getRoll() + ", 간직여부: " + (chosenDice[i]?"O":"X"));
				}
				if(roll != 2) { // roll == 0, 1, 즉 첫 번째와 두 번째 던질 때만 주사위를 선택해서 간직한다.
					for(int i = 0; i < 5; i++) {
						System.out.print("\t >>>>간직할 주사위선택(1-5), 선택의끝(0) :");
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
		System.out.println("======================= 점수판 =====================");
		System.out.println("\t\t[[카테고리]]\t\t[[점수]]");
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
		System.out.print("     카테고리 선택 (1-13)>>");
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
