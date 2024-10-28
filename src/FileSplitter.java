import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama file: ");
        String fileName = scanner.nextLine();

        System.out.print("Masukkan jumlah baris per bagian: ");
        int linesPerPart = scanner.nextInt();

        Queue<String> queue = new LinkedList<>();
        int partNumber = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                queue.add(line);
                lineCount++;

                if (lineCount == linesPerPart) {
                    writeQueueToFile(queue, partNumber++);
                    lineCount = 0;
                }
            }

            if (!queue.isEmpty()) {
                writeQueueToFile(queue, partNumber);
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }

        scanner.close();
    }

    private static void writeQueueToFile(Queue<String> queue, int partNumber) {
        String outputFileName = "Part_" + partNumber + ".txt";

        try (FileWriter writer = new FileWriter(outputFileName)) {
            while (!queue.isEmpty()) {
                writer.write(queue.poll() + "\n");
            }
            System.out.println("Berhasil menyimpan " + outputFileName);

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis ke file: " + e.getMessage());
        }
    }
}
