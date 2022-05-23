
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
}
	