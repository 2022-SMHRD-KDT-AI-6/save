import java.util.Scanner;

public class View {

	public static void main(String[] args) {
		//화면
//		가입로그인 -> 로그인, 회원가입
		
//		메인메뉴 -> 게임 / 생성삭제 / 강화 / 정보확인변경 / 게임종료
//		생성삭제 -> 생성 / 삭제
//		정보확인변경 -> 유저정보 / 캐릭터정보 / 타순 
		
		Scanner sc = new Scanner(System.in);
		Controller con = new Controller();
		model md = new model();
		
		String game_page = "가입로그인";
		int user_select = -1;
		String id = "";
		String pw = "";
		
		while(true) {
			
			switch(game_page) {
			
			case("가입로그인"):
				System.out.println("=====야구게임=====");
				System.out.print("로그인[1] 회원가입[2] >> ");
				user_select = sc.nextInt();
				
				if(user_select == 1) { 
					//로그인
					System.out.println("=====로그인=====");
					System.out.println("ID와 PW를 입력해주세요");
					System.out.print("ID >> ");
					id = sc.next();
					md.setUser_id(id);
					System.out.print("PW >> ");
					pw = sc.next();
					md.setUser_pw(pw); // 이거 id라고 써놓음...
					
					if(con.loginId(md)) {
						System.out.println("로그인 성공!");
						//con.bringUserInfo(md);
						game_page = "메인메뉴";
					}
					else {
					System.out.println("로그인 실패");
					}
				}
				// 회원가입
				else if(user_select == 2) {
					System.out.println("=====회원가입=====");
					System.out.println("가입할 ID와 PW를 입력해주세요");
					System.out.print("ID >> ");
					id = sc.next();
					md.setUser_id(id);
					System.out.print("PW >> ");
					pw = sc.next();
					md.setUser_pw(pw);
					
					if(con.makeId(md)) { //아이디 생성 시도
						System.out.println("아이디 생성 성공!");
					}
					else {
						System.out.println("이미 존재하는 아이디입니다.");
					}
				}
				
				else {
					System.out.println("숫자를 잘못 입력하셨습니다.");
				}
				
				break;
				
//----------------------------------------------------------------------------------------
				//메인메뉴
			case("메인메뉴"):
				System.out.println("=====메인메뉴=====");
				System.out.println("게임시작[1] 캐릭터 생성 또는 삭제[2] 캐릭터 확인[3] 종료[4]");
				user_select = sc.nextInt();
//----------------------------------------------------------------------------------------				
				//게임시작
				if(user_select == 1) { 
					//viewGameStart(md);
					System.out.println("미구현");
				}
//----------------------------------------------------------------------------------------				
				// 캐릭터 생성 또는 삭제
				else if(user_select == 2) { 
					System.out.println("=====캐릭터 생성 또는 삭제=====");
					System.out.println("캐릭터 생성[1] 캐릭터 삭제[2]");
					user_select = sc.nextInt();
					
					if(user_select == 1) { // 캐릭터 생성
						if(con.checkChar(md) == false) { //DB에 캐릭터가 없다면
							viewMakeChar(md); //캐릭터 생성시작
						}
						else { //DB에 캐릭터가 없음
							System.out.println("캐릭터를 생성할수없습니다.");
						}
						
					}
					else if(user_select == 2) { //캐릭터 삭제
						if(con.checkChar(md)) {
							System.out.println("캐릭터 삭제 완료");
							con.deleteChar(md); //캐릭터 삭제
						}
						else { // 삭제할 캐릭터가 없음
							System.out.println("삭제할 캐릭터가 없습니다.");
						}
					}
					else { // 잘못된 번호 입력
						System.out.println("잘못된 번호 입력");
					}	
				}
//----------------------------------------------------------------------------------------
					
				// 캐릭터 확인
				else if(user_select == 3) {
					System.out.println("=====캐릭터 확인=====");
					
					//캐릭터가 있다면 
					if(con.checkChar(md) == false) {
						String[] charName = md.getCharName();
							
							for(int i = 0; i < charName.length; i++) {
								System.out.println((i+1) + "번 이름 : " + charName[i]);
							}
						}
					else {
						System.out.println("캐릭터가 없습니다.");
					}		
				}
//----------------------------------------------------------------------------------------				
				// 종료
				else if(user_select == 4) {
					System.out.println("게임종료");
					game_page = "게임종료";
				}
			}
//----------------------------------------------------------------------------------------
			//게임종료면 while 탈출
			if(game_page.equals("게임종료")) {
				break;
			}
		}

		
	}
	

	public static void viewGameStart(model md) {
		//1. 치는거, 던지는거, 스트라이크, 쳤다, 아웃, 공수교대, 게임종료
		Scanner sc = new Scanner(System.in);
		Controller con = new Controller();
		
		int maxRound = 0;
		int nowRound = 1;
		String gameState = "공격";
		String[] charName = md.getCharName();
		int actNum = 0;
		
		System.out.println("=====게임시작=====");
		
		//라운드수 입력
		do{
			System.out.print("라운드 수(최대 10) : ");
			maxRound = sc.nextInt();
			if(maxRound > 10) {
				System.out.println("10보다 작게 입력해주세요.");
			}
			else {
				//게임시작전 초기값 설정 라운드수, 현재라운드, 타자번호, 공수상황
				md.setMaxRound(maxRound);
				md.setNowRound(1);
				md.setHitterNum(1);
				md.setOdState(0);
				md.setTeamPoint(0);
				md.setEnemyPoint(0);
				md.setStrikeCount(0);
				md.setOutCount(0);
			}
		}while(maxRound >= 10);
		
		while(true) {
			switch(gameState) {
				
				case("OFFEN"):
					System.out.println("=====공격=====");
					System.out.println("팀 포인트 :" + md.getTeamPoint() + "적팀 포인트" + md.getEnemyPoint());
					System.out.print(md.getHitterNum() + "번 이름 :  " + charName[md.getHitterNum()-1]);
					System.out.print("번트[1] 스윙[2] 강스윙[3] >> ");
					actNum = sc.nextInt();
					
					if(con.isHitBall(md)) {
						gameState = "HITBALL";
					}
					
					break;
					
				case("DEFEN"):
					System.out.println("=====수비=====");
					System.out.println("팀 포인트 :" + md.getTeamPoint() + "적팀 포인트" + md.getEnemyPoint());
					System.out.print(md.getHitterNum() + "번 이름 :  " + charName[md.getHitterNum()-1]);
					System.out.print("변화구[1] 슬라이더[2] 직구[3] >> ");
					actNum = sc.nextInt();
				
					if(con.isHitBall(md)) {
						gameState = "HITBALL";
					}
					else {
						gameState = "STRIKE";
					}
					break;
				
				case("STRIKE"):
					System.out.println("STRIKE!");
					int strike = md.getStrikeCount();
					strike++;
					md.setStrikeCount(strike);
					break;
					
				case("HITBALL"):
					System.out.println("공을 쳤습니다.");
					String result = con.hitBall(md);
					System.out.println("결과 : " + result);
					con.playUpdate(md, gameState);
					break;
					
				case("OUT"):
					System.out.println("아웃");
					int outCount = md.getOutCount();
					outCount++;
					break;
					
				case("CHANGE"):
					System.out.println("공수 교대");
					int odState = md.getOdState();
					md.setStrikeCount(0);
					md.setOutCount(0);
					
					//공격 - > 수비
					if(odState == 0) {
						odState = 1;
					}
					//수비 - > 공격
					else {
						nowRound++;
						md.setNowRound(nowRound);
						odState = 0;
					}
					md.setOdState(odState);
					
					break;
			}
			
			//아웃카운트 증가
			if(md.getStrikeCount() > 2) {
				int outCount = md.getOutCount();
				md.setStrikeCount(0);
				outCount++;
				md.setOutCount(outCount);
			}
			//아웃 3회 공수교대
			if(md.getOutCount() > 2) {
				gameState = "CHANGE";
			}
			
			if(md.getNowRound() == md.getMaxRound()) {
				System.out.println("게임종료");
				System.out.println("최종 스코어 팀 :" + md.getTeamPoint() + "적팀 : " + md.getEnemyPoint());
				break;
			}
		}
		
		
		
	}
//	public static void viewGameStart2(model md) {
//		Scanner sc = new Scanner(System.in);
//		
//		String[] charName = md.getCharName();
//		
//		System.out.println("=====게임시작=====");
//		
//		//게임 라운드수 입력
//		do{
//			System.out.print("라운드 수(최대 10) : ");
//			maxRound = sc.nextInt();
//			if(maxRound > 10) {
//				System.out.println("10보다 작게 입력해주세요.");
//			}
//			else {
//				//게임시작전 초기값 설정 라운드수, 현재라운드, 타자번호, 공수상황
//				md.setMaxRound(maxRound);
//				md.setNowRound(1);
//				md.setHitterNum(0);
//				md.setOdState(0);
//			}
//		}while(maxRound >= 10);
//		
//		int nowRound = 1;
//		
//		//투수 선택
//		while(true) {
//			//////////////////////////////////////////////////////////여기서부터 진행
//			int outCnt = 0;
//			//공격상황
//			if(md.getOdState() == 0) {
//				int hitterNum = sc.nextInt();
//				System.out.println("공격상황");
//				System.out.println("현재팀 점수 : " + md.getTeamPoint() + "적팀 점수 : " + md.getEnemyPoint());
//				System.out.print(hitterNum + "번 이름 :  " + charName[hitterNum-1]);
//				System.out.print(" 타구력 : " + charInfo[hitterNum-1][0] + " ");
//				System.out.print(" 강화상태 : " + charInfo[hitterNum-1][2] + " ");
//				System.out.print(" 순번 : " + charInfo[hitterNum-1][3] + " ");
//				System.out.println();
//					
//				System.out.println("타자의 행동 선택>> 번트[1] 스윙[2] 강스윙[3]");
//				md.setActNum(hitterNum);
//				
//				//공을 쳤을때
//				if(isHitBall()) {
//					System.out.println("공을 쳤습니다!");
//					String Ballstate = hitBall();
//					int[] scoreSheet = md.getScoreSheet();
//					int teamPoint = md.getTeamPoint();
//					md.playUpdate(Ballstate);
//					System.out.println("다음 선수");
//					hitterNum++;
//				}
//				//공을 못쳤을때
//				else {
//					//스트라이크 당함
//					System.out.println("스트라이크");
//					int strikeCnt = md.getStrikeCount();
//					strikeCnt++;
//					if(strikeCnt >= 3) {
//						//삼진아웃
//						System.out.println("삼진아웃");
//						md.setStrikeCount(0);
//						outCnt++;
//						System.out.println("다음 선수");
//						hitterNum++;
//					}
//					
//					
//					
//				}
//			}
//			
//			//수비상황
//			else {
//				System.out.print("투수를 선택해주세요 : ");
//				pitcherNum = sc.nextInt();
//				md.setPitcherNum(pitcherNum);
//				
//				System.out.println("수비");
//				System.out.println("현재팀 점수 : " + md.getTeamPoint() + "적팀 점수 : " + md.getEnemyPoint());
//				System.out.print(pitcherNum + "번 이름 :  " + charName[pitcherNum-1]);
//				System.out.print(" 투구력 : " + charInfo[pitcherNum-1][1] + " ");
//				System.out.print(" 강화상태 : " + charInfo[pitcherNum-1][2] + " ");
//				System.out.println();
//				
//				
//				
//			}
//			
//			//아웃 3회 공수교대
//			if(outCnt >= 3) {
//				System.out.println("공수교대");
//				int odState = md.getOdState();
//				//공격 -> 수비
//				if(odState == 0) {
//					System.out.print("이번 게임 투수를 선택해주세요");
//					pitcherNum = sc.nextInt();
//					if(pitcherNum < 1 && pitcherNum > 5) {
//						System.out.println("잘못된 번호 입력");
//					}
//					else {
//						//투수 번호 초기값 설정
//						md.setPitcherNum(pitcherNum);
//					}
//					md.setOdState(1);
//					nowRound++;
//				}
//				//수비 -> 공격
//				else {
//					nowRound++;
//					md.setNowRound(nowRound);
//					md.setOdState(0);;
//				}
//			}
//			
//			if(nowRound == maxRound) {
//			System.out.println(maxRound + "끝 게임종료");
//			break;
//			}
//			
//		}
		
		
	
	public static void viewMakeChar(model md) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=====캐릭터 생성=====");
		
			String[] character = new String[5];
			
			//캐릭터 생성
			for(int i = 0; i < 5; i++) {
				System.out.print((i+1) + "번째 캐릭터의 이름입력 :");
				System.out.print("이름 : ");
				character[i] = sc.next();					
			}
			
			//생성한 캐릭터 이름 중복없음
			if(checkName(character)) {
				md.setCharName(character);
				System.out.println("캐릭터 생성 성공!");
			}
			//생성한 캐릭터 중복 있음
			else {
				System.out.println("캐릭터 생성실패");
				System.out.println("이름을 중복하여 작성했습니다.");
			}
	}
	
	public static boolean checkName(String[] character) {
		for(int i = 0; i < character.length - 1; i++) {
			for(int j = i + 1; j < character.length; j++) {
				if(character[i].equals(character[j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	


}
