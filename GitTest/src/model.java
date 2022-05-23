
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
	
	//확률관련 
	private int buntSuccBase = 70; //번트 성공확률
	private int[] buntChanceBase = {45,0,0,5,50,0}; //번트 성공 시 획득점수 확률 (1루타, 2루타, 3루타, 파울, 아웃, 홈런)
	private int normalSuccBase = 40; //스윙 성공확률
	private int[] normalChanceBase= {30,20,15,10,20,5}; //스윙 성공 시 획득점수 확률(1루타, 2루타, 3루타, 파울, 아웃, 홈런)
	private int strongSuccBase = 30; //강스윙 성공 확률
	private int[] strongChanceBase= {10,28,22,15,25,10}; //강스윙 성공 시 획득 점수 확률(1루타, 2루타, 3루타, 파울, 아웃, 홈런)
	private int breakingBallBase= 10;//변화구 타자의 타구 성공률 얼마나 깎는지
	private int[] breakingBallChanceBase= {80,5,15}; //변화구 타자가 쳤을 때 추가 획득 점수 확률(변화 없음, 파울로 변경, 한 단계 상승)
	private int sliderBase= 5; //슬라이더 타자의 타구 성공률 얼마나 깎는지
	private int[] sliderChanceBase= {75,0,25}; //슬라이더 타자가 쳤을 때 추가 획득 점수 확률(변화 없음, 파울로 변경, 한 단계 상승)
	private int fastBallBase= 20; //직구 타자의 타구 성공률 얼마나 깎는지
	private int[] fastBallChanceBase={50,10,40}; //직구 타자가 쳤을 때 추가 획득 점수 확률(변화 없음, 파울로 변경, 한 단계 상승)
	private int enhanceBase = 50;
			

	private String[] charName = new String[5]; //캐릭터의 이름 5개 만들기
	private int[][] charInfo = new int[5][4]; //캐릭터의 정보(타구력, 투구력, 강화상태,순번)
	private int[] comInfo = {100,100}; //컴퓨터의 기본 타구력, 투구력 
	private int[][] charGameGrade = new int[5][7]; //현재 플레이중인 게임의 캐릭터들의 성적,(스트라이크 횟수, 삼진아웃 횟수, 타석 올라온 횟수, 1루타, 2루타, 3루타, 홈런),스트라이크와, 삼진아웃은 투수성적, 나머지 타자 성적
	private int[][] charAllGrade = new int[5][7]; //현재캐릭터들의 전체성적(스트라이크 횟수, 삼진아웃 횟수, 타석 올라온 횟수, 1루타, 2루타, 3루타, 홈런),스트라이크와, 삼진아웃은 투수성적, 나머지 타자 성적
	private int[] userGrade= new int [5]; //사용자의 전체 성적
	

	private String user_id;	// 현재 사용자가 입력한 id
	private String user_pw; // 현재 사용자가 입력한 password
	private String user_money; // 돈
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
	