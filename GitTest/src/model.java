
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
	
	
	private String[] charName = new String[5]; //ĳ������ �̸� 5��
	

	private String user_id;	// ���� ����ڰ� �Է��� id
	private String user_pw; // ���� ����ڰ� �Է��� password
	private String user_money; // ��
	
	public model() {
		
	}
	
	public model(String rm) {
		this.user_id = rm;
	}
	
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
	public String[] getCharName() {
		return charName;
	}
	public void setCharName(String[] charName) {
		this.charName = charName;
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
	