
public class model {
	
	//����
	private int maxRound; //������ ���� ����
	private int nowRound; // �������
	private int odState; // ���� ������� ������Ȳ
	private int pitcherNum; // ������� ���� ��ȣ
	private int hitterNum; // ������� ���� ��ȣ
	private int teamPoint; // ���� �� ����
	private int enemyPoint; // ���� ����� ����
	private int strikeCount; //��Ʈ����ũ ����
	private int outCount; //�ƿ� ����
	private int[] scoreSheet = {1, 3, 5, 10}; //1��Ÿ =1��, 2��Ÿ =3��, 3��Ÿ =5��, Ȩ�� =10��
	
	//Ȯ������ 
	private int buntSuccBase = 70; //��Ʈ ����Ȯ��
	private int[] buntChanceBase = {45,0,0,5,50,0}; //��Ʈ ���� �� ȹ������ Ȯ�� (1��Ÿ, 2��Ÿ, 3��Ÿ, �Ŀ�, �ƿ�, Ȩ��)
	private int normalSuccBase = 40; //���� ����Ȯ��
	private int[] normalChanceBase= {30,20,15,10,20,5}; //���� ���� �� ȹ������ Ȯ��(1��Ÿ, 2��Ÿ, 3��Ÿ, �Ŀ�, �ƿ�, Ȩ��)
	private int strongSuccBase = 30; //������ ���� Ȯ��
	private int[] strongChanceBase= {10,28,22,15,25,10}; //������ ���� �� ȹ�� ���� Ȯ��(1��Ÿ, 2��Ÿ, 3��Ÿ, �Ŀ�, �ƿ�, Ȩ��)
	private int breakingBallBase= 10;//��ȭ�� Ÿ���� Ÿ�� ������ �󸶳� �����
	private int[] breakingBallChanceBase= {80,5,15}; //��ȭ�� Ÿ�ڰ� ���� �� �߰� ȹ�� ���� Ȯ��(��ȭ ����, �Ŀ�� ����, �� �ܰ� ���)
	private int sliderBase= 5; //�����̴� Ÿ���� Ÿ�� ������ �󸶳� �����
	private int[] sliderChanceBase= {75,0,25}; //�����̴� Ÿ�ڰ� ���� �� �߰� ȹ�� ���� Ȯ��(��ȭ ����, �Ŀ�� ����, �� �ܰ� ���)
	private int fastBallBase= 20; //���� Ÿ���� Ÿ�� ������ �󸶳� �����
	private int[] fastBallChanceBase={50,10,40}; //���� Ÿ�ڰ� ���� �� �߰� ȹ�� ���� Ȯ��(��ȭ ����, �Ŀ�� ����, �� �ܰ� ���)
	private int enhanceBase = 50;
			

	private String[] charName = new String[5]; //ĳ������ �̸� 5�� �����
	private int[][] charInfo = new int[5][4]; //ĳ������ ����(Ÿ����, ������, ��ȭ����,����)
	private int[] comInfo = {100,100}; //��ǻ���� �⺻ Ÿ����, ������ 
	private int[][] charGameGrade = new int[5][7]; //���� �÷������� ������ ĳ���͵��� ����,(��Ʈ����ũ Ƚ��, �����ƿ� Ƚ��, Ÿ�� �ö�� Ƚ��, 1��Ÿ, 2��Ÿ, 3��Ÿ, Ȩ��),��Ʈ����ũ��, �����ƿ��� ��������, ������ Ÿ�� ����
	private int[][] charAllGrade = new int[5][7]; //����ĳ���͵��� ��ü����(��Ʈ����ũ Ƚ��, �����ƿ� Ƚ��, Ÿ�� �ö�� Ƚ��, 1��Ÿ, 2��Ÿ, 3��Ÿ, Ȩ��),��Ʈ����ũ��, �����ƿ��� ��������, ������ Ÿ�� ����
	private int[] userGrade= new int [5]; //������� ��ü ����
	

	private String user_id;	// ���� ����ڰ� �Է��� id
	private String user_pw; // ���� ����ڰ� �Է��� password
	private String user_money; // ��
}
	