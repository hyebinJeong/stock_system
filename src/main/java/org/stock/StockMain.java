// 파일 위치: src/main/java/org/stock/StockMain.java
package org.stock;

import org.stock.dao.StockDao;
import org.stock.dao.StockDaoImpl;
import org.stock.vo.StockVO;

import java.util.List;
import java.util.Scanner;

public class StockMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StockDao stockDao = new StockDaoImpl();

        while (true) {
            System.out.println("===== 주식 관리 시스템 =====");
            System.out.println("1. 전체 주식 조회");
            System.out.println("2. 새 주식 추가");
            System.out.println("3. 주식 수정");
            System.out.println("4. 주식 삭제");
            System.out.println("0. 종료");
            System.out.print("선택 > ");

            int choice = sc.nextInt();
            sc.nextLine(); // nextInt 후 개행 문자 버퍼 비우기

            if (choice == 1) {
                // 전체 주식 목록 조회
                List<StockVO> list = stockDao.getAllStocks();
                for (StockVO vo : list) {
                    System.out.println(vo);
                }

            } else if (choice == 2) {
                // 새 주식 추가 입력 받기
                System.out.print("주식명 입력: ");
                String name = sc.nextLine();

                System.out.print("종목코드 입력: ");
                String ticker = sc.nextLine();

                System.out.print("가격 입력: ");
                int price = sc.nextInt();
                sc.nextLine(); // 가격 입력 후 개행 버퍼 비우기

                System.out.print("수량 입력: ");
                int qty = sc.nextInt();
                sc.nextLine(); // 수량 입력 후 개행 버퍼 비우기

                // StockVO 객체 생성 후 데이터 설정
                StockVO stock = new StockVO();
                stock.setStockName(name);
                stock.setTicker(ticker);
                stock.setPrice(price);
                stock.setHoldingQty(qty);

                // DB에 삽입
                stockDao.insertStock(stock);
                System.out.println("주식이 추가되었습니다.");

            } else if (choice == 3) {
                // 주식 수정 (id 기준)
                System.out.print("수정할 주식 ID 입력: ");
                int id = sc.nextInt();
                sc.nextLine(); // 개행 문자 비우기

                System.out.print("새 주식명 입력: ");
                String name = sc.nextLine();

                System.out.print("새 종목코드 입력: ");
                String ticker = sc.nextLine();

                System.out.print("새 가격 입력: ");
                int price = sc.nextInt();
                sc.nextLine(); // 개행 문자 비우기

                System.out.print("새 수량 입력: ");
                int qty = sc.nextInt();
                sc.nextLine(); // 개행 문자 비우기

                StockVO stock = new StockVO();
                stock.setId(id);
                stock.setStockName(name);
                stock.setTicker(ticker);
                stock.setPrice(price);
                stock.setHoldingQty(qty);

                stockDao.updateStock(stock);
                System.out.println("주식이 수정되었습니다.");

            } else if (choice == 4) {
                // 주식 삭제 (id 기준)
                System.out.print("삭제할 주식 ID 입력: ");
                int id = sc.nextInt();
                sc.nextLine(); // 개행 문자 비우기

                stockDao.deleteStock(id);
                System.out.println("주식이 삭제되었습니다.");

            } else if (choice == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;

            } else {
                System.out.println("잘못된 선택입니다.");
            }
        }

        sc.close(); // Scanner 자원 해제
    }
}
