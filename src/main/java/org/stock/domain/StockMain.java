package org.stock.main;

import org.stock.dao.StockDao;
import org.stock.dao.StockDaoImpl;
import org.stock.domain.StockVO;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StockMain {
    // DAO와 Scanner 객체 생성
    static StockDao stockDao = new StockDaoImpl();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        // 반복적으로 메뉴를 출력하여 사용자 입력 처리
        while (true) {
            System.out.println("원하는 작업을 선택하시오.");
            System.out.println("=====================");
            System.out.println("1. 주식 등록");
            System.out.println("2. 주식 전체 조회");
            System.out.println("3. 주식 단건 조회");
            System.out.println("4. 주식 수정");
            System.out.println("5. 주식 삭제");
            System.out.println("6. 종료");
            System.out.println("=====================");
            System.out.print("번호 입력>> ");
            int choice = sc.nextInt();

            // 사용자 입력에 따라 각 기능 호출
            if (choice == 1) {
                insert(); // 주식 등록
            } else if (choice == 2) {
                selectList(); // 전체 목록 조회
            } else if (choice == 3) {
                selectOne(); // 개별 종목 조회
            } else if (choice == 4) {
                update(); // 주식 정보 수정
            } else if (choice == 5) {
                delete(); // 주식 삭제
            } else if (choice == 6) {
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            } else {
                System.out.println("선택이 올바르지 않음.");
            }
        }
    }

    // 주식 정보 입력 받아 DB에 저장
    public static void insert() throws SQLException {
        StockVO stock = new StockVO();

        // 사용자 입력 받아 VO에 세팅
        System.out.println("stockName, ticker, price, holdingQty 순서대로 입력하세요.");
        System.out.print("종목명 >> ");
        stock.setStockName(sc.next());
        System.out.print("티커 >> ");
        stock.setTicker(sc.next());
        System.out.print("현재 가격 >> ");
        stock.setPrice(sc.nextInt());
        System.out.print("보유 수량 >> ");
        stock.setHoldingQty(sc.nextInt());

        // DAO를 통해 insert 수행
        int result = stockDao.create(stock);
        if (result == 1) System.out.println("등록 성공!");
    }

    // 전체 주식 목록 조회
    public static void selectList() throws SQLException {
        List<StockVO> list = stockDao.getList(); // DAO로부터 List 받기
        for (StockVO stock : list) {
            printVO(stock); // 하나씩 출력
        }
    }

    // 특정 주식 1건 조회
    public static void selectOne() throws SQLException {
        System.out.print("조회할 티커 입력 >> ");
        String ticker = sc.next();
        StockVO stock = stockDao.get(ticker); // DAO로부터 VO 받기
        printVO(stock); // 결과 출력
    }

    // 주식 정보 수정
    public static void update() throws SQLException {
        StockVO stock = new StockVO();

        // 수정할 정보 입력 받기
        System.out.println("stockName, price, holdingQty, ticker 순서대로 입력하세요.");
        System.out.print("종목명 >> ");
        stock.setStockName(sc.next());
        System.out.print("현재 가격 >> ");
        stock.setPrice(sc.nextInt());
        System.out.print("보유 수량 >> ");
        stock.setHoldingQty(sc.nextInt());
        System.out.print("티커 >> ");
        stock.setTicker(sc.next());

        // DAO를 통해 update 수행
        int result = stockDao.update(stock);
        if (result == 1) System.out.println("수정 성공!");
    }

    // 주식 삭제
    public static void delete() throws SQLException {
        System.out.print("삭제할 티커 입력 >> ");
        String ticker = sc.next();
        int result = stockDao.delete(ticker); // DAO를 통해 delete 수행
        if (result == 1) System.out.println("삭제 성공!");
    }

    // 주식 정보 출력용 공통 함수
    private static void printVO(StockVO stock) {
        System.out.println("종목명: " + stock.getStockName()
                + ", 티커: " + stock.getTicker()
                + ", 가격: " + stock.getPrice()
                + ", 보유 수량: " + stock.getHoldingQty());
    }
}
