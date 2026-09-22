import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().trim().split("\\s+");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        if (n == 1) {
            int tengah = matrix[0][0];
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + tengah);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + tengah);
            return;
        }

        if (n == 2) {
            int total = matrix[0][0] + matrix[0][1] + matrix[1][0] + matrix[1][1];
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + total);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + total);
            return;
        }

        int nilaiL = 0;
        int nilaiLK = 0;
        for (int i = 0; i < n; i++) {
            nilaiL += matrix[i][0];
            nilaiLK += matrix[i][n - 1];
        }
        for (int j = 1; j <= n - 2; j++) {
            nilaiL += matrix[n - 1][j];
            nilaiLK += matrix[0][j];
        }

        int nilaiTengah;
        if (n % 2 == 1) {
            nilaiTengah = matrix[n / 2][n / 2];
        } else {
            int mid = n / 2;
            nilaiTengah = matrix[mid - 1][mid - 1] + matrix[mid - 1][mid]
                    + matrix[mid][mid - 1] + matrix[mid][mid];
        }

        int perbedaan = Math.abs(nilaiL - nilaiLK);
        int dominan = (perbedaan == 0) ? nilaiTengah : Math.max(nilaiL, nilaiLK);

        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + nilaiLK);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }
}