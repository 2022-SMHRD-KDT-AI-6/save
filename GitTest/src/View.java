
import java.util.Scanner;

public class View {

	public static void main(String[] args) {
		//ȭ��
//		���Էα��� -> �α���, ȸ������
		
//		���θ޴� -> ���� / �������� / ��ȭ / ����Ȯ�κ��� / ��������
//		�������� -> ���� / ����
//		����Ȯ�κ��� -> �������� / ĳ�������� / Ÿ�� 
		
		Scanner sc = new Scanner(System.in);
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
					System.out.print("ID�� PW�� �Է����ּ���");
					System.out.print("ID >> ");
					id = sc.next();
					System.out.println("PW >> ");
					pw = sc.next();
					
					if(loginId()) {
						System.out.println("�α��� ����!");
						bringUserInfo();
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
					System.out.print("PW >> ");
					pw = sc.next();
					
					if(makeId()) { //���̵� ���� �õ�
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
				
//-----------------------------------------------------------------------------
				
			case("���θ޴�"):
				System.out.println("=====���θ޴�=====");
				System.out.println("���ӽ���[1] ĳ���� ���� �Ǵ� ����[2] ��ȭ��[3] ����Ȯ��(����)[4] ����[5]");
				user_select = sc.nextInt();
				
				//���ӽ���
				if(user_select == 1) { 
					viewGameStart();
				}
				
				// ĳ���� ���� �Ǵ� ����
				else if(user_select == 2) { 
					System.out.println("=====ĳ���� ���� �Ǵ� ����=====");
					System.out.println("ĳ���� ����[1] ĳ���� ����[2]");
					user_select = sc.nextInt();
					
					if(user_select == 1) { // ĳ���� ����
						if(checkChar() == false) { //DB�� ĳ���Ͱ� �ִٸ�
							viewMakeChar(); //ĳ���� ��������
						}
						else { //DB�� ĳ���Ͱ� ����
							System.out.println("ĳ���͸� �����Ҽ������ϴ�.");
						}
						
					}
					else if(user_select == 2) { //ĳ���� ����
						if(checkChar()) {
							System.out.println("ĳ���� ���� �Ϸ�");
							deleteChar(); //ĳ���� ����
						}
						else { // ������ ĳ���Ͱ� ����
							System.out.println("������ ĳ���Ͱ� �����ϴ�.");
						}
					}
					else { // �߸��� ��ȣ �Է�
						System.out.println("�߸��� ��ȣ �Է�");
					}	
				}

				// ��ȭ��
				else if(user_select == 3) {
					if(checkChar()) {
						viewenhaceChar();				
					}
					else {
						System.out.println("��ȭ�� ĳ���Ͱ� �����ϴ�");
					}
					
					
				}
				// ����Ȯ��(����)
				else if(user_select == 4) {
					System.out.println("����Ȯ��[1] ĳ���� ����Ȯ��[2] Ÿ�ڼ����ٲٱ�[3] ���θ޴�[4]");
					user_select = sc.nextInt();
					
					if(user_select == 1) {
						System.out.println("=====����Ȯ��=====");
						
					}
					else if(user_select == 2) {
						
					}
					else if(user_select == 3) {
						
					}	
					else if(user_select == 4) {
						System.out.println("���θ޴��� �̵�");
					}
				}
				// ����
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
		
		System.out.println("=====ĳ���� ����=====");
		
			String[] character = new String[5];
			
			//ĳ���� ����
			for(int i = 0; i < 5; i++) {
				System.out.println((i+1) + "��° ĳ������ �̸��� Ÿ��, �����Է�");
				System.out.print("�̸� : ");
				character[i] = sc.next();					
			}
			
			//������ ĳ���� �̸� �ߺ�Ȯ��
			if(checkName(character)) {
				makeChar(character); //��Ʈ�ѷ� �θ���
				System.out.println("ĳ���� ���� ����!");
			}
			else {
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
	
	public static void viewenhaceChar() {
		
		Scanner sc = new Scanner(System.in);
		model md = new model();
		
		int[][] charInfo = md.getCharInfo();
		String[] charName = md.getCharName();
		int enhanceNum = 0;
		int user_select = 0;
		
		//ĳ���� ���� ���
		while(true) {
			for(int i = 0; i < charInfo.length; i++) {
			System.out.println((i+1) + "�� �̸� :" + charName[i]);
			System.out.print(" Ÿ���� : " + charInfo[i][0] + " ");
			System.out.print(" ������ : " + charInfo[i][1] + " ");
			System.out.print(" ��ȭ���� : " + charInfo[i][2] + " ");
			System.out.print(" ���� : " + charInfo[i][3] + " ");
			System.out.println();
			}
			
			System.out.print("��ȭ�� ĳ���� ��ȣ �Է� >> ");
			enhanceNum = sc.nextInt();
			
			System.out.println(charName[enhanceNum - 1] + "���� ������ ĳ������ ��ȭ ���´�" + charInfo[enhanceNum -1][2]);
			System.out.println("��ȭ[1] ���[2]");
			user_select = sc.nextInt();
			
			if(user_select == 1) {
				enhanceChar(enhanceNum);
				System.out.println(charName[enhanceNum - 1] + "���� ������ ĳ������ ��ȭ ���´�" + charInfo[enhanceNum -1][2]);
				System.out.print("��ȭ�ϱ�[1] ���θ޴���[2]");
				user_select = sc.nextInt();
				if(user_select != 1) {
					break;
				}
			}
			else {
				System.out.println("��� �Ǿ����ϴ�");
				break;
			}
		}
		
	}
	
	
	

}
