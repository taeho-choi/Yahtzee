public class Configuration {
	private static String[] configs = {"Ones","Twos","Threes",
			"Fours","Fives","Sixes","Upper Scores",
			"Upper Bonus(35)","Three of a Kind",
			"Four of a Kind","Full House(25)",
			"Small Straight(30)","Large Straight(40)",
			"Yahtzee(50)", "Chance","Lower Scores","Total"};
	public static String[] getConfigs() {
		return configs;
	}
	public static int score(int row, Dice[] d) {
		int[] diceCount = {0, 0, 0, 0, 0, 0};
		int sum = 0;
		
		switch (row) {
		case 0: case 1: case 2: case 3: case 4: case 5:
			for (int i=0; i<5; i++)
				if (d[i].getRoll() == (row+1))
					sum += row+1;
			return sum;
			
		case 6://Three of a Kind
			for(int i = 0; i < 5; i++)
				diceCount[d[i].getRoll() - 1]++;
			for(int i = 0; i < 6; i++) {
				if(diceCount[i] >= 3) {
					for(int j = 0; j < 5; j++)
						sum += d[j].getRoll();
					return sum;
				}
			}
			return 0;
			
		case 7://Four of a Kind
			for(int i = 0; i < 5; i++)
				diceCount[d[i].getRoll() - 1]++;
			for(int i = 0; i < 6; i++) {
				if(diceCount[i] >= 4) {
					for(int j = 0; j < 5; j++)
						sum += d[j].getRoll();
					return sum;
				}
			}
			return 0;
			
		case 8://Full House
			for(int i = 0; i < 5; i++)
				diceCount[d[i].getRoll() - 1]++;
			boolean two = false, three = false;
			for(int i = 0; i < 6; i++) {
				if(diceCount[i] == 2)
					two = true;
				else if(diceCount[i] == 3)
					three = true;
			}
			if(two && three)
				return 25;
			else
				return 0;
			
		case 9://Small Straight
			for(int i = 0; i < 5; i++)
				diceCount[d[i].getRoll() - 1]++;

			if((diceCount[0] != 0 && diceCount[1] != 0 && diceCount[2] != 0 && diceCount[3] != 0) ||
				(diceCount[1] != 0 && diceCount[2] != 0 && diceCount[3] != 0 && diceCount[4] != 0) ||
				(diceCount[2] != 0 && diceCount[3] != 0 && diceCount[4] != 0 && diceCount[5] != 0))
				return 30;
			else
				return 0;
			
		case 10://Large Straight
			for(int i = 0; i < 5; i++)
				diceCount[d[i].getRoll() - 1]++;

			if((diceCount[0] != 0 && diceCount[1] != 0 && diceCount[2] != 0 && diceCount[3] != 0 && diceCount[4] != 0) ||
				(diceCount[1] != 0 && diceCount[2] != 0 && diceCount[3] != 0 && diceCount[4] != 0 && diceCount[5] != 0))
				return 40;
			else
				return 0;
			
		case 11://Yahtzee
			for(int i = 0; i < 5; i++)
				diceCount[d[i].getRoll() - 1]++;
			for(int i = 0; i < 6; i++) {
				if(diceCount[i] >= 5)
					return 50;
			}
			return 0;
			
			
		case 12://Chance
			for(int j = 0; j < 5; j++)
				sum += d[j].getRoll();
			return sum;
			
		default:
			return 0;
		}
	}
}
