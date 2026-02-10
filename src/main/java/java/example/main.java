package java.example;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class main {
    public static void main(String[] args) {
        String s="ciao a tutti";
        //
        StringAnalyzer s2=new StringAnalyzerImpl();
        String s3=converti(s,s2);
        System.out.println(s3);
        //
        s2=new StringAnalyzer() {
            @Override
            public String converti(String s) {
                return s.toUpperCase();
            }
        };
        s3=converti(s,s2);
        System.out.println(s3);
        //
        s2=(s12)->s.toUpperCase();
        s3=converti(s,s2);
        System.out.println(s3);

        int c=new Random().nextInt(10);
        String s4=switch (c){
            case 0 -> "zero";
            case 1 -> "uno";
            default -> "altro";
        };

        var s5 =c==2?"due": LocalDate.now();
        switch (s5){
            case String s6 -> System.out.println(s6);
            case LocalDate ld -> System.out.println(ld);
            default -> System.out.println("default");
        }

        var c1="ciao";
        var var = "var";

        String testo= """
                ciao a tutti
                sono un testo che prende
                anche la formattazione
                
                """;
        Set<String> set=new HashSet<>();



    }

    public static String converti(String s,StringAnalyzer s2){
        return s2.converti(s);
    }
}
