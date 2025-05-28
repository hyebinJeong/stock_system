import org.stock.dao.StockDao;
import org.stock.dao.StockDaoImpl;
import org.stock.domain.StockVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StockMain {
    static StockDao stockDao = new StockDaoImpl();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        while (true) {
            System.out.println("\n원하는 작업을 선택하시오.");
            System.out.println("=====================");
            System.out.println("1. 주식 등록 (insert)");
            System.out.println("2. 주식 목록 조회 (selectList)");
            System.out.println("3. 주식 단일 조회 (selectOne)");
            System.out.println("4. 주식 정보 수정 (update)");
            System.out.println("5. 주식 삭제 (delete)");
            System.out.println("6. 프로그램 종료");
            System.out.println("=====================");
            System.out.print("번호 입력 >> ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> insert();
                case 2 -> selectList();
                case 3 -> selectOne();
                case 4 -> update();
                case 5 -> delete();
                case 6 -> {
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                }
                default -> System.out.println("선택이 올바르지 않습니다.");
            }
        }
    }

    public static void insert() throws SQLException {
        StockVO stock = new StockVO();

        System.out.println("\n종목명, 티커, 현재가, 보유 수량을 순서대로 입력하시오.");
        System.out.print("stock_name >> ");
        stock.setStock_name(sc.next());
        System.out.print("ticker >> ");
        stock.setTicker(sc.next());
        System.out.print("price >> ");
        stock.setPrice(sc.nextInt());
        System.out.print("holding_qty >> ");
        stock.setHolding_qty(sc.nextInt());

        int result = stockDao.create(stock);
        if (result == 1) {
            System.out.println("등록 성공!");
        } else {
            System.out.println("등록 실패...");
        }
    }

    public static void selectList() throws SQLException {
        List<StockVO> list = stockDao.getList();
        for (StockVO stock : list) {
            printStock(stock);
        }
    }

    public static void selectOne() throws SQLException {
        System.out.print("\n조회할 주식의 id를 입력하시오 >> ");
        int id = sc.nextInt();

        StockVO stock = stockDao.get(id);
        if (stock != null) {
            printStock(stock);
        } else {
            System.out.println("해당 ID의 주식이 존재하지 않습니다.");
        }
    }

    public static void update() throws SQLException {
        StockVO stock = new StockVO();

        System.out.println("\n수정할 주식의 정보를 입력하시오.");
        System.out.print("stock_name >> ");
        stock.setStock_name(sc.next());
        System.out.print("ticker >> ");
        stock.setTicker(sc.next());
        System.out.print("price >> ");
        stock.setPrice(sc.nextInt());
        System.out.print("holding_qty >> ");
        stock.setHolding_qty(sc.nextInt());
        System.out.print("수정할 주식 id >> ");
        stock.setId(sc.nextInt());

        int result = stockDao.update(stock);
        if (result == 1) {
            System.out.println("수정 성공!");
        } else {
            System.out.println("수정 실패...");
        }
    }

    public static void delete() throws SQLException {
        System.out.print("\n삭제할 주식의 id를 입력하시오 >> ");
        int id = sc.nextInt();

        int result = stockDao.delete(id);
        if (result == 1) {
            System.out.println("삭제 성공!");
        } else {
            System.out.println("삭제 실패...");
        }
    }

    private static void printStock(StockVO stock) {
        System.out.printf("ID: %d | 이름: %s | 티커: %s | 가격: %d | 수량: %d%n",
                stock.getId(), stock.getStock_name(), stock.getTicker(), stock.getPrice(), stock.getHolding_qty());
    }
}

