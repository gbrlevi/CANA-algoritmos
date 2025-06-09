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

        int capacidadePendrive = 50;

        System.out.println("Pendrive total capacity: " + capacidadePendrive + "MB\n");

        calc(meusPdfs, capacidadePendrive);
    }

    private static void calc(Pdf[] pdfs, int pendriveMbs){

        Comparator<Pdf> densityComparator = new Comparator<Pdf>() {
            @Override
            public int compare(Pdf p1, Pdf p2) {
                Double density1 = (double) p1.pages / p1.mb;
                Double density2 = (double) p2.pages / p2.mb;

                return density2.compareTo(density1);
            }
        };

        Arrays.sort(pdfs, densityComparator);

        int leftMbs = pendriveMbs;
        List<Pdf> result = new ArrayList<>();

        for(Pdf pdf: pdfs){
            if(pdf.mb <= leftMbs){
                result.add(pdf);
                leftMbs -= pdf.mb;
                System.out.println("Choosing this pdf for his density:" + pdf.name + ": " + pdf.mb);
                System.out.println("Left pendrive capacity:" + leftMbs);
            } else{
                System.out.println("This pdf exceeds max capacity!: "+ pdf.name);
            }
        }

        System.out.println("\n--- Chosen pdfs ---");
        for (Pdf finalPdf : result) {
            System.out.println("- " + finalPdf.name);
        }
    }

    private static class Pdf {
        String name;
        int mb;
        int pages;

        public Pdf(String name, int mb, int pages) {
            this.name = name;
            this.mb = mb;
            this.pages = pages;
        }
    }
}
