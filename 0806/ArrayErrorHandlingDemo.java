// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.Arrays;

public class ArrayErrorHandlingDemo {
   public ArrayErrorHandlingDemo() {
   }

   public static int safeGet(int[] var0, int var1, int var2) {
      if (var0 == null) {
         System.out.println("警告：陣列為 null，返回預設值");
         return var2;
      } else if (var1 >= 0 && var1 < var0.length) {
         return var0[var1];
      } else {
         System.out.printf("警告：索引 %d 超出範圍 [0, %d]，返回預設值\n", var1, var0.length - 1);
         return var2;
      }
   }

   public static boolean safeSet(int[] var0, int var1, int var2) {
      if (var0 == null) {
         System.out.println("錯誤：無法在 null 陣列上設定值");
         return false;
      } else if (var1 >= 0 && var1 < var0.length) {
         var0[var1] = var2;
         System.out.printf("成功：array[%d] = %d\n", var1, var2);
         return true;
      } else {
         System.out.printf("錯誤：無法在索引 %d 設定值，有效範圍是 [0, %d]\n", var1, var0.length - 1);
         return false;
      }
   }

   public static void demonstrateCommonErrors() {
      int[] var0 = new int[]{10, 20, 30, 40, 50};
      System.out.println("陣列內容：" + Arrays.toString(var0));
      System.out.printf("陣列長度：%d\n", var0.length);
      System.out.printf("有效索引範圍：0 到 %d\n", var0.length - 1);
      System.out.println();
      System.out.println("=== 常見錯誤處理 ===");

      int var1;
      try {
         var1 = var0[var0.length];
      } catch (ArrayIndexOutOfBoundsException var3) {
         System.out.println("捕獲錯誤：陣列索引超出範圍");
      }

      try {
         var1 = var0[-1];
      } catch (ArrayIndexOutOfBoundsException var2) {
         System.out.println("捕獲錯誤：負數索引");
      }

   }

   public static void main(String[] var0) {
      demonstrateCommonErrors();
      System.out.println("\n=== 安全存取示範 ===");
      int[] var1 = new int[]{10, 20, 30, 40, 50};
      System.out.println("正常存取：" + safeGet(var1, 2, -1));
      System.out.println("越界存取：" + safeGet(var1, 10, -1));
      System.out.println("負數索引：" + safeGet(var1, -1, -1));
      System.out.println("null 陣列：" + safeGet((int[])null, 0, -1));
      System.out.println("\n安全設定測試：");
      safeSet(var1, 2, 999);
      safeSet(var1, 10, 888);
      safeSet((int[])null, 0, 777);
      System.out.println("最終陣列：" + Arrays.toString(var1));
   }
}
