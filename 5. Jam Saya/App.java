import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String jamAwalInput = scanner.nextLine().trim();

        String[] parts = jamAwalInput.split(":", -1);
        if (parts.length != 2) {
            System.out.println("Jam tidak valid");
            return;
        }

        int jam, menit;
        try {
            jam = Integer.parseInt(parts[0].trim());
            menit = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("Jam tidak valid");
            return;
        }

        if (jam < 0 || jam > 23 || menit < 0 || menit > 59) {
            System.out.println("Jam tidak valid");
            return;
        }

        int currentMinutes = jam * 60 + menit;
        int totalGeser = 0;
        int pergantianHari = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) break;
            if (line.isEmpty()) continue;

            char tanda = line.charAt(0);
            String sisa = line.substring(1);

            boolean sisaAngka = !sisa.isEmpty() && sisa.chars().allMatch(Character::isDigit);
            if ((tanda != '+' && tanda != '-') || !sisaAngka) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            int n = Integer.parseInt(sisa);
            int delta = (tanda == '+') ? n : -n;

            totalGeser += delta;
            currentMinutes += delta;

            while (currentMinutes >= 1440) {
                currentMinutes -= 1440;
                pergantianHari++;
            }
            while (currentMinutes < 0) {
                currentMinutes += 1440;
                pergantianHari++;
            }
        }

        int jamAkhir = currentMinutes / 60;
        int menitAkhir = currentMinutes % 60;

        String totalMenitStr = (totalGeser > 0) ? "+" + totalGeser : String.valueOf(totalGeser);

        System.out.printf("Jam Awal: %02d:%02d%n", jam, menit);
        System.out.printf("Jam Akhir: %02d:%02d%n", jamAkhir, menitAkhir);
        System.out.println("Total Menit: " + totalMenitStr);
        System.out.println("Pergantian Hari: " + pergantianHari);
    }
}