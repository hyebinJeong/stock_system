package org.stock;

import org.stock.common.JDBCUtil;
import org.stock.dao.StockDao;
import org.stock.dao.StockDaoImpl;
import org.stock.domain.StockVO;

import java.sql.SQLException;
import java.util.Scanner;

public class StockMain {
    // StockDao 객체 생성 (데이터베이스 작업을 위한 객체)
    static StockDao stockDao = new StockDaoImpl();

    // 사용자 입력을 받기 위한 Scanner 객체
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        while (true) {
            System.out.println("원하는 작업을 선택하시오.");
            System.out.println("=====================");
            System.out.println("1. insert");
            System.out.println("2. selectList");
            System.out.println("3. selectOne");
            System.out.println("4. update");
            System.out.println("5. delete");
            System.out.println("6. exit");
            System.out.println("=====================");
            System.out.print("번호 입력>> ");

            // 사용자로부터 번호 입력 받기
            int choice = sc.nextInt();
            if (choice == 1) {
                insert();
            } else if (choice == 2) {
                selectList();
            } else if (choice == 3){
                selectOne();
            } else if (choice == 4) {
                update();
            } else if(choice == 5){
                delete();
            }else if (choice == 6) {
                JDBCUtil.close();
                System.out.println("프로그램을 종료합니다.");
                System.exit(0); //프로그램 종료
            } else {
                System.out.println("선택이 올바르지 않음.");
            }
        }
    }

    private static void printVO(StockVO stockVO) {
        System.out.println("주식명 : " + stockVO.getStockName() + ", 종목 코드 : " + stockVO.getTicker() + ", 현재가 : " + stockVO.getPrice() + ", 보유 수량 : " + stockVO.getHoldingQty());
    }

    // 주식 정보 입력 메서드
    public static void insert() throws SQLException {
        System.out.println("주식 이름 입력");
        String stockName = sc.next();
        System.out.println("종목 코드 입력");
        String ticker = sc.next();
        System.out.println("현재가 입력");
        Integer price = sc.nextInt();
        System.out.println("보유 수량 입력");
        Integer holdingQty = sc.nextInt();

        // 입력값으로 StockVO 객체 생성
        StockVO stock = new StockVO();
        stock.setStockName(stockName);
        stock.setTicker(ticker);
        stock.setPrice(price);
        stock.setHoldingQty(holdingQty);

        // DAO를 통해 db에 주식 정보 저장
        int result = stockDao.create(stock);
        // 결과 출력
        if (result > 0) System.out.println("주식 등록 성공");
        else System.out.println("주식 등록 실패");
    }

    // 전체 주식 목록 출력 메서드
    public static void selectList() throws SQLException{
        System.out.println("주식 목록 조회");
        // 모든 주식 정보 가져와서 출력
        for(StockVO stock : stockDao.getList()){
            printVO(stock);
        }
    }

    // 특정 주식 조회
    public static void selectOne() throws SQLException {
        System.out.println("조회할 종목 코드");
        String ticker = sc.next();
        // DAO를 통해 해당 종목 코드의 주식 저오 가져오기
        StockVO stock = stockDao.get(ticker);
        // 정보가 있으면 출력, 없으면 안내 메시지
        if(stock.getTicker() != null){
            printVO(stock);
        } else{
            System.out.println("해당 종목이 없습니다.");
        }
    }

    // 주식 수정 메소드
    public static void update() throws SQLException{
        System.out.print("수정할 종목 코드 (ticker): ");
        String ticker = sc.next();

        StockVO stock = stockDao.get(ticker);

        if (stock.getTicker() == null) {
            System.out.println("해당 종목이 존재하지 않습니다.");
            return;
        }

        System.out.println("기존 정보:");
        printVO(stock);

        sc.nextLine(); // 개행 제거

        System.out.print("수정할 주식 이름 (" + stock.getStockName() + "): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) stock.setStockName(name);

        System.out.print("수정할 종목 코드 (" + stock.getTicker() + "): ");
        String newTicker = sc.nextLine();
        if (!newTicker.isEmpty()) stock.setTicker(newTicker);

        System.out.print("수정할 주식 현재가 (" + stock.getPrice() + "): ");
        String priceStr = sc.nextLine();
        if (!priceStr.isEmpty()) stock.setPrice(Integer.parseInt(priceStr));

        System.out.print("수정할 보유 수량 (" + stock.getHoldingQty() + "): ");
        String qtyStr = sc.nextLine();
        if (!qtyStr.isEmpty()) stock.setHoldingQty(Integer.parseInt(qtyStr));

        int result = stockDao.update(stock);
        if (result > 0) {
            System.out.println("수정 완료!");
        } else {
            System.out.println("수정 실패");
        }
    }

    // 주식 삭제 메서드
    public static void delete() throws SQLException{
        System.out.println("삭제할 종목 코드 (ticker) : ");
        String ticker = sc.next();

        StockVO stock = stockDao.get(ticker);

        if(stock.getTicker() == null){
            System.out.println("해당 종목이 존재하지 않습니다.");
            return;
        }

        printVO(stock);

        System.out.println("정말 삭제하시겠습니까? (y/n) : ");
        String confirm = sc.next();

        if(confirm.equalsIgnoreCase("y")){
            int result = stockDao.delete(ticker);
            if(result > 0){
                System.out.println("삭제 완료!");
            } else{
                System.out.println("삭제 실패!");
            }
        } else{
            System.out.println("삭제 취소됨.");
        }
    }

}
