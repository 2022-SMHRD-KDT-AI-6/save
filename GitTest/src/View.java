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
						model md2 = con.bringCharInfo(md);
						String[] charName = md2.getCharName();
						md.setCharName(charName);
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
					viewGameStart(md);
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
					if(con.checkChar(md) == true) {
						model md2 = con.bringCharInfo(md);
						String[] charName = md2.getCharName();
							
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
		String gameState = "OFFEN";
		String[] charName = md.getCharName();
		int actNum = 0;
		
		System.out.println("=====���ӽ���=====");
		
		//����� �Է�
		do{
			System.out.print("���� ��(�ִ� 10) : ");
			maxRound = sc.nextInt();
			System.out.println("\n\n\n");
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
		
		String result = "";
		while(true) {
			
			switch(gameState) {
				
				case("OFFEN"):
					result = "";
					System.out.println("");
					System.out.println("============ ���� ���� : " + md.getMaxRound() + "-" + md.getNowRound() + " ===========");
					System.out.println("[����]		�� ����Ʈ :" + md.getTeamPoint() + " ���� ����Ʈ" + md.getEnemyPoint());
					System.out.println("STRIKE	: " + md.getStrikeCount() + "\nOUT	: " + md.getOutCount());
					System.out.print(md.getHitterNum() + " �� �̸� : " + charName[md.getHitterNum()-1]);
					System.out.print(" ��Ʈ[1] ����[2] ������[3] >> ");
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
					System.out.println("============ ���� ���� : " + md.getMaxRound() + "-" + md.getNowRound() + " ===========");
					System.out.println("[����]		�� ����Ʈ :" + md.getTeamPoint() + " ���� ����Ʈ" + md.getEnemyPoint());
					System.out.println("STRIKE	: " + md.getStrikeCount() + "\nOUT	: " + md.getOutCount());
					System.out.print(md.getHitterNum() + "�� �̸� :  " + charName[md.getHitterNum()-1]);
					System.out.print(" ��ȭ��[1] �����̴�[2] ����[3] >> ");
					actNum = sc.nextInt();
				
					if(con.isHitBall(md)) {
						gameState = "HITBALL";
					}
					else {
						gameState = "STRIKE";
					}
					break;
				
				case("STRIKE"):
					System.out.println("\n\n���� ���ƽ��ϴ�.");
					System.out.println((md.getStrikeCount()+1) + "STRIKE!");
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
					
					System.out.println("\n\n���� �ƽ��ϴ�.");
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
							System.out.println("\n\n���� ���ƽ��ϴ�");
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
					break;
					
				case("CHANGE"):
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
	
	


}
