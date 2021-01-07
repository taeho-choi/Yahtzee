public class Dice {
	private int roll; //주사위 눈의 값
	public void rollDie() { //주사위를 던져서 랜덤 값을 생성
		roll = (int)(Math.random() * 6 + 1);
	}
	public int getRoll() {
		return roll;
	}
}
