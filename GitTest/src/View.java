import java.util.Scanner;
import java.util.Random;

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
		clearScreen();
		
		while(true) {
			
			switch(game_page) {
			
			case("���Էα���"):
				System.out.println("=====�߱�����=====");
				System.out.print("�α���[1] ȸ������[2] >> ");
				user_select = sc.nextInt();
				
				if(user_select == 1) { 
					//�α���
					clearScreen();
					System.out.println("=====�α���=====");
					System.out.println("ID�� PW�� �Է����ּ���");
					System.out.print("ID >> ");
					id = sc.next();
					md.setUser_id(id);
					System.out.print("PW >> ");
					pw = sc.next();
					md.setUser_pw(pw); // �̰� id��� �����...
					
					if(con.loginId(md)) {
						clearScreen();
						System.out.println("�α��� ����!");
						model md2 = con.bringCharInfo(md);
						String[] charName = md2.getCharName();
						md.setCharName(charName);
						game_page = "���θ޴�";
					}
					else {
					clearScreen();
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
						clearScreen();
						System.out.println("���̵� ���� ����!");
					}
					else {
						clearScreen();
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
				System.out.print("���� >> ");
				user_select = sc.nextInt();
//----------------------------------------------------------------------------------------				
				//���ӽ���
				if(user_select == 1) { 
					clearScreen();
					viewGameStart(md);
				}
//----------------------------------------------------------------------------------------				
				// ĳ���� ���� �Ǵ� ����
				else if(user_select == 2) { 
					clearScreen();
					System.out.println("=====ĳ���� ���� �Ǵ� ����=====");
					System.out.println("ĳ���� ����[1] ĳ���� ����[2]");
					user_select = sc.nextInt();
					
					if(user_select == 1) { // ĳ���� ����
						if(con.checkChar(md) == false) { //DB�� ĳ���Ͱ� ���ٸ�
							viewMakeChar(md); //ĳ���� ��������
						}
						else { //DB�� ĳ���Ͱ� ����
							clearScreen();
							System.out.println("ĳ���͸� �����Ҽ������ϴ�.");
						}
						
					}
					else if(user_select == 2) { //ĳ���� ����
						if(con.checkChar(md)) {
							clearScreen();
							System.out.println("ĳ���� ���� �Ϸ�");
							con.deleteChar(md); //ĳ���� ����
						}
						else { // ������ ĳ���Ͱ� ����
							clearScreen();
							System.out.println("������ ĳ���Ͱ� �����ϴ�.");
						}
					}
					else { // �߸��� ��ȣ �Է�
						clearScreen();
						System.out.println("�߸��� ��ȣ �Է�");
					}	
				}
//----------------------------------------------------------------------------------------
					
				// ĳ���� Ȯ��
				else if(user_select == 3) {
					clearScreen();
					System.out.println("=====ĳ���� Ȯ��=====");
					
					//ĳ���Ͱ� �ִٸ� 
					if(con.checkChar(md) == true) {
						model md2 = con.bringCharInfo(md);
						String[] charName = md2.getCharName();
							
							for(int i = 0; i < charName.length; i++) {
								System.out.println((i+1) + "�� �̸� : " + charName[i]);
							}
					}
					else {
						clearScreen();
						System.out.println("ĳ���Ͱ� �����ϴ�.");
					}		
				}
//----------------------------------------------------------------------------------------				
				// ����
				else if(user_select == 4) {
					clearScreen();
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
		Controller con = new Controller();
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		
		if(con.checkChar(md) == false) {
			clearScreen();
			System.out.println("ĳ���Ͱ� �����ϴ�.");
			return;
		}
		
		int maxRound = 0;
		int nowRound = 1;
		int pitcherNum = 0;
		String gameState = "OFFEN";
		String[] charName = md.getCharName();
		int actNum = 0;
		int comNum = 0;
		
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
		
		for(int i = 0; i < charName.length; i++) {
			System.out.print(charName[i] + "[" + (i+1) + "] ");
		}
		
		System.out.print("�̹����� ������ �����ּ��� >>");
		pitcherNum = sc.nextInt();
		clearScreen();
		
		String result = "";
		while(true) {
			
			switch(gameState) {
				
				case("OFFEN"):
					result = "";
					System.out.println("");
					System.out.println("============ ���� ���� : " + md.getMaxRound() + "-" + md.getNowRound() + " ===========");
					System.out.println("[����]		�� ����Ʈ :" + md.getTeamPoint() + " ��ǻ�� ����Ʈ : " + md.getEnemyPoint());
					System.out.println("STRIKE	: " + md.getStrikeCount() + "\nOUT	: " + md.getOutCount());
					System.out.print(md.getHitterNum() + " �� Ÿ�� " + charName[md.getHitterNum()-1]);
					System.out.print(" ��Ʈ[1] ����[2] ������[3] >> ");
					actNum = sc.nextInt();
					comNum = rd.nextInt(3)+1;
					
					clearScreen();
					if(comNum == 1) {
						System.out.println("��ǻ�Ͱ� ��ȭ���� ������");
					}
					else if(comNum == 2) {
						System.out.println("��ǻ�Ͱ� �����̴��� ������");
					}
					else {
						System.out.println("��ǻ�Ͱ� ������ ������");
					}
					
					if(actNum == 1) {
						System.out.println(charName[md.getHitterNum()-1] + "��(��) ��Ʈ");
					}
					else if(actNum == 2) {
						System.out.println(charName[md.getHitterNum()-1] + "��(��) ����");
					}
					else {
						System.out.println(charName[md.getHitterNum()-1] + "��(��) ������");
					}
					
					int odState = md.getOdState();
					
					if(con.isHitBall(md, actNum, comNum)) {
						gameState = "HITBALL";
					}
					else {
						gameState = "STRIKE";
					}
					
					break;
					
				case("DEFEN"):
					result = "";
					System.out.println("");
					System.out.println("============ ���� ���� : " + md.getMaxRound() + "-" + md.getNowRound() + " ===========");
					System.out.println("[����]		�� ����Ʈ :" + md.getTeamPoint() + " ��ǻ�� ����Ʈ : " + md.getEnemyPoint());
					System.out.println("STRIKE	: " + md.getStrikeCount() + "\nOUT	: " + md.getOutCount());
					System.out.println("�̸� :  " + charName[pitcherNum-1]);
					System.out.print(" ��ȭ��[1] �����̴�[2] ����[3] >> ");
					actNum = sc.nextInt();
					
					clearScreen();
					if(actNum == 1) {
						System.out.println(charName[md.getHitterNum()-1] + "��(��) ��ȭ���� ������");
					}
					else if(actNum == 2) {
						System.out.println(charName[md.getHitterNum()-1] + "��(��) �����̴��� ������");
					}
					else {
						System.out.println(charName[md.getHitterNum()-1] + "��(��) ������ ������");
					}
					
					if(comNum == 1) {
						System.out.println("��ǻ�Ͱ� ��Ʈ");
					}
					else if(comNum == 2) {
						System.out.println("��ǻ�Ͱ� ����");
					}
					else {
						System.out.println("��ǻ�Ͱ� ������");
					}
				
					if(con.isHitBall(md, actNum, comNum)) {
						gameState = "HITBALL";
					}
					else {
						gameState = "STRIKE";
					}
					break;
				
				case("STRIKE"):
					if(md.getOdState() == 0) {
						System.out.print(charName[md.getHitterNum()-1] + "��(��) ");
					}
					else {
						System.out.print("��ǻ�Ͱ� ");
					}
					
					System.out.println("���� ���ƽ��ϴ�.");
					System.out.println(" " + (md.getStrikeCount()+1) + " STRIKE!");
					int strike = md.getStrikeCount();
					strike++;
					md.setStrikeCount(strike);

					
					odState = md.getOdState();
					//��������
					if(odState == 0) {
						gameState = "OFFEN";
					}
					//��������
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
					
					
					//���� ��Ȳ
					if(md.getOdState() == 0) {
						result = con.hitBall(md,actNum);
					}
					//�����Ȳ
					else {
						comNum = rd.nextInt(3)+1;
						result = con.hitBall(md,comNum);
					}
					

					if(odState==0) {
						System.out.println(charName[md.getHitterNum()-1] + "��(��) ���� �ƽ��ϴ�.");
						System.out.println(result + "!");
						int hitterNum = md.getHitterNum();
						hitterNum++;
						if(hitterNum > 5) {
							hitterNum = 1;
						}
						md.setHitterNum(hitterNum);
						switch(result) { 
						
						case "1��Ÿ": 
							teamPoint += 1;
							md.setTeamPoint(teamPoint);
							md.setStrikeCount(0);
							break;
							
						case "2��Ÿ": 
							teamPoint += 3;
							md.setTeamPoint(teamPoint);
							md.setStrikeCount(0);
							break;
							
						case "3��Ÿ": 
							teamPoint += 5;
							md.setTeamPoint(teamPoint);
							md.setStrikeCount(0);
							break;
							
						case "Ȩ��": 
							teamPoint += 10;
							md.setTeamPoint(teamPoint);
							md.setStrikeCount(0);
							break;
							
						case "�Ŀ�":
							strikeCount += 1;
							md.setStrikeCount(strikeCount);
							hitterNum--;
							
							break;
							
						case "�ƿ�":
							outCount += 1;
							md.setOutCount(outCount);
							md.setStrikeCount(0);
							break;
						}
					}
					else {
						System.out.println("��ǻ�Ͱ� ���� �ƽ��ϴ�.");
						System.out.println(result + "!");
						switch(result) {
						
						case "�ƿ�":
							outCount += 1;
							md.setOutCount(outCount);
							md.setStrikeCount(0);
							
							break;
						case "1��Ÿ":
							enemyPoint += 1;
							md.setEnemyPoint(enemyPoint);
							md.setStrikeCount(0);
							break;
						case "2��Ÿ":
							enemyPoint += 3;
							md.setEnemyPoint(enemyPoint);
							md.setStrikeCount(0);
							break;
						case "3��Ÿ":
							enemyPoint += 5;
							md.setEnemyPoint(enemyPoint);
							md.setStrikeCount(0);
							break;
						case "Ȩ��":
							enemyPoint += 10;
							md.setEnemyPoint(enemyPoint);
							md.setStrikeCount(0);
							break;
						case "�Ŀ�":
							strikeCount += 1;
							md.setStrikeCount(strikeCount);
							break;
						
						}
					}
					//��������
					if(odState == 0) {
						gameState = "OFFEN";
					}
					//��������
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
					
					int hitterNum = md.getHitterNum();
					hitterNum++;
					if(hitterNum > 5) {
						hitterNum = 1;
					}
					md.setHitterNum(hitterNum);
					
					break;
					
				case("CHANGE"):
					clearScreen();
					if(md.getMaxRound() != md.getNowRound()) {
						System.out.println("\n\n���� ����");
					}
					odState = md.getOdState();
					md.setStrikeCount(0);
					md.setOutCount(0);
					
					//���� - > ����
					if(odState == 0) {
						odState = 1;
						md.setOdState(odState);
						gameState = "DEFEN";
					}
					//���� - > ����
					
					else {
						nowRound++;
						md.setNowRound(nowRound);
						odState = 0;
						md.setOdState(odState);
						gameState = "OFFEN";
					}
					
			}
			
			//�ƿ�ī��Ʈ ����
			if(md.getStrikeCount() > 2 && result.equals("FOUL") != true ) {
				System.out.println((md.getOutCount()+1) + " OUT!");
				int outCount = md.getOutCount();
				md.setStrikeCount(0);
				outCount++;
				md.setOutCount(outCount);
				
				int hitterNum = md.getHitterNum();
				hitterNum++;
				if(hitterNum > 5) {
					hitterNum = 1;
				}
				md.setHitterNum(hitterNum);
			}
			//�ƿ� 3ȸ ��������
			if(md.getOutCount() > 2) {
				gameState = "CHANGE";
			}
			
			
			
			if(md.getNowRound() > md.getMaxRound()) {
				//System.out.println("������� : " + md.getNowRound() + "������ ���� : " + md.getMaxRound());
				System.out.println("\n\n��������");
				System.out.println("���� ���ھ� �� :" + md.getTeamPoint() + " ���� : " + md.getEnemyPoint());
				break;
			}
		}
		
		
		
	}

		

	public static void viewMakeChar(model md) {
		
		Scanner sc = new Scanner(System.in);
		Controller con = new Controller();
		
		System.out.println("=====ĳ���� ����=====");
		
			String[] character = new String[5];
			//ĳ���� ����
			for(int i = 0; i < 5; i++) {
				System.out.print((i+1) + "��° ĳ������ �̸��Է� :");
				System.out.print("�̸� : ");
				character[i] = sc.next();
				md.setCharName(character);
			}
			
			//������ ĳ���� �̸� �ߺ�����
			if(checkName(character)) {
				md.setCharName(character);
				clearScreen();
				System.out.println("ĳ���� ���� ����!");
				con.makeChar(md);
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
	public static void clearScreen() {
		  for (int i = 0; i < 80; i++)
		   System.out.println("");
		}
	


}
