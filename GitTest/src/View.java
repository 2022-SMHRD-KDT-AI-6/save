import java.util.Scanner;

public class View {

	public static void main(String[] args) {
		//ȭ��
//		���Էα��� -> �α���, ȸ������
		
//		���θ޴� -> ���� / �������� / ��ȭ / ����Ȯ�κ��� / ��������
//		�������� -> ���� / ����
//		����Ȯ�κ��� -> �������� / ĳ�������� / Ÿ�� 
		
		Scanner sc = new Scanner(System.in);
		Controller con = new Controller();
		model md = new model();
		
		String game_page = "���Էα���";
		int user_select = -1;
		String id = "";
		String pw = "";
		
		while(true) {
			
			switch(game_page) {
			
			case("���Էα���"):
				System.out.println("=====�߱�����=====");
				System.out.print("�α���[1] ȸ������[2] >> ");
				user_select = sc.nextInt();
				
				if(user_select == 1) { 
					//�α���
					System.out.println("=====�α���=====");
					System.out.println("ID�� PW�� �Է����ּ���");
					System.out.print("ID >> ");
					id = sc.next();
					md.setUser_id(id);
					System.out.print("PW >> ");
					pw = sc.next();
					md.setUser_pw(pw); // �̰� id��� �����...
					
					if(con.loginId(md)) {
						System.out.println("�α��� ����!");
						//con.bringUserInfo(md);
						game_page = "���θ޴�";
					}
					else {
					System.out.println("�α��� ����");
					}
				}
				// ȸ������
				else if(user_select == 2) {
					System.out.println("=====ȸ������=====");
					System.out.println("������ ID�� PW�� �Է����ּ���");
					System.out.print("ID >> ");
					id = sc.next();
					md.setUser_id(id);
					System.out.print("PW >> ");
					pw = sc.next();
					md.setUser_pw(pw);
					
					if(con.makeId(md)) { //���̵� ���� �õ�
						System.out.println("���̵� ���� ����!");
					}
					else {
						System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
					}
				}
				
				else {
					System.out.println("���ڸ� �߸� �Է��ϼ̽��ϴ�.");
				}
				
				break;
				
//----------------------------------------------------------------------------------------
				//���θ޴�
			case("���θ޴�"):
				System.out.println("=====���θ޴�=====");
				System.out.println("���ӽ���[1] ĳ���� ���� �Ǵ� ����[2] ĳ���� Ȯ��[3] ����[4]");
				user_select = sc.nextInt();
//----------------------------------------------------------------------------------------				
				//���ӽ���
				if(user_select == 1) { 
					//viewGameStart(md);
					System.out.println("�̱���");
				}
//----------------------------------------------------------------------------------------				
				// ĳ���� ���� �Ǵ� ����
				else if(user_select == 2) { 
					System.out.println("=====ĳ���� ���� �Ǵ� ����=====");
					System.out.println("ĳ���� ����[1] ĳ���� ����[2]");
					user_select = sc.nextInt();
					
					if(user_select == 1) { // ĳ���� ����
						if(con.checkChar(md) == false) { //DB�� ĳ���Ͱ� ���ٸ�
							viewMakeChar(md); //ĳ���� ��������
						}
						else { //DB�� ĳ���Ͱ� ����
							System.out.println("ĳ���͸� �����Ҽ������ϴ�.");
						}
						
					}
					else if(user_select == 2) { //ĳ���� ����
						if(con.checkChar(md)) {
							System.out.println("ĳ���� ���� �Ϸ�");
							con.deleteChar(md); //ĳ���� ����
						}
						else { // ������ ĳ���Ͱ� ����
							System.out.println("������ ĳ���Ͱ� �����ϴ�.");
						}
					}
					else { // �߸��� ��ȣ �Է�
						System.out.println("�߸��� ��ȣ �Է�");
					}	
				}
//----------------------------------------------------------------------------------------
					
				// ĳ���� Ȯ��
				else if(user_select == 3) {
					System.out.println("=====ĳ���� Ȯ��=====");
					
					//ĳ���Ͱ� �ִٸ� 
					if(con.checkChar(md) == false) {
						String[] charName = md.getCharName();
							
							for(int i = 0; i < charName.length; i++) {
								System.out.println((i+1) + "�� �̸� : " + charName[i]);
							}
						}
					else {
						System.out.println("ĳ���Ͱ� �����ϴ�.");
					}		
				}
//----------------------------------------------------------------------------------------				
				// ����
				else if(user_select == 4) {
					System.out.println("��������");
					game_page = "��������";
				}
			}
//----------------------------------------------------------------------------------------
			//��������� while Ż��
			if(game_page.equals("��������")) {
				break;
			}
		}

		
	}
	

	public static void viewGameStart(model md) {
		//1. ġ�°�, �����°�, ��Ʈ����ũ, �ƴ�, �ƿ�, ��������, ��������
		Scanner sc = new Scanner(System.in);
		Controller con = new Controller();
		
		int maxRound = 0;
		int nowRound = 1;
		String gameState = "����";
		String[] charName = md.getCharName();
		int actNum = 0;
		
		System.out.println("=====���ӽ���=====");
		
		//����� �Է�
		do{
			System.out.print("���� ��(�ִ� 10) : ");
			maxRound = sc.nextInt();
			if(maxRound > 10) {
				System.out.println("10���� �۰� �Է����ּ���.");
			}
			else {
				//���ӽ����� �ʱⰪ ���� �����, �������, Ÿ�ڹ�ȣ, ������Ȳ
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
					System.out.println("=====����=====");
					System.out.println("�� ����Ʈ :" + md.getTeamPoint() + "���� ����Ʈ" + md.getEnemyPoint());
					System.out.print(md.getHitterNum() + "�� �̸� :  " + charName[md.getHitterNum()-1]);
					System.out.print("��Ʈ[1] ����[2] ������[3] >> ");
					actNum = sc.nextInt();
					
					if(con.isHitBall(md)) {
						gameState = "HITBALL";
					}
					
					break;
					
				case("DEFEN"):
					System.out.println("=====����=====");
					System.out.println("�� ����Ʈ :" + md.getTeamPoint() + "���� ����Ʈ" + md.getEnemyPoint());
					System.out.print(md.getHitterNum() + "�� �̸� :  " + charName[md.getHitterNum()-1]);
					System.out.print("��ȭ��[1] �����̴�[2] ����[3] >> ");
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
					System.out.println("���� �ƽ��ϴ�.");
					String result = con.hitBall(md);
					System.out.println("��� : " + result);
					con.playUpdate(md, gameState);
					break;
					
				case("OUT"):
					System.out.println("�ƿ�");
					int outCount = md.getOutCount();
					outCount++;
					break;
					
				case("CHANGE"):
					System.out.println("���� ����");
					int odState = md.getOdState();
					md.setStrikeCount(0);
					md.setOutCount(0);
					
					//���� - > ����
					if(odState == 0) {
						odState = 1;
					}
					//���� - > ����
					else {
						nowRound++;
						md.setNowRound(nowRound);
						odState = 0;
					}
					md.setOdState(odState);
					
					break;
			}
			
			//�ƿ�ī��Ʈ ����
			if(md.getStrikeCount() > 2) {
				int outCount = md.getOutCount();
				md.setStrikeCount(0);
				outCount++;
				md.setOutCount(outCount);
			}
			//�ƿ� 3ȸ ��������
			if(md.getOutCount() > 2) {
				gameState = "CHANGE";
			}
			
			if(md.getNowRound() == md.getMaxRound()) {
				System.out.println("��������");
				System.out.println("���� ���ھ� �� :" + md.getTeamPoint() + "���� : " + md.getEnemyPoint());
				break;
			}
		}
		
		
		
	}
//	public static void viewGameStart2(model md) {
//		Scanner sc = new Scanner(System.in);
//		
//		String[] charName = md.getCharName();
//		
//		System.out.println("=====���ӽ���=====");
//		
//		//���� ����� �Է�
//		do{
//			System.out.print("���� ��(�ִ� 10) : ");
//			maxRound = sc.nextInt();
//			if(maxRound > 10) {
//				System.out.println("10���� �۰� �Է����ּ���.");
//			}
//			else {
//				//���ӽ����� �ʱⰪ ���� �����, �������, Ÿ�ڹ�ȣ, ������Ȳ
//				md.setMaxRound(maxRound);
//				md.setNowRound(1);
//				md.setHitterNum(0);
//				md.setOdState(0);
//			}
//		}while(maxRound >= 10);
//		
//		int nowRound = 1;
//		
//		//���� ����
//		while(true) {
//			//////////////////////////////////////////////////////////���⼭���� ����
//			int outCnt = 0;
//			//���ݻ�Ȳ
//			if(md.getOdState() == 0) {
//				int hitterNum = sc.nextInt();
//				System.out.println("���ݻ�Ȳ");
//				System.out.println("������ ���� : " + md.getTeamPoint() + "���� ���� : " + md.getEnemyPoint());
//				System.out.print(hitterNum + "�� �̸� :  " + charName[hitterNum-1]);
//				System.out.print(" Ÿ���� : " + charInfo[hitterNum-1][0] + " ");
//				System.out.print(" ��ȭ���� : " + charInfo[hitterNum-1][2] + " ");
//				System.out.print(" ���� : " + charInfo[hitterNum-1][3] + " ");
//				System.out.println();
//					
//				System.out.println("Ÿ���� �ൿ ����>> ��Ʈ[1] ����[2] ������[3]");
//				md.setActNum(hitterNum);
//				
//				//���� ������
//				if(isHitBall()) {
//					System.out.println("���� �ƽ��ϴ�!");
//					String Ballstate = hitBall();
//					int[] scoreSheet = md.getScoreSheet();
//					int teamPoint = md.getTeamPoint();
//					md.playUpdate(Ballstate);
//					System.out.println("���� ����");
//					hitterNum++;
//				}
//				//���� ��������
//				else {
//					//��Ʈ����ũ ����
//					System.out.println("��Ʈ����ũ");
//					int strikeCnt = md.getStrikeCount();
//					strikeCnt++;
//					if(strikeCnt >= 3) {
//						//�����ƿ�
//						System.out.println("�����ƿ�");
//						md.setStrikeCount(0);
//						outCnt++;
//						System.out.println("���� ����");
//						hitterNum++;
//					}
//					
//					
//					
//				}
//			}
//			
//			//�����Ȳ
//			else {
//				System.out.print("������ �������ּ��� : ");
//				pitcherNum = sc.nextInt();
//				md.setPitcherNum(pitcherNum);
//				
//				System.out.println("����");
//				System.out.println("������ ���� : " + md.getTeamPoint() + "���� ���� : " + md.getEnemyPoint());
//				System.out.print(pitcherNum + "�� �̸� :  " + charName[pitcherNum-1]);
//				System.out.print(" ������ : " + charInfo[pitcherNum-1][1] + " ");
//				System.out.print(" ��ȭ���� : " + charInfo[pitcherNum-1][2] + " ");
//				System.out.println();
//				
//				
//				
//			}
//			
//			//�ƿ� 3ȸ ��������
//			if(outCnt >= 3) {
//				System.out.println("��������");
//				int odState = md.getOdState();
//				//���� -> ����
//				if(odState == 0) {
//					System.out.print("�̹� ���� ������ �������ּ���");
//					pitcherNum = sc.nextInt();
//					if(pitcherNum < 1 && pitcherNum > 5) {
//						System.out.println("�߸��� ��ȣ �Է�");
//					}
//					else {
//						//���� ��ȣ �ʱⰪ ����
//						md.setPitcherNum(pitcherNum);
//					}
//					md.setOdState(1);
//					nowRound++;
//				}
//				//���� -> ����
//				else {
//					nowRound++;
//					md.setNowRound(nowRound);
//					md.setOdState(0);;
//				}
//			}
//			
//			if(nowRound == maxRound) {
//			System.out.println(maxRound + "�� ��������");
//			break;
//			}
//			
//		}
		
		
	
	public static void viewMakeChar(model md) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=====ĳ���� ����=====");
		
			String[] character = new String[5];
			
			//ĳ���� ����
			for(int i = 0; i < 5; i++) {
				System.out.print((i+1) + "��° ĳ������ �̸��Է� :");
				System.out.print("�̸� : ");
				character[i] = sc.next();					
			}
			
			//������ ĳ���� �̸� �ߺ�����
			if(checkName(character)) {
				md.setCharName(character);
				System.out.println("ĳ���� ���� ����!");
			}
			//������ ĳ���� �ߺ� ����
			else {
				System.out.println("ĳ���� ��������");
				System.out.println("�̸��� �ߺ��Ͽ� �ۼ��߽��ϴ�.");
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
