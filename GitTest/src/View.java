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
					md.setUser_pw(id);
					
					if(con.loginId(md)) {
						System.out.println("로그인 성공!");
						bringUserInfo();
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
				
//-----------------------------------------------------------------------------
				
			case("메인메뉴"):
				System.out.println("=====메인메뉴=====");
				System.out.println("게임시작[1] 캐릭터 생성 또는 삭제[2] 강화소[3] 정보확인(변경)[4] 종료[5]");
				user_select = sc.nextInt();
				
				//게임시작
				if(user_select == 1) { 
					viewGameStart();
				}
				
				// 캐릭터 생성 또는 삭제
				else if(user_select == 2) { 
					System.out.println("=====캐릭터 생성 또는 삭제=====");
					System.out.println("캐릭터 생성[1] 캐릭터 삭제[2]");
					user_select = sc.nextInt();
					
					if(user_select == 1) { // 캐릭터 생성
						if(checkChar() == false) { //DB에 캐릭터가 없다면
							viewMakeChar(); //캐릭터 생성시작
						}
						else { //DB에 캐릭터가 없음
							System.out.println("캐릭터를 생성할수없습니다.");
						}
						
					}
					else if(user_select == 2) { //캐릭터 삭제
						if(checkChar()) {
							System.out.println("캐릭터 삭제 완료");
							deleteChar(); //캐릭터 삭제
						}
						else { // 삭제할 캐릭터가 없음
							System.out.println("삭제할 캐릭터가 없습니다.");
						}
					}
					else { // 잘못된 번호 입력
						System.out.println("잘못된 번호 입력");
					}	
				}

				// 강화소
				else if(user_select == 3) {
					if(checkChar()) {
						viewenhaceChar();				
					}
					else {
						System.out.println("강화할 캐릭터가 없습니다");
					}
					
					
				}
				// 정보확인(변경)
				else if(user_select == 4) {
					System.out.println("=====정보확인(변경)=====");
					System.out.println("성적확인[1] 캐릭터 성적확인[2] 타자순서바꾸기[3] 메인메뉴[4]");
					user_select = sc.nextInt();
					
					//사용자 성적 조회
					if(user_select == 1) {
						int[] userGrade = md.getUserGrade();
						System.out.println("=====성적확인=====");
						System.out.print("게임 : " + userGrade[0]);
						System.out.print("승리 : " + userGrade[1]);
						System.out.print("패배 : " + userGrade[2]);
						System.out.print("무승부 : " + userGrade[3]);
						System.out.print("무승부 : " + userGrade[4]);
						System.out.println("총 흭득 점수 : " + userGrade[5]);
					}
					//캐릭터 성적 조회
					else if(user_select == 2) {
						if(checkChar() == false) { //캐릭터가 있는지 확인
							int[][] charAllGrade = md.getCharAllGrade();
							String[] charName = md.getCharName();
							
							for(int i = 0; i < charAllGrade.length; i++) {
								System.out.print("이름 : " + charName[i]);
								System.out.print(" 투수 정보 >> 스트라이크 : " + charAllGrade[i][0]);
								System.out.print(" 삼진아웃 : " + charAllGrade[i][1]);
								System.out.print(" || 타자 정보 >> 타석 : " + charAllGrade[i][2]);
								System.out.print(" 1루타 : " + charAllGrade[i][3]);
								System.out.print(" 2루타 : " + charAllGrade[i][4]);
								System.out.print(" 3루타 : " + charAllGrade[i][5]);
								System.out.println(" 홈런 : " + charAllGrade[i][6]);
							}
						}
						else {
							System.out.println("캐릭터가 없습니다.");
						}
					}
					//타자 순서 바꾸기
					else if(user_select == 3) {
						//캐릭터가 있다면 순번 교체 시작
						if(checkChar() == false) {
							int[][] charInfo = md.getCharInfo();
							String[] charName = md.getCharName();
							int[] turn = {0,0,0,0,0};
							
						
							for(int i = 0; i < 5; i++) {
								
								for(int j = 0; j < charInfo.length; j++) {
									System.out.print((j+1) + "번 이름 :  " + charName[i]);
									System.out.print(" 타구력 : " + charInfo[j][0] + " ");
									System.out.print(" 투구력 : " + charInfo[j][1] + " ");
									System.out.print(" 강화상태 : " + charInfo[j][2] + " ");
									System.out.print(" 순번 : " + charInfo[j][3] + " ");
									System.out.println();
									}
								
								System.out.print((i+1) + "번 타자 선택 >>");
								user_select = sc.nextInt();
								
								if(user_select < 1 || user_select > 5 || turn[user_select-1] !=0 ) {
									System.out.println("잘못된 번호 입력");
									i--;
								}
								else {
									turn[user_select-1] = user_select;
									charInfo[user_select-1][3] = i+1;
								}
								
							}
							md.setCharInfo(charInfo);
							System.out.println("타순 변경 완료");
						}
						//캐릭터없으면 메인메뉴로
						else {
							System.out.println("캐릭터가 없습니다.");
						}
						
					}
					//메인 메뉴
					else if(user_select == 4) {
						System.out.println("메인메뉴로 이동");
					}
				}
				// 종료
				else if(user_select == 5) {
					System.out.println("게임종료");
					game_page = "게임종료";
				}
				
			}
			
			if(game_page.equals("게임종료")) {
				break;
			}
		}

	}
	public static boolean loginId() {
		return true;
	}
	public static void bringUserInfo() {
	}
	
	public static boolean makeId() {
		return true;
	}
	public static boolean checkChar() {
		return false;
	}
	public static void makeChar(String[] character) {
	}
	public static void deleteChar() {
	}
	public static void enhanceChar(int Char_num) {
	}
	
	
	
	

	public static void viewGameStart() {
		Scanner sc = new Scanner(System.in);
		model md = new model();
		
		int maxRound = 0;
		int pitcherNum = 0;
		int[][] charInfo = md.getCharInfo();
		String[] charName = md.getCharName();
		
		System.out.println("=====게임시작=====");
		
		//게임 라운드수 입력
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
				md.setHitterNum(0);
				md.setOdState(0);
			}
		}
		while(maxRound >= 10);
		
		//캐릭터 정보 출력
		for(int i = 0; i < charInfo.length; i++) {
			System.out.print((i+1) + "번 이름 :  " + charName[i]);
			System.out.print(" 타구력 : " + charInfo[i][0] + " ");
			System.out.print(" 투구력 : " + charInfo[i][1] + " ");
			System.out.print(" 강화상태 : " + charInfo[i][2] + " ");
			System.out.print(" 순번 : " + charInfo[i][3] + " ");
			System.out.println();
			}
		
		//투수 선택
		while(true) {
			//////////////////////////////////////////////////////////여기서부터 진행
			System.out.print("이번 게임 투수를 선택해주세요");
			pitcherNum = sc.nextInt();
			if(pitcherNum < 1 && pitcherNum > 5) {
				System.out.println("잘못된 번호 입력");
			}
			else {
				//투수 번호 초기값 설정
				md.setPitcherNum(pitcherNum);
			}
			
		}
		
		
		
	}
	
	public static void viewMakeChar() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=====캐릭터 생성=====");
		
			String[] character = new String[5];
			
			//캐릭터 생성
			for(int i = 0; i < 5; i++) {
				System.out.print((i+1) + "번째 캐릭터의 이름입력 :");
				System.out.print("이름 : ");
				character[i] = sc.next();					
			}
			
			//생성한 캐릭터 이름 중복확인
			if(checkName(character)) {
				makeChar(character); //컨트롤러 부르기
				System.out.println("캐릭터 생성 성공!");
			}
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
	
	public static void viewenhaceChar() {
		
		Scanner sc = new Scanner(System.in);
		model md = new model();
		
		int[][] charInfo = md.getCharInfo();
		String[] charName = md.getCharName();
		int enhanceNum = 0;
		int user_select = 0;
		
		//캐릭터 정보 출력
		while(true) {
			for(int i = 0; i < charInfo.length; i++) {
			System.out.print((i+1) + "번 이름 :  " + charName[i]);
			System.out.print(" 타구력 : " + charInfo[i][0] + " ");
			System.out.print(" 투구력 : " + charInfo[i][1] + " ");
			System.out.print(" 강화상태 : " + charInfo[i][2] + " ");
			System.out.print(" 순번 : " + charInfo[i][3] + " ");
			System.out.println();
			}
			
			System.out.print("강화할 캐릭터 번호 입력 >> ");
			enhanceNum = sc.nextInt();
			
			System.out.println(enhanceNum + "번 이름 : " + charName[enhanceNum - 1] + "\t강화 상태 : " + charInfo[enhanceNum -1][2]);
			System.out.println("강화[1] 취소[2]");
			user_select = sc.nextInt();
			
			if(user_select == 1) {
				enhanceChar(enhanceNum);
				System.out.println(enhanceNum + "번 이름 : " + charName[enhanceNum - 1] + "\t강화 상태 : " + charInfo[enhanceNum -1][2]);
				System.out.print("강화하기[1] 메인메뉴로[2]");
				user_select = sc.nextInt();
				if(user_select != 1) {
					break;
				}
			}
			else {
				System.out.println("취소 되었습니다");
				break;
			}
		}
		
	}
	
	
	

}
