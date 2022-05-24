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
						model md2 = con.bringCharInfo(md);
						String[] charName = md2.getCharName();
						md.setCharName(charName);
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
					viewGameStart(md);
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
						else { //DB에 캐릭터가 있음
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
					if(con.checkChar(md) == true) {
						model md2 = con.bringCharInfo(md);
						String[] charName = md2.getCharName();
							
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
		String gameState = "OFFEN";
		String[] charName = md.getCharName();
		int actNum = 0;
		
		System.out.println("=====게임시작=====");
		
		//라운드수 입력
		do{
			System.out.print("라운드 수(최대 10) : ");
			maxRound = sc.nextInt();
			System.out.println("\n\n\n");
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
		
		String result = "";
		while(true) {
			
			switch(gameState) {
				
				case("OFFEN"):
					result = "";
					System.out.println("");
					System.out.println("============ 현재 라운드 : " + md.getMaxRound() + "-" + md.getNowRound() + " ===========");
					System.out.println("[공격]		팀 포인트 :" + md.getTeamPoint() + " 적팀 포인트" + md.getEnemyPoint());
					System.out.println("STRIKE	: " + md.getStrikeCount() + "\nOUT	: " + md.getOutCount());
					System.out.print(md.getHitterNum() + " 번 이름 : " + charName[md.getHitterNum()-1]);
					System.out.print(" 번트[1] 스윙[2] 강스윙[3] >> ");
					actNum = sc.nextInt();
					int odState = md.getOdState();
					
					if(con.isHitBall(md)) {
						gameState = "HITBALL";
					}
					else {
						gameState = "STRIKE";
					}
					
					break;
					
				case("DEFEN"):
					result = "";
					System.out.println("");
					System.out.println("============ 현재 라운드 : " + md.getMaxRound() + "-" + md.getNowRound() + " ===========");
					System.out.println("[수비]		팀 포인트 :" + md.getTeamPoint() + " 적팀 포인트" + md.getEnemyPoint());
					System.out.println("STRIKE	: " + md.getStrikeCount() + "\nOUT	: " + md.getOutCount());
					System.out.print(md.getHitterNum() + "번 이름 :  " + charName[md.getHitterNum()-1]);
					System.out.print(" 변화구[1] 슬라이더[2] 직구[3] >> ");
					actNum = sc.nextInt();
				
					if(con.isHitBall(md)) {
						gameState = "HITBALL";
					}
					else {
						gameState = "STRIKE";
					}
					break;
				
				case("STRIKE"):
					System.out.println("\n\n공을 못쳤습니다.");
					System.out.println((md.getStrikeCount()+1) + "STRIKE!");
					int strike = md.getStrikeCount();
					strike++;
					md.setStrikeCount(strike);

					
					odState = md.getOdState();
					//공격진행
					if(odState == 0) {
						gameState = "OFFEN";
					}
					//수비진행
					else {
						gameState = "DEFEN";
					}
					
					break;
					
				case("HITBALL"):
					
					odState = md.getOdState();
					int teamPoint = md.getTeamPoint();
					int enemyPoint = md.getEnemyPoint();
					int strikeCount = md.getStrikeCount();
					int outCount = md.getOutCount();
					
					System.out.println("\n\n공을 쳤습니다.");
					result = con.hitBall(md);
					System.out.println(result + "!");

					if(odState==0) {
						switch(result) { 
						case "ONEBASE": 
							teamPoint += 1;
							md.setTeamPoint(teamPoint);
							md.setStrikeCount(0);
							break;
							
						case "DOUBLE": 
							teamPoint += 3;
							md.setTeamPoint(teamPoint);
							md.setStrikeCount(0);
							break;
							
						case "TRIPLE": 
							teamPoint += 5;
							md.setTeamPoint(teamPoint);
							md.setStrikeCount(0);
							break;
							
						case "HOMERUN": 
							teamPoint += 10;
							md.setTeamPoint(teamPoint);
							md.setStrikeCount(0);
							break;
							
						case "FOUL":
							strikeCount += 1;
							md.setStrikeCount(strikeCount);
							
							break;
							
						case "OUT":
							outCount += 1;
							md.setOutCount(outCount);
							md.setStrikeCount(0);
							break;
						}
					}
					else {
						switch(result) {
						case "STRIKE":
							System.out.println("\n\n공을 못쳤습니다");
							strikeCount += 1;
							md.setStrikeCount(strikeCount);
							break;
						case "OUT":
							outCount += 1;
							md.setOutCount(outCount);
							md.setStrikeCount(0);
							break;
						case "ONEBASE":
							enemyPoint += 1;
							md.setEnemyPoint(enemyPoint);
							md.setStrikeCount(0);
							break;
						case "DOUBLE":
							enemyPoint += 3;
							md.setEnemyPoint(enemyPoint);
							md.setStrikeCount(0);
							break;
						case "TRIPLE":
							enemyPoint += 5;
							md.setEnemyPoint(enemyPoint);
							md.setStrikeCount(0);
							break;
						case "HOMERUN":
							enemyPoint += 10;
							md.setEnemyPoint(enemyPoint);
							md.setStrikeCount(0);
							break;
						case "FOUL":
							strikeCount += 1;
							md.setStrikeCount(strikeCount);
							break;
						
						}
					}
					//공격진행
					if(odState == 0) {
						gameState = "OFFEN";
					}
					//수비진행
					else {
						gameState = "DEFEN";
					}
					
					break;
					
				case("OUT"):
					System.out.println((md.getOutCount()+1) + " OUT!");
					outCount = md.getOutCount();
					outCount++;
					md.setStrikeCount(0);
					md.setOutCount(outCount);
					break;
					
				case("CHANGE"):
					if(md.getMaxRound() != md.getNowRound()) {
						System.out.println("\n\n공수 교대");
					}
					odState = md.getOdState();
					md.setStrikeCount(0);
					md.setOutCount(0);
					
					//공격 - > 수비
					if(odState == 0) {
						odState = 1;
						md.setOdState(odState);
						gameState = "DEFEN";
					}
					//수비 - > 공격
					
					else {
						nowRound++;
						md.setNowRound(nowRound);
						odState = 0;
						md.setOdState(odState);
						gameState = "OFFEN";
					}
					
			}
			
			//아웃카운트 증가
			if(md.getStrikeCount() > 2 && result.equals("FOUL") != true ) {
				System.out.println((md.getOutCount()+1) + " OUT!");
				int outCount = md.getOutCount();
				md.setStrikeCount(0);
				outCount++;
				md.setOutCount(outCount);
			}
			//아웃 3회 공수교대
			if(md.getOutCount() > 2) {
				gameState = "CHANGE";
			}
			
			
			
			if(md.getNowRound() > md.getMaxRound()) {
				//System.out.println("현재라운드 : " + md.getNowRound() + "마지막 라운드 : " + md.getMaxRound());
				System.out.println("\n\n게임종료");
				System.out.println("최종 스코어 팀 :" + md.getTeamPoint() + " 적팀 : " + md.getEnemyPoint());
				break;
			}
		}
		
		
		
	}

		

	public static void viewMakeChar(model md) {
		
		Scanner sc = new Scanner(System.in);
		Controller con = new Controller();
		
		System.out.println("=====캐릭터 생성=====");
		
			String[] character = new String[5];
			
			//캐릭터 생성
			for(int i = 0; i < 5; i++) {
				System.out.print((i+1) + "번째 캐릭터의 이름입력 :");
				System.out.print("이름 : ");
				character[i] = sc.next();
				md.setCharName(character);
			}
			
			//생성한 캐릭터 이름 중복없음
			if(checkName(character)) {
				md.setCharName(character);
				System.out.println("캐릭터 생성 성공!");
				con.makeChar(md);
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
