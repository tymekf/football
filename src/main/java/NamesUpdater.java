import java.util.*;

public class NamesUpdater {

    public static void main(String[] args) {

        List<Tournament> euro = Arrays.asList(
                new Tournament(1960, "USSR ", "Yugoslavia ", "Czechoslovakia ", "various", 2),
                new Tournament(1964, "Spain ", "USSR ", "Hungary ", "various", 2),
                new Tournament(1968, "Italy ", "Yugoslavia ", "England ", "Dragan Dzajić", 2),
                new Tournament(1972, "West Germany ", "USSR ", "Belgium ", "Gerd Mueller", 4),
                new Tournament(1976, "Czechoslovakia ", "West Germany ", "Netherlands", "Dieter Mueller", 4),
                new Tournament(1980, "Germany ", "Belgium ", "Czechoslovakia ", "Klaus Allofs", 3),
                new Tournament(1984, "France ", "Spain ", "Denmark & Portugal", "Michel Platini", 9),
                new Tournament(1988, "Netherlands ", "Russia ", "Germany & Italy", "Marco van Basten", 5),
                new Tournament(1992, "Denmark ", "Germany ", "Netherlands & Sweden", "various", 3),
                new Tournament(1996, "Germany ", "Czech Republic ", "France & England", "Alan Shearer", 5),
                new Tournament(2000, "France ", "Italy", "Netherlands & Portugal", "Patrick Kluivert", 5),
                new Tournament(2004, "Greece ", "Portugal ", "Netherlands & Czech Republic", "Milan Baros", 5),
                new Tournament(2008, "Spain ", "Germany ", "Russia & Turkey", "David Villa", 4),
                new Tournament(2012, "Spain ", "Italy ", "Portugal & Germany", "Fernando Torres", 3),
                new Tournament(2016, "Portugal ", "France ", "Germany & Wales", "Antoine Griezmann", 6),
                new Tournament(2020, "Italy ", "England ", "Spain & Denmark", "Patrik Schick", 5));

        List<String> namesToUpdate = new ArrayList<>();
        for (Tournament t : euro) {
            namesToUpdate.add(t.getGoldMedalist());
            namesToUpdate.add(t.getSilverMedalist());
            if (!t.getBronzeMedalist().contains("&")) {
                namesToUpdate.add(t.getBronzeMedalist());
            }
        }

        for (String ugabuga : namesToUpdate) {
            System.out.println("UGABUGA\n" + ugabuga);
        }

        namesToUpdate.toArray();

        int i = 0;
        for (String s : namesToUpdate) {
            s = namesToUpdate.get(i).replaceAll("West Germany", "Germany").replaceAll("Czechoslovakia ", "Czechoslovakia/Czech Republic")
                    .replaceAll("Czech Republic ", "Czechoslovakia/Czech Republic").replaceAll("USSR ", "USSR/Russia").replaceAll("Russia ", "USSR/Russia");
            i++;

        }
        for (String bulbazaur : namesToUpdate) {
            System.out.println("BULBULCZAK\n" + bulbazaur);
        }

    }

}

