
public class model {
	
	//변수
	private int maxRound; //게임을 끝낼 라운드
	private int nowRound; // 현재라운드
	private int odState; // 현재 사용자의 공수상황
	private int pitcherNum; // 사용자의 투수 번호
	private int hitterNum; // 사용자의 투수 번호
	private int teamPoint; // 현재 팀 점수
	private int enemyPoint; // 현재 상대팀 점수
	private int strikeCount; //스트라이크 개수
	private int outCount; //아웃 개수
	private int[] scoreSheet = {1, 3, 5, 10}; //1루타 =1점, 2루타 =3점, 3루타 =5점, 홈런 =10점
	
	
	private String[] charName = new String[5]; //캐릭터의 이름 5개
	

	private String user_id;	// 현재 사용자가 입력한 id
	private String user_pw; // 현재 사용자가 입력한 password
	private String user_money; // 돈
	
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
	