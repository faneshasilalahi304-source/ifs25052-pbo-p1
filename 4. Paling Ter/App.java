import java.util.Scanner;
import java.util.TreeMap;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeMap<Integer, Integer> freq = new TreeMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) break;
            if (line.isEmpty()) continue;
            int value = Integer.parseInt(line);
            freq.put(value, freq.getOrDefault(value, 0) + 1);
        }

        if (freq.isEmpty()) {
            return;
        }

        int tertinggi = freq.lastKey();
        int terendah = freq.firstKey();

        int terbanyakNilai = 0, terbanyakFreq = -1;
        int tersedikitNilai = 0, tersedikitFreq = Integer.MAX_VALUE;
        int jumlahTertinggiNilai = 0, jumlahTertinggiFreq = 0;
        long jumlahTertinggiVal = Long.MIN_VALUE;
        int jumlahTerendahNilai = 0, jumlahTerendahFreq = 0;
        long jumlahTerendahVal = Long.MAX_VALUE;

        for (int nilai : freq.keySet()) {
            int f = freq.get(nilai);
            long jumlah = (long) nilai * f;

            if (f > terbanyakFreq || (f == terbanyakFreq && nilai > terbanyakNilai)) {
                terbanyakFreq = f;
                terbanyakNilai = nilai;
            }

            if (f < tersedikitFreq || (f == tersedikitFreq && nilai < tersedikitNilai)) {
                tersedikitFreq = f;
                tersedikitNilai = nilai;
            }

            if (jumlah > jumlahTertinggiVal || (jumlah == jumlahTertinggiVal && nilai > jumlahTertinggiNilai)) {
                jumlahTertinggiVal = jumlah;
                jumlahTertinggiNilai = nilai;
                jumlahTertinggiFreq = f;
            }

            if (jumlah < jumlahTerendahVal || (jumlah == jumlahTerendahVal && nilai < jumlahTerendahNilai)) {
                jumlahTerendahVal = jumlah;
                jumlahTerendahNilai = nilai;
                jumlahTerendahFreq = f;
            }
        }

        System.out.println("Tertinggi: " + tertinggi);
        System.out.println("Terendah: " + terendah);
        System.out.println("Terbanyak: " + terbanyakNilai + " (" + terbanyakFreq + "x)");
        System.out.println("Tersedikit: " + tersedikitNilai + " (" + tersedikitFreq + "x)");
        System.out.println("Jumlah Tertinggi: " + jumlahTertinggiNilai + " * " + jumlahTertinggiFreq
                + " = " + jumlahTertinggiVal);
        System.out.println("Jumlah Terendah: " + jumlahTerendahNilai + " * " + jumlahTerendahFreq
                + " = " + jumlahTerendahVal);
    }
}