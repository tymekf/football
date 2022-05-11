import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Tournament> euro = Arrays.asList(
                new Tournament(1960, "Russia", "Yugoslavia", "Czechoslovakia", "various", 2),
                new Tournament(1964, "Spain", "Russia", "Hungary", "various", 2),
                new Tournament(1968, "Italy", "Yugoslavia", "England", "Dragan Dzajić", 2),
                new Tournament(1972, "West Germany", "Russia", "Belgium", "Gerd Mueller", 4),
                new Tournament(1976, "Czechoslovakia", "West Germany", "Netherlands", "Dieter Mueller", 4),
                new Tournament(1980, "Germany", "Belgium", "Czechoslovakia", "Klaus Allofs", 3),
                new Tournament(1984, "France", "Spain", "Denmark & Portugal", "Michel Platini", 9),
                new Tournament(1988, "Netherlands", "Russia", "Germany & Italy", "Marco van Basten", 5),
                new Tournament(1992, "Denmark", "Germany", "Netherlands & Sweden", "various", 3),
                new Tournament(1996, "Germany", "Czech Republic", "France & England", "Alan Shearer", 5),
                new Tournament(2000, "France", "Italy", "Netherlands & Portugal", "Patrick Kluivert", 5),
                new Tournament(2004, "Greece", "Portugal", "Netherlands & Czech Republic", "Milan Baros", 5),
                new Tournament(2008, "Spain", "Germany", "Russia & Turkey", "David Villa", 4),
                new Tournament(2012, "Spain", "Italy", "Portugal & Germany", "Fernando Torres", 3),
                new Tournament(2016, "Portugal", "France", "Germany & Wales", "Antoine Griezmann", 6));

        System.out.println("Which country's Euro performance you want to check?");
        Scanner input = new Scanner(System.in);
        String country = input.nextLine();




        System.out.println("\nList of top scorers by year:");
        for (Tournament t : euro) {
            System.out.println(t.getYear() + ": " + t.getTopScorer() + " (" + t.getGoalsScored() + ")");
        }
    }

}
