//import java.util.*;
//import java.util.stream.Collectors;
//
//public class NamesSplitter {
//
//    public void splitSemiFinalistsNames(List<Tournament> euro) {
//
//    }
//
//    public static void main(String[] args) {
//
//        List<Tournament> euro = Arrays.asList(
//                new Tournament(1960, "Russia", "Yugoslavia", "Czechoslovakia", "various", 2),
//                new Tournament(1964, "Spain", "Russia", "Hungary", "various", 2),
//                new Tournament(1968, "Italy", "Yugoslavia", "England", "Dragan Dzajić", 2),
//                new Tournament(1972, "West Germany", "Russia", "Belgium", "Gerd Mueller", 4),
//                new Tournament(1976, "Czechoslovakia", "West Germany", "Netherlands", "Dieter Mueller", 4),
//                new Tournament(1980, "Germany", "Belgium", "Czechoslovakia", "Klaus Allofs", 3),
//                new Tournament(1984, "France", "Spain", "Denmark & Portugal", "Michel Platini", 9),
//                new Tournament(1988, "Netherlands", "Russia", "Germany & Italy", "Marco van Basten", 5),
//                new Tournament(1992, "Denmark", "Germany", "Netherlands & Sweden", "various", 3),
//                new Tournament(1996, "Germany", "Czech Republic", "France & England", "Alan Shearer", 5),
//                new Tournament(2000, "France", "Italy", "Netherlands & Portugal", "Patrick Kluivert", 5),
//                new Tournament(2004, "Greece", "Portugal", "Netherlands & Czech Republic", "Milan Baros", 5),
//                new Tournament(2008, "Spain", "Germany", "Russia & Turkey", "David Villa", 4),
//                new Tournament(2012, "Spain", "Italy", "Portugal & Germany", "Fernando Torres", 3),
//                new Tournament(2016, "Portugal", "France", "Germany & Wales", "Antoine Griezmann", 6));
//
//
//        List<String> countriesName = new ArrayList<>();
//
//        for (Tournament t : euro) {
//            countriesName.add(t.getBronzeMedalist().concat(" "));
//            countriesName.add(t.getSilverMedalist().concat(" "));
//            countriesName.add(t.getGoldMedalist().concat(" "));
//        }
//
//
//        String [] arrayOfStrings = new String[countriesName.size()];
//        arrayOfStrings = countriesName.toArray(arrayOfStrings);
//
//        List<String> splittedStringsList = new ArrayList<>();
//
//        for (int i = 0; i < arrayOfStrings.length; i++) {
//            if (arrayOfStrings[i].contains("&")) {
//                String[] newArrayOfStrings = arrayOfStrings[i].split("&");
//                String string1 = newArrayOfStrings[0];
//                splittedStringsList.add(string1.replaceAll("West Germany ", "Germany").replaceAll("USSR ", "USSR/Russia").replaceAll("Russia ", "USSR/Russia").replaceAll("Czech Republic ", "Czechoslovakia/Czech Republic")
//                        .replaceAll("Czechoslovakia ", "Czechoslovakia/Czech Republic").trim());
//                String string2 = newArrayOfStrings[1];
//                splittedStringsList.add(string2.replaceAll("West Germany ", "Germany").replaceAll("USSR ", "USSR/Russia").replaceAll("Russia ", "USSR/Russia").replaceAll("Czech Republic ", "Czechoslovakia/Czech Republic")
//                        .replaceAll("Czechoslovakia ", "Czechoslovakia/Czech Republic").trim());
//            } else {
//                splittedStringsList.add(arrayOfStrings[i].replaceAll("West Germany ", "Germany").replaceAll("USSR ", "USSR/Russia").replaceAll("Russia ", "USSR/Russia").replaceAll("Czech Republic ", "Czechoslovakia/Czech Republic")
//                        .replaceAll("Czechoslovakia ", "Czechoslovakia/Czech Republic").trim());
//            }
//        }
//
//        List<String> listWithoutDuplicates = splittedStringsList.stream().distinct().sorted().collect(Collectors.toList());
//
//        for (String s : listWithoutDuplicates) {
//            System.out.println(s);
//        }
//
//    }
//
//}
