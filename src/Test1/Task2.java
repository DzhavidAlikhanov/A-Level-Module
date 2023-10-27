package Test1;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int chessboardSize = 8;
        int[][] chessboard = new int[chessboardSize][chessboardSize];

        while (true) {
            System.out.println("Для выхода из цикла введите число вне числового диапазона.");
            System.out.println();

            System.out.println("Введите текущее положение коня (x1 y1 - от 1 до 8): ");
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();

            if (x1 < 1 || y1 < 1 || x1 > 8 || y1 > 8) {
                System.out.println("Выход из программы.");
                break;
            }

            System.out.println("Введите клетку в которую вы хотите передвинуть коня (x2 y2 - от 1 до 8): ");
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            if (x2 < 1 || y2 < 1 || x2 > 8 || y2 > 8 ) {
                System.out.println("Выход из программы.");
                break;
            }


            boolean canMove = isKnightMovePossible(x1, y1, x2, y2, chessboard);

            if (canMove) {
                System.out.println("Конь может совершить ход. ");
            } else {
                System.out.println("Конь не может совершить ход. ");
            }
        }

        scanner.close();
    }

    public static boolean isKnightMovePossible(int x1, int y1, int x2, int y2, int[][] chessboardSize) {
        if (x1 - 1 < 0 || x1 - 1 >= chessboardSize.length
                || y1 - 1 < 0 || y1 - 1 >= chessboardSize.length
                || x2 - 1 < 0 || x2 - 1 >= chessboardSize.length
                || y2 - 1 < 0 || y2 - 1 >= chessboardSize.length) {
            return false;
        }

        int deltaX = Math.abs(x1 - x2);
        int deltaY = Math.abs(y1 - y2);

        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }
}

