import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ReceiptMain2 {

	public static ArrayList<Integer> saveNo = new ArrayList<Integer>();
	public static ArrayList<Integer> saveCount = new ArrayList<Integer>();
	public static ArrayList<String> saveName = new ArrayList<String>();
	public static ArrayList<Integer> savePrice = new ArrayList<Integer>();
	public static ArrayList<Integer> saveTotal = new ArrayList<Integer>();
	public static ArrayList<Integer> saveSumTotal = new ArrayList<Integer>();

	public static void main(String[] args) {
		int count = 0;
		int inputNo = 0;
		int type;
		int no = 0;
		String name = null;
		int price = 0;
		int total;
		int sumTotal = 0;
		Scanner sc = new Scanner(System.in);

		while (true) {
			do {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/goodslist", "root", "123456");

					System.out.print("상품 번호를 입력하세요 : ");
					inputNo = sc.nextInt();

					Statement stmt = conn.createStatement();
					ResultSet rset = stmt.executeQuery("select * from goods where no=" + inputNo);

					if (rset.next()) {
						System.out.printf("%d %s %d원 %s\n", rset.getInt(1), rset.getString(2), rset.getInt(3),
								rset.getString(4));
						no = rset.getInt(1);
						name = rset.getString(2);
						price = rset.getInt(3);
						total = rset.getInt(3) * count;
						sumTotal += total;
					} else {
						System.out.println("해당하는 상품이 없습니다.");
						break;
					}
					rset.close();
					stmt.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.print("구매 갯수 : ");
				count = sc.nextInt();
				saveNo.add(no);
				saveName.add(name);
				savePrice.add(price);
				total = price * count;
				saveTotal.add(total);
				sumTotal += total;
				saveSumTotal.add(sumTotal);
				System.out.print("1. 추가구매, 2. 구매종료 : ");
				type = sc.nextInt();
				if (type == 2) {
					System.out.println();
					System.out.println("           <<CAFE GREENY>>          ");
					System.out.println("\n" + "Hawsung Dongbudaero 01-12");
					System.out.printf("Owner: David Kim\n");
					System.out.println("-------------------------------------");
					System.out.println("Bill No.: 001-145138");
					System.out.println("Date: 21-06-21");
					System.out.println("Casher: Hanna");
					System.out.println("-------------------------------------");
					System.out.printf("%30s", "Order Number A-506");
					System.out.println("\n-------------------------------------");
					System.out.println("=============== Order ===============");
					System.out.printf("%s %10s %10s %7s %5s\n", "no", "name", "price", "count", "total");
				}
			} while (type != 2);

			for (int index = 0; index < saveName.size(); index++) {
				System.out.printf("%d %10s %5d %5d %5d\n", saveNo.get(index), saveName.get(index), savePrice.get(index),
						saveCount.get(index), saveTotal.get(index));
			}
			System.out.println("-------------------------------------");
			System.out.println("Please visit us again soon \nFor more information visit");
			System.out.println("www.cafegreeny.com\nWifi password: 13579cg");
			System.out.println("=====================================");
			System.out.printf("Total: %d", sumTotal);
			System.out.println("");
			System.out.printf("Payment: %d", sumTotal);
			System.out.printf("%s", "\n--------- 38 Check Closed ---------");
			System.out.println();
			Date today = new Date();
			System.out.println(today);
			break;

		}

	}
}
