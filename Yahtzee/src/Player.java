public class Player {
	private String name;
	private static final int UPPER = 6;
	private static final int LOWER = 7;
	private boolean[] used; // 13�� �������� ����ߴ��� ���θ� �����ϴ� �迭
	private int[] scores;   // 13�� �������� ���� �迭
	private int upperScore; // ���� 6�� UPPER Section ������ ��
	private int lowerScore; // ���� 7�� LOWER Section ������ ��
	private int totalScore; // upperScore + Bonus(35) + lowerScore
	public Player(String name) {
		this.name = name;
		this.used = new boolean[UPPER + LOWER];
		this.scores = new int[UPPER + LOWER];
	}
	public void setScore(int score, int index) {
		scores[index] = score;
	}
	public void setAtUsed(int index, boolean value) {
		used[index] = value;
	}
	public int[] getScores() {
		return scores;
	}
	public boolean[] getUsed() {
		return used;
	}
	public int getUpperScore() {	// ��� 6�� ī�װ� ������ ��
		upperScore = 0;
		for(int i = 0; i < UPPER; i++)
			upperScore += scores[i];
		return upperScore;
	}
	public int getLowerScore() {	// �ϴ� 7�� ī�װ� ������ ��
		lowerScore = 0;
		for(int i = UPPER; i < UPPER + LOWER; i++)
			lowerScore += scores[i];
		return lowerScore;
	}
	public int getTotalScore() {
		totalScore = getUpperScore() + getLowerScore();
		if(getUpperScore() > 63)
			totalScore += 35;
		return totalScore;
	}
}
