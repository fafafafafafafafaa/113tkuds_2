import java.util.Scanner;

public class TicTacToeBoard {
    private char[][] board;
    private char currentPlayer;

    // 初始化棋盤
    public TicTacToeBoard() {
        board = new char[3][3];
        currentPlayer = 'X'; // X 先手
        initializeBoard();
    }

    // 建立空棋盤
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // 印出棋盤
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // 嘗試放置棋子
    public boolean placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    // 切換玩家
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // 檢查是否有玩家獲勝
    public boolean checkWin() {
        // 檢查行
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        // 檢查列
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }

        // 檢查對角線
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    // 檢查是否平手
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // 主程式測試
    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 井字遊戲開始！ ===");
        game.printBoard();

        while (true) {
            System.out.println("玩家 " + game.currentPlayer + " 請輸入行與列 (0-2)：");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (game.placeMark(row, col)) {
                game.printBoard();

                if (game.checkWin()) {
                    System.out.println("玩家 " + game.currentPlayer + " 獲勝！");
                    break;
                } else if (game.isBoardFull()) {
                    System.out.println("平手！");
                    break;
                }

                game.changePlayer();
            } else {
                System.out.println("無效的下棋位置，請重新輸入！");
            }
        }

        scanner.close();
    }
}
