import java.util.Scanner;

public class App {
    static final String[] NAMA_KOMPONEN = {"Partisipatif", "Tugas", "Kuis", "Proyek", "UTS", "UAS"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] bobotDeclared = new int[6];
        int totalBobotDeclared = 0;
        for (int i = 0; i < 6; i++) {
            bobotDeclared[i] = Integer.parseInt(scanner.nextLine().trim());
            totalBobotDeclared += bobotDeclared[i];
        }

        if (totalBobotDeclared != 100) {
            System.out.println("Total bobot harus 100");
            return;
        }

        int[] totalBobotData = new int[6];
        int[] totalPerolehanData = new int[6];

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().equals("---")) break;

            String[] parts = line.split("\\|", -1);
            if (parts.length != 3) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            String simbol = parts[0].trim();
            String bobotStr = parts[1].trim();
            String perolehanStr = parts[2].trim();

            int bobot, perolehan;
            try {
                bobot = Integer.parseInt(bobotStr);
                perolehan = Integer.parseInt(perolehanStr);
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            int index = indexOfSimbol(simbol);
            if (index == -1) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }

            if (perolehan > bobot) perolehan = bobot;
            if (perolehan < 0) perolehan = 0;

            totalBobotData[index] += bobot;
            totalPerolehanData[index] += perolehan;
        }

        double nilaiAkhir = 0;

        System.out.println("Perolehan Nilai:");
        for (int i = 0; i < 6; i++) {
            int persen = totalBobotData[i] == 0 ? 0 : (totalPerolehanData[i] * 100) / totalBobotData[i];
            double kontribusi = (persen / 100.0) * bobotDeclared[i];
            nilaiAkhir += kontribusi;
            System.out.printf(">> %s: %d/100 (%.2f/%d)%n", NAMA_KOMPONEN[i], persen, kontribusi, bobotDeclared[i]);
        }

        System.out.println();
        System.out.printf(">> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + getGrade(nilaiAkhir));
    }

    private static int indexOfSimbol(String simbol) {
        switch (simbol) {
            case "PA": return 0;
            case "T": return 1;
            case "K": return 2;
            case "P": return 3;
            case "UTS": return 4;
            case "UAS": return 5;
            default: return -1;
        }
    }

    private static String getGrade(double nilai) {
        if (nilai >= 79.5) return "A";
        if (nilai >= 72) return "AB";
        if (nilai >= 64.5) return "B";
        if (nilai >= 57) return "BC";
        if (nilai >= 49.5) return "C";
        if (nilai >= 34) return "D";
        return "E";
    }
}