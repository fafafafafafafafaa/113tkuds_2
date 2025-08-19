public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        // 計算平均、最高、最低
        int sum = 0;
        int max = scores[0];
        int min = scores[0];

        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }
        double avg = (double) sum / scores.length;

        // 統計等第
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
        for (int score : scores) {
            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }

        // 統計高於平均的人數
        int aboveAvgCount = 0;
        for (int score : scores) {
            if (score > avg) aboveAvgCount++;
        }

        // 輸出成績報表
        System.out.println("===== 成績報表 =====");
        System.out.print("成績列表: ");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println("\n---------------------");
        System.out.printf("平均分: %.2f%n", avg);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("---------------------");
        System.out.println("A 等人數: " + countA);
        System.out.println("B 等人數: " + countB);
        System.out.println("C 等人數: " + countC);
        System.out.println("D 等人數: " + countD);
        System.out.println("F 等人數: " + countF);
        System.out.println("---------------------");
        System.out.println("高於平均分人數: " + aboveAvgCount);
        System.out.println("=====================");
    }
}
