public class Player {
	private String name;
	private static final int UPPER = 6;
	private static final int LOWER = 7;
	private boolean[] used; // 13개 점수판을 기록했는지 여부를 관리하는 배열
	private int[] scores;   // 13개 점수판의 점수 배열
	private int upperScore; // 상위 6개 UPPER Section 점수의 합
	private int lowerScore; // 하위 7개 LOWER Section 점수의 합
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
	public int getUpperScore() {	// 상단 6개 카테고리 점수의 합
		upperScore = 0;
		for(int i = 0; i < UPPER; i++)
			upperScore += scores[i];
		return upperScore;
	}
	public int getLowerScore() {	// 하단 7개 카테고리 점수의 합
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
