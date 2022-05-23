
import java.util.Scanner;

public class View {

	public static void main(String[] args) {
		//화면
//		가입로그인 -> 로그인, 회원가입
		
//		메인메뉴 -> 게임 / 생성삭제 / 강화 / 정보확인변경 / 게임종료
//		생성삭제 -> 생성 / 삭제
//		정보확인변경 -> 유저정보 / 캐릭터정보 / 타순 
		
		Scanner sc = new Scanner(System.in);
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
					System.out.print("ID와 PW를 입력해주세요");
					System.out.print("ID >> ");
					id = sc.next();
					System.out.println("PW >> ");
					pw = sc.next();
					
					if(loginId()) {
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
					System.out.print("PW >> ");
					pw = sc.next();
					
					if(makeId()) { //아이디 생성 시도
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
						if(checkChar() == false) { //DB에 캐릭터가 있다면
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
					System.out.println("성적확인[1] 캐릭터 성적확인[2] 타자순서바꾸기[3] 메인메뉴[4]");
					user_select = sc.nextInt();
					
					if(user_select == 1) {
						System.out.println("=====성적확인=====");
						
					}
					else if(user_select == 2) {
						
					}
					else if(user_select == 3) {
						
					}	
					else if(user_select == 4) {
						System.out.println("메인메뉴로 이동");
					}
				}
				// 종료
				else if(user_select == 5) {
	
				}
				
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
		return true;
	}
	public static void makeChar(String[] character) {
	}
	public static void deleteChar() {
	}
	public static void enhanceChar(int Char_num) {
	}
	
	
	
	

	public static void viewGameStart() {
		
	}
	
	public static void viewMakeChar() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=====캐릭터 생성=====");
		
			String[] character = new String[5];
			
			//캐릭터 생성
			for(int i = 0; i < 5; i++) {
				System.out.println((i+1) + "번째 캐릭터의 이름과 타석, 순번입력");
				System.out.print("이름 : ");
				character[i] = sc.next();					
			}
			
			//생성한 캐릭터 이름 중복확인
			if(checkName(character)) {
				makeChar(character); //컨트롤러 부르기
				System.out.println("캐릭터 생성 성공!");
			}
			else {
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
			System.out.println((i+1) + "번 이름 :" + charName[i]);
			System.out.print(" 타구력 : " + charInfo[i][0] + " ");
			System.out.print(" 투구력 : " + charInfo[i][1] + " ");
			System.out.print(" 강화상태 : " + charInfo[i][2] + " ");
			System.out.print(" 순번 : " + charInfo[i][3] + " ");
			System.out.println();
			}
			
			System.out.print("강화할 캐릭터 번호 입력 >> ");
			enhanceNum = sc.nextInt();
			
			System.out.println(charName[enhanceNum - 1] + "현재 선택한 캐릭터의 강화 상태는" + charInfo[enhanceNum -1][2]);
			System.out.println("강화[1] 취소[2]");
			user_select = sc.nextInt();
			
			if(user_select == 1) {
				enhanceChar(enhanceNum);
				System.out.println(charName[enhanceNum - 1] + "현재 선택한 캐릭터의 강화 상태는" + charInfo[enhanceNum -1][2]);
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
