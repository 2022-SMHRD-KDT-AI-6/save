
public class model {
	
	//����
	private int maxRound; //������ ���� ����
	private int nowRound; // �������
	private int odState; // ���� ������� ������Ȳ
	private int pitcherNum; // ������� ���� ��ȣ
	private int hitterNum; // ������� ���� ��ȣ
	private int teamPoint; // ���� �� ����
	private int enemyPoint; // ���� ����� ����
	private int strikeCount; //��Ʈ����ũ ����
	private int outCount; //�ƿ� ����
	private int[] scoreSheet = {1, 3, 5, 10}; //1��Ÿ =1��, 2��Ÿ =3��, 3��Ÿ =5��, Ȩ�� =10��
	
	//Ȯ������ 
	private int buntSuccBase = 70; //��Ʈ ����Ȯ��
	private int[] buntChanceBase = {45,0,0,5,50,0}; //��Ʈ ���� �� ȹ������ Ȯ�� (1��Ÿ, 2��Ÿ, 3��Ÿ, �Ŀ�, �ƿ�, Ȩ��)
	private int normalSuccBase = 40; //���� ����Ȯ��
	private int[] normalChanceBase= {30,20,15,10,20,5}; //���� ���� �� ȹ������ Ȯ��(1��Ÿ, 2��Ÿ, 3��Ÿ, �Ŀ�, �ƿ�, Ȩ��)
	private int strongSuccBase = 30; //������ ���� Ȯ��
	private int[] strongChanceBase= {10,28,22,15,25,10}; //������ ���� �� ȹ�� ���� Ȯ��(1��Ÿ, 2��Ÿ, 3��Ÿ, �Ŀ�, �ƿ�, Ȩ��)
	private int breakingBallBase= 10;//��ȭ�� Ÿ���� Ÿ�� ������ �󸶳� �����
	private int[] breakingBallChanceBase= {80,5,15}; //��ȭ�� Ÿ�ڰ� ���� �� �߰� ȹ�� ���� Ȯ��(��ȭ ����, �Ŀ�� ����, �� �ܰ� ���)
	private int sliderBase= 5; //�����̴� Ÿ���� Ÿ�� ������ �󸶳� �����
	private int[] sliderChanceBase= {75,0,25}; //�����̴� Ÿ�ڰ� ���� �� �߰� ȹ�� ���� Ȯ��(��ȭ ����, �Ŀ�� ����, �� �ܰ� ���)
	private int fastBallBase= 20; //���� Ÿ���� Ÿ�� ������ �󸶳� �����
	private int[] fastBallChanceBase={50,10,40}; //���� Ÿ�ڰ� ���� �� �߰� ȹ�� ���� Ȯ��(��ȭ ����, �Ŀ�� ����, �� �ܰ� ���)
	private int enhanceBase = 50;
			

	private String[] charName = new String[5]; //ĳ������ �̸� 5�� �����
	private int[][] charInfo = new int[5][4]; //ĳ������ ����(Ÿ����, ������, ��ȭ����,����)
	private int[] comInfo = {100,100}; //��ǻ���� �⺻ Ÿ����, ������ 
	private int[][] charGameGrade = new int[5][7]; //���� �÷������� ������ ĳ���͵��� ����,(��Ʈ����ũ Ƚ��, �����ƿ� Ƚ��, Ÿ�� �ö�� Ƚ��, 1��Ÿ, 2��Ÿ, 3��Ÿ, Ȩ��),��Ʈ����ũ��, �����ƿ��� ��������, ������ Ÿ�� ����
	private int[][] charAllGrade = new int[5][7]; //����ĳ���͵��� ��ü����(��Ʈ����ũ Ƚ��, �����ƿ� Ƚ��, Ÿ�� �ö�� Ƚ��, 1��Ÿ, 2��Ÿ, 3��Ÿ, Ȩ��),��Ʈ����ũ��, �����ƿ��� ��������, ������ Ÿ�� ����
	private int[] userGrade= new int [5]; //������� ��ü ����
	

	private String user_id;	// ���� ����ڰ� �Է��� id
	private String user_pw; // ���� ����ڰ� �Է��� password
	private String user_money; // ��
	public int getMaxRound() {
		return maxRound;
	}
	public void setMaxRound(int maxRound) {
		this.maxRound = maxRound;
	}
	public int getNowRound() {
		return nowRound;
	}
	public void setNowRound(int nowRound) {
		this.nowRound = nowRound;
	}
	public int getOdState() {
		return odState;
	}
	public void setOdState(int odState) {
		this.odState = odState;
	}
	public int getPitcherNum() {
		return pitcherNum;
	}
	public void setPitcherNum(int pitcherNum) {
		this.pitcherNum = pitcherNum;
	}
	public int getHitterNum() {
		return hitterNum;
	}
	public void setHitterNum(int hitterNum) {
		this.hitterNum = hitterNum;
	}
	public int getTeamPoint() {
		return teamPoint;
	}
	public void setTeamPoint(int teamPoint) {
		this.teamPoint = teamPoint;
	}
	public int getEnemyPoint() {
		return enemyPoint;
	}
	public void setEnemyPoint(int enemyPoint) {
		this.enemyPoint = enemyPoint;
	}
	public int getStrikeCount() {
		return strikeCount;
	}
	public void setStrikeCount(int strikeCount) {
		this.strikeCount = strikeCount;
	}
	public int getOutCount() {
		return outCount;
	}
	public void setOutCount(int outCount) {
		this.outCount = outCount;
	}
	public int[] getScoreSheet() {
		return scoreSheet;
	}
	public void setScoreSheet(int[] scoreSheet) {
		this.scoreSheet = scoreSheet;
	}
	public int getBuntSuccBase() {
		return buntSuccBase;
	}
	public void setBuntSuccBase(int buntSuccBase) {
		this.buntSuccBase = buntSuccBase;
	}
	public int[] getBuntChanceBase() {
		return buntChanceBase;
	}
	public void setBuntChanceBase(int[] buntChanceBase) {
		this.buntChanceBase = buntChanceBase;
	}
	public int getNormalSuccBase() {
		return normalSuccBase;
	}
	public void setNormalSuccBase(int normalSuccBase) {
		this.normalSuccBase = normalSuccBase;
	}
	public int[] getNormalChanceBase() {
		return normalChanceBase;
	}
	public void setNormalChanceBase(int[] normalChanceBase) {
		this.normalChanceBase = normalChanceBase;
	}
	public int getStrongSuccBase() {
		return strongSuccBase;
	}
	public void setStrongSuccBase(int strongSuccBase) {
		this.strongSuccBase = strongSuccBase;
	}
	public int[] getStrongChanceBase() {
		return strongChanceBase;
	}
	public void setStrongChanceBase(int[] strongChanceBase) {
		this.strongChanceBase = strongChanceBase;
	}
	public int getBreakingBallBase() {
		return breakingBallBase;
	}
	public void setBreakingBallBase(int breakingBallBase) {
		this.breakingBallBase = breakingBallBase;
	}
	public int[] getBreakingBallChanceBase() {
		return breakingBallChanceBase;
	}
	public void setBreakingBallChanceBase(int[] breakingBallChanceBase) {
		this.breakingBallChanceBase = breakingBallChanceBase;
	}
	public int getSliderBase() {
		return sliderBase;
	}
	public void setSliderBase(int sliderBase) {
		this.sliderBase = sliderBase;
	}
	public int[] getSliderChanceBase() {
		return sliderChanceBase;
	}
	public void setSliderChanceBase(int[] sliderChanceBase) {
		this.sliderChanceBase = sliderChanceBase;
	}
	public int getFastBallBase() {
		return fastBallBase;
	}
	public void setFastBallBase(int fastBallBase) {
		this.fastBallBase = fastBallBase;
	}
	public int[] getFastBallChanceBase() {
		return fastBallChanceBase;
	}
	public void setFastBallChanceBase(int[] fastBallChanceBase) {
		this.fastBallChanceBase = fastBallChanceBase;
	}
	public int getEnhanceBase() {
		return enhanceBase;
	}
	public void setEnhanceBase(int enhanceBase) {
		this.enhanceBase = enhanceBase;
	}
	public String[] getCharName() {
		return charName;
	}
	public void setCharName(String[] charName) {
		this.charName = charName;
	}
	public int[][] getCharInfo() {
		return charInfo;
	}
	public void setCharInfo(int[][] charInfo) {
		this.charInfo = charInfo;
	}
	public int[] getComInfo() {
		return comInfo;
	}
	public void setComInfo(int[] comInfo) {
		this.comInfo = comInfo;
	}
	public int[][] getCharGameGrade() {
		return charGameGrade;
	}
	public void setCharGameGrade(int[][] charGameGrade) {
		this.charGameGrade = charGameGrade;
	}
	public int[][] getCharAllGrade() {
		return charAllGrade;
	}
	public void setCharAllGrade(int[][] charAllGrade) {
		this.charAllGrade = charAllGrade;
	}
	public int[] getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(int[] userGrade) {
		this.userGrade = userGrade;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_money() {
		return user_money;
	}
	public void setUser_money(String user_money) {
		this.user_money = user_money;
	}
}
	