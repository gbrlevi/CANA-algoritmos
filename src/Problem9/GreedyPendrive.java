package Problem9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GreedyPendrive {
    public static void main(String[] args) {
        Pdf[] meusPdfs = {
                new Pdf("Introduction to algorithms", 25, 1200),
                new Pdf("Data structure in java", 10, 650),
                new Pdf("Learning complexity", 5, 200),
                new Pdf("Deisgn pattern and Project structure", 40, 1600),
                new Pdf("POO in practice", 18, 900)
        };

        double capacidadePendrive = 50;

        System.out.println("Pendrive total capacity: " + capacidadePendrive + "MB\n");

        calc(meusPdfs, capacidadePendrive);
    }

    private static void calc(Pdf[] pdfs, double pendriveMbs) {

        Comparator<Pdf> densityComparator = new Comparator<Pdf>() {
            @Override
            public int compare(Pdf p1, Pdf p2) {
                Double density1 =  p1.pages / p1.mb;
                Double density2 =  p2.pages / p2.mb;

                return density2.compareTo(density1);
            }
        };

        Arrays.sort(pdfs, densityComparator);

        double leftMbs = pendriveMbs;
        List<Pdf> result = new ArrayList<>();

        System.out.println("-- Selecting by greedy algorithm\n");

        for (Pdf pdf : pdfs) {
            double density = pdf.pages/pdf.mb;
            if (pdf.mb <= leftMbs) {
                result.add(pdf);
                leftMbs -= pdf.mb;
                System.out.println("Choosing this pdf for his density:" + pdf.name + ": " + density + " pages/mb");
                System.out.println("Left pendrive capacity:" + leftMbs + "\n");
            } else {
                System.out.println("This pdf exceeds max capacity!: " + pdf.name + ": " + density + " pages/mb");
            }
        }

        System.out.println("\n--- Chosen pdfs ---");
        for (Pdf finalPdf : result) {
            System.out.println("- " + finalPdf.name);
        }
    }

    private static class Pdf {
        String name;
        double mb;
        double pages;

        public Pdf(String name, double mb, double pages) {
            this.name = name;
            this.mb = mb;
            this.pages = pages;
        }
    }
}
