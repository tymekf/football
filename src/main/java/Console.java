import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Console implements Comparator<Long> {

    private Scanner scanner = new Scanner(System.in);
    private String choice;
    private String CONTINUE = "\nPress any alphanumerical key to continue.";

    public static void main(String[] args) {

        Console console = new Console();
        console.printInitialMenu();
    }

    @Override
    public int compare(Long o1, Long o2) {
        return 0;
    }

    void printInitialMenu() {
        System.out.println("Welcome to the jungle. \nChoose a tournament you want to check:"
                + "\n-----------------------------------------------------");
        System.out.println("1. UEFA Euro Championship" +
                "\n2. FIFA World Cup" +
                "\n3. Copa America" +
                "\n4. Champions League" +
                "\n-----------------------" +
                "\n5. Check a particular year" +
                "\n6. Check a particular country" +
                "\n7. Ballon d'Or"
        );

        choice = scanner.next();

        switch (choice) {
            case "1":
                printOptionMenu(euro);
            case "2":
                printOptionMenu(worldCups);
            case "3":
                printOptionMenu(copaAmerica);
            case "4":
                printOptionMenu(championsLeague);
            case "5":
                System.out.println("Enter a year:");
                int year = scanner.nextInt();
                checkAParticularYear(year);
                scanner.next();
                printInitialMenu();
            case "6":
                System.out.println("Enter a country");
                String country = scanner.next();
                showAchievementsByCountry(country);
                scanner.next();
                printInitialMenu();
            case "7":
                showBallonDOrWinner(ballonDOr);

        }
    }

    void printOptionMenu(List<Tournament> tournament) {
        System.out.println("\nWhat do you want to know?" + "\n------------------------");
        System.out.println("1. List of winners by year" +
                "\n2. Who has the most wins?" +
                "\n3. Who has the most medals overall?" +
                "\n4. Performance of a certain team" +
                "\n5. List of top scorers by year" +
                "\n6. Who has the most finals?" +
                "\n7. BACK"
        );

        choice = scanner.next();

        switch (choice) {
            case "1":
                System.out.println("Winners by year:");
                showWinnersByYear(tournament);
                System.out.println(CONTINUE);
                scanner.next();
                printOptionMenu(tournament);
            case "2":
                System.out.println("MOST WINS:");
                whoHasTheMostWins(tournament);
                System.out.println(CONTINUE);
                scanner.next();
                printOptionMenu(tournament);
            case "3":
                System.out.println("MOST MEDALS OVERALL:");
                whoHasTheMostMedals(tournament);
                System.out.println(CONTINUE);
                scanner.next();
                printOptionMenu(tournament);
            case "4":
                System.out.println("WHICH NATIONAL TEAM YOU WANT TO CHECK? THE SEARCH IS LIMITED TO THE FINAL FOUR STAGE: ");
                splitSemiFinalsTeam(tournament);
                String country = scanner.next();
                showTeamPerformances(tournament, country);
                System.out.println(CONTINUE);
                scanner.next();
                printOptionMenu(tournament);
            case "5":
                showTopScorersByYear(tournament);
                System.out.println(CONTINUE);
                scanner.next();
                printOptionMenu(tournament);
            case "6":
                System.out.println("MOST APPEARANCES IN THE FINAL: ");
                whoHasTheMostFinals(tournament);
                System.out.println(CONTINUE);
                scanner.next();
                printOptionMenu(tournament);
            case "7":
                printInitialMenu();

        }
    }

    void printAllSemifinalists(List<Tournament> tournament) {

        List<String> listOfSemifinalists = new ArrayList<>();
        for (Tournament t : tournament) {
            listOfSemifinalists.add(t.getGoldMedalist().concat(" "));
            listOfSemifinalists.add(t.getSilverMedalist().concat(" "));
            listOfSemifinalists.add(t.getBronzeMedalist().concat(" "));
        }

        listOfSemifinalists.stream().distinct().sorted().forEach(System.out::println);

    }

    void showWinnersByYear(List<Tournament> tournament) {

        tournament.stream().map(t1->t1.getYear() + ": " + t1.getGoldMedalist()).forEach(System.out::println);

    }

    void whoHasTheMostWins(List<Tournament> tournament) {

        List<String> listOfWinners = tournament.stream().map(Tournament::getGoldMedalist).map(t1 -> t1.concat(" ")).collect(Collectors.toList());
        updateCountriesNames(listOfWinners);

    }

    void whoHasTheMostMedals(List<Tournament> tournament) {

        List<String> mostMedals = new ArrayList<>();

        for (Tournament t : tournament) {
            mostMedals.add(t.getGoldMedalist().concat(" "));
            mostMedals.add(t.getSilverMedalist().concat(" "));
            if (!t.getBronzeMedalist().contains("&")) {
                mostMedals.add(t.getBronzeMedalist().concat(" "));
            }

        }

        updateCountriesNames(mostMedals);

    }

    void showTeamPerformances(List<Tournament> tournament, String country) {

        for (Tournament t : tournament) {
            if (t.getGoldMedalist().contains(country))
                System.out.println(t.getYear() + ": winners");
            else if (t.getSilverMedalist().contains(country)) {
                System.out.println(t.getYear() + ": runners-up");
            } else if (t.getBronzeMedalist().contains(country)) {
                if (t.getBronzeMedalist().contains(" & ")) {
                    System.out.println(t.getYear() + ": semi final");
                } else {
                    System.out.println(t.getYear() + ": bronze medal");
                }
            }
        }
    }

    void showTopScorersByYear(List<Tournament> tournament) {
        System.out.println("Top scorers by tournament: ");
        for (Tournament t : tournament) {
            System.out.println(t.getYear() + ": " + t.getTopScorer() + ": " + t.getGoalsScored());
        }
    }

    void updateCountriesNames(List<String> listOfCountriesNames) {

        String [] stringsListConvertedToAnArray = new String[listOfCountriesNames.size()];
        stringsListConvertedToAnArray = listOfCountriesNames.toArray(stringsListConvertedToAnArray);

        List<String> listOfMedals = new ArrayList<>();

        for (String s : stringsListConvertedToAnArray) {
            s = s.replaceAll("West Germany ", "Germany").replaceAll("USSR ", "USSR/Russia").replaceAll("Russia ", "USSR/Russia").replaceAll("Czech Republic ", "Czechoslovakia/Czech Republic")
                    .replaceAll("Czechoslovakia ", "Czechoslovakia/Czech Republic");
            s = s.trim();
            listOfMedals.add(s);

        }

        Map<String, Long> allMedals = listOfMedals.stream().
                collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        Stream<Map.Entry<String, Long>> sorted =
                allMedals.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        sorted.forEach(System.out::println);

    }

    void splitSemiFinalsTeam(List <Tournament> tournament) {

        List<String> countriesName = new ArrayList<>();

        for (Tournament t : tournament) {
            countriesName.add(t.getBronzeMedalist().concat(" "));
            countriesName.add(t.getSilverMedalist().concat(" "));
            countriesName.add(t.getGoldMedalist().concat(" "));
        }


        String [] arrayOfStrings = new String[countriesName.size()];
        arrayOfStrings = countriesName.toArray(arrayOfStrings);

        List<String> splittedStringsList = new ArrayList<>();

        for (int i = 0; i < arrayOfStrings.length; i++) {
            if (arrayOfStrings[i].contains("&")) {
                String[] newArrayOfStrings = arrayOfStrings[i].split("&");
                String string1 = newArrayOfStrings[0];
                splittedStringsList.add(string1.replaceAll("West Germany ", "Germany").replaceAll("USSR ", "USSR/Russia").replaceAll("Russia ", "USSR/Russia").replaceAll("Czech Republic ", "Czechoslovakia/Czech Republic")
                        .replaceAll("Czechoslovakia ", "Czechoslovakia/Czech Republic").trim());
                String string2 = newArrayOfStrings[1];
                splittedStringsList.add(string2.replaceAll("West Germany ", "Germany").replaceAll("USSR ", "USSR/Russia").replaceAll("Russia ", "USSR/Russia").replaceAll("Czech Republic ", "Czechoslovakia/Czech Republic")
                        .replaceAll("Czechoslovakia ", "Czechoslovakia/Czech Republic").trim());
            } else {
                splittedStringsList.add(arrayOfStrings[i].replaceAll("West Germany ", "Germany").replaceAll("USSR ", "USSR/Russia").replaceAll("Russia ", "USSR/Russia").replaceAll("Czech Republic ", "Czechoslovakia/Czech Republic")
                        .replaceAll("Czechoslovakia ", "Czechoslovakia/Czech Republic").trim());
            }
        }

        splittedStringsList.stream().distinct().sorted().forEach(System.out::println);

    }

    void checkAParticularYear(int year) {

        checkForAwards(year, ballonDOr);
        checkForTournaments(year, euro);
        checkForTournaments(year, worldCups);
        checkForTournaments(year, copaAmerica);
        checkForTournaments(year, championsLeague);

    }

    void checkForTournaments(int year, List<Tournament> tournament) {

        if (tournament == euro) {
            for (Tournament t : euro) {
                if (year == t.getYear()) {
                    System.out.println("EURO");
                }
            }
        } else if (tournament == worldCups) {
            for (Tournament t : worldCups) {
                if (year == t.getYear()) {
                    System.out.println("WORLD CUP");
                }
            }
        } else if (tournament == copaAmerica) {
            for (Tournament t : copaAmerica) {
                if (year == t.getYear()) {
                    System.out.println("COPA AMERICA");
                }
            }
        } else if (tournament == championsLeague) {
            for (Tournament t : championsLeague) {
                if (year == t.getYear()) {
                    System.out.println("CHAMPIONS LEAGUE");
                }
            }
        }

        for (Tournament t : tournament) {
            if (year == t.getYear()) {
                System.out.println("winner: " + t.getGoldMedalist());
                System.out.println("runner-up: " + t.getSilverMedalist());
                System.out.println("top scorer: " + t.getTopScorer());
            }
        }


    }

    void checkForAwards(int year, List<Award> award) {

        if (award == ballonDOr) {
            for (Award a : ballonDOr) {
                if (year == a.getAwardYear()) {
                    System.out.println("BALLON D'OR");
                }
            }
        } else;

        for (Award a : award) {
            if (year == a.getAwardYear()) {
                System.out.println("winner: " + a.getAwardWinner());
                System.out.println("runner-up: " + a.getAwardSecondPlace());
                System.out.println("third place: " + a.getAwardThirdPlace());
            }
        }
    }

    void showAchievementsByCountry(String country) {

        showTournamentAchievements(country, euro);
        showTournamentAchievements(country, worldCups);
        showTournamentAchievements(country, copaAmerica);
        showTournamentAchievements(country, championsLeague);
        showAwardsAchievements(country, ballonDOr);
    }

    void showTournamentAchievements(String country, List<Tournament> tournament) {

        if (tournament == euro) {
            for (Tournament t : tournament) {
                if (t.getGoldMedalist().contains(country) || t.getSilverMedalist().contains(country) || t.getBronzeMedalist().contains(country)) {
                    System.out.println("EURO:");
                    break;
                }
            }
        } else if (tournament == worldCups) {
            for (Tournament t : tournament) {
                if (t.getGoldMedalist().contains(country) || t.getSilverMedalist().contains(country) || t.getBronzeMedalist().contains(country)) {
                    System.out.println("WORLD CUP:");
                    break;
                }
            }
        } else if (tournament == copaAmerica) {
            for (Tournament t : tournament) {
                if (t.getGoldMedalist().contains(country) || t.getSilverMedalist().contains(country) || t.getBronzeMedalist().contains(country)) {
                    System.out.println("COPA AMERICA:");
                    break;
                }
            }
        } else if (tournament == championsLeague) {
            System.out.println("CHAMPIONS LEAGUE:");
        }

        for (Tournament t : tournament) {

            if (t.getGoldMedalist().contains(country)) {
                System.out.println(t.getYear() + ": winner");
            }
            if (t.getSilverMedalist().contains(country)) {
                System.out.println(t.getYear() + ": runner-up");
            }
            if (t.getBronzeMedalist().contains(country)) {
                System.out.println(t.getYear() + ": semi final");
            }
            if (t.getTopScorer().contains(country.toUpperCase().substring(0,3))) {
                System.out.println(t.getYear() + ": top scorer - " + t.getTopScorer());
            }
            if (t.getGoldMedalist().contains(country.toUpperCase().substring(0,3))) {
                System.out.println(t.getYear() + ": winner - " + t.getGoldMedalist());
            }
            if (t.getSilverMedalist().contains(country.toUpperCase().substring(0,3))) {
                System.out.println(t.getYear() + ": runner-up - " + t.getSilverMedalist());
            }
            if (t.getBronzeMedalist().contains(country.toUpperCase().substring(0,3))) {
                System.out.println(t.getYear() + ": semi final - " + t.getBronzeMedalist());
            }
        }

    }

    void showAwardsAchievements(String country, List<Award> awards) {

        if (awards == ballonDOr) {
            System.out.println("BALLON D'OR");
        }

        for (Award a : awards) {
            if (a.getAwardWinner().contains(country.toUpperCase().substring(0, 3))) {
                System.out.println(a.getAwardYear() + ": Ballon d'Or winner - " + a.getAwardWinner());
            }
            if (a.getAwardSecondPlace().contains(country.toUpperCase().substring(0, 3))) {
                System.out.println(a.getAwardYear() + ": Ballon d'Or runner up - " + a.getAwardSecondPlace());
            }
            if (a.getAwardThirdPlace().contains(country.toUpperCase().substring(0, 3))) {
                System.out.println(a.getAwardYear() + ": Ballon d'Or 3rd place - " + a.getAwardThirdPlace());
            }
        }
        for (Award a : awards) {
            if (a.getAwardWinnersClub().contains(country.toUpperCase().substring(0, 3))) {
                System.out.println(a.getAwardYear() + ": Ballon d'Or winner - " + a.getAwardWinner() + " (" + a.getAwardWinnersClub() + ")");
            }
            if (a.getAwardSecondPlacesClub().contains(country.toUpperCase().substring(0, 3))) {
                System.out.println(a.getAwardYear() + ": Ballon d'Or runner up - " + a.getAwardSecondPlace() + " (" + a.getAwardSecondPlacesClub() + ")");
            }
            if (a.getAwardThirdPlacesClub().contains(country.toUpperCase().substring(0, 3))) {
                System.out.println(a.getAwardYear() + ": Ballon d'Or 3rd place - " + a.getAwardThirdPlace() + " ( " + a.getAwardThirdPlacesClub() + ")");
            }
        }

    }

    void whoHasTheMostFinals(List<Tournament> tournament) {

        List<String> mostFinals = new ArrayList<>();

        for (Tournament t : tournament) {
            mostFinals.add(t.getGoldMedalist().concat(" "));
            mostFinals.add(t.getSilverMedalist().concat(" "));
        }

        updateCountriesNames(mostFinals);

    }

    void showBallonDOrWinner(List<Award> ballonDOr) {
        for (Award a : ballonDOr) {
            System.out.println(a.getAwardYear() + " : " + a.getAwardWinner() + ", " + a.getAwardWinnersClub());
        }
    }

    List<Tournament> euro = Arrays.asList(
            new Tournament(1960, "Russia", "Yugoslavia", "Czechoslovakia", "various", 2),
            new Tournament(1964, "Spain", "Russia", "Hungary", "various", 2),
            new Tournament(1968, "Italy", "Yugoslavia", "England", "Dragan Dzajić (YUG)", 2),
            new Tournament(1972, "West Germany", "Russia", "Belgium", "Gerd Mueller (GER)", 4),
            new Tournament(1976, "Czechoslovakia", "West Germany", "Netherlands", "Dieter Mueller (GER)", 4),
            new Tournament(1980, "Germany", "Belgium", "Czechoslovakia", "Klaus Allofs (GER)", 3),
            new Tournament(1984, "France", "Spain", "Denmark & Portugal", "Michel Platini (FRA)", 9),
            new Tournament(1988, "Netherlands", "Russia", "Germany & Italy", "Marco van Basten (NET)", 5),
            new Tournament(1992, "Denmark", "Germany", "Netherlands & Sweden", "various", 3),
            new Tournament(1996, "Germany", "Czech Republic", "France & England", "Alan Shearer (ENG)", 5),
            new Tournament(2000, "France", "Italy", "Netherlands & Portugal", "Patrick Kluivert (NET)", 5),
            new Tournament(2004, "Greece", "Portugal", "Netherlands & Czech Republic", "Milan Baros (CZE)", 5),
            new Tournament(2008, "Spain", "Germany", "Russia & Turkey", "David Villa (SPA)", 4),
            new Tournament(2012, "Spain", "Italy", "Portugal & Germany", "Fernando Torres (SPA)", 3),
            new Tournament(2016, "Portugal", "France", "Germany & Wales", "Antoine Griezmann (FRA)", 6),
            new Tournament(2020, "Italy", "England", "Spain & Denmark", "Cristiano Ronaldo (POR) & Patrik Schick (CZE)", 5));

    List<Tournament> worldCups = Arrays.asList(
            new Tournament(1930, "Uruguay", "Argentina", "USA", "Guillermo Stabile (ARG)", 8),
            new Tournament(1930, "Italy", "Czechoslovakia", "Germany", "Oldrich Nejedly (CZE)", 5),
            new Tournament(1938, "Italy", "Hungary", "Brazil", "Leonidas (BRA)", 7),
            new Tournament(1950, "Uruguay", "Brazil", "Sweden", "Ademir (BRA)", 8),
            new Tournament(1954, "West Germany", "Hungary", "Austria", "Sandor Kocsis (HUN)", 11),
            new Tournament(1958, "Brazil", "Sweden", "France", "Just Fontaine (FRA)", 13),
            new Tournament(1962, "Brazil", "Czechoslovakia", "Chile", "Garrincha (BRA)", 4),
            new Tournament(1966, "England", "Germany", "Portugal", "Eusebio (POR), Florian Albert (HUN), Valentin Ivanov (USR), Drazan Jerkovic (YUG), Leonel Sanchez (CHI) & Vava (BRA)", 9),
            new Tournament(1970, "Brazil", "Italy", "West Germany", "Gerd Muller (GER)", 10),
            new Tournament(1974, "West Germany", "Netherlands", "Poland", "Grzegorz Lato (POL)", 7),
            new Tournament(1978, "Argentina", "Netherlands", "Brazil", "Mario Kempes (ARG)", 6),
            new Tournament(1982, "Italy", "Germany", "Poland", "Paolo Rossi (ITA)", 6),
            new Tournament(1986, "Argentina", "Germany", "France", "Gary Lineker (ENG)", 6),
            new Tournament(1990, "Germany", "Argentina", "Italy", "Salvatore Schillaci (ITA)", 6),
            new Tournament(1994, "Brazil", "Italy", "Sweden", "Oleg Salenko (RUS)", 6),
            new Tournament(1998, "France", "Brazil", "Croatia", "Davor Suker (CRO)", 6),
            new Tournament(2002, "Brazil", "Germany", "Turkey", "Ronaldo (BRA)", 8),
            new Tournament(2006, "Italy", "France", "Germany", "Miroslav Klose (GER)", 5),
            new Tournament(2010, "Spain", "Netherlands", "Germany", "Thomas Muller (GER), Diego Forlan (URU), Wesley Sneijder (NET) & David Villa (SPA)", 5),
            new Tournament(2014, "Germany", "Argentina", "Netherlands", "James Rodriguez (COL)", 6),
            new Tournament(2018, "France", "Croatia", "Belgium", "Harry Kane (ENG)", 6)

    );

    List<Tournament> copaAmerica = Arrays.asList(
            new Tournament(1916, "Uruguay", "Argentina", "Brazil & Chile", "Isabelino Gradin (URU)", 3),
            new Tournament(1917, "Uruguay", "Argentina", "Brazil & Chile", "Angel Romano (URU)", 4),
            new Tournament(1919, "Brazil", "Uruguay", "Argentina & Chile", "Arthur Friedenreich (BRA) & Neco (BRA)", 4),
            new Tournament(1920, "Uruguay", "Argentina", "Brazil & Chile", "Jose Perez (URU) & Angel Romano (URU)", 3),
            new Tournament(1921, "Argentina", "Brazil", "Uruguay & Paraguay", "Julio Libonatti (ARG)", 3),
            new Tournament(1922, "Brazil", "Paraguay", "Uruguay & Argentina", "Julio Francia (ARG)", 4),
            new Tournament(1923, "Uruguay", "Argentina", "Paraguay & Brazil", "Vincent Aguirre (ARG) & Pedro Petrone (URU)", 0),
            new Tournament(1924, "Uruguay", "Argentina", "Paraguay & Chile", "Pedro Petrone (URU)", 4),
            new Tournament(1925, "Argentina", "Brazil", "Paraguay", "Manuel Seoane (ARG)", 4),
            new Tournament(1926, "Uruguay", "Argentina", "Chile & Paraguay", "David Arellano (CHI)", 7),
            new Tournament(1927, "Argentina", "Uruguay", "Peru & Bolivia", "Alfredo Carricabery (ARG), Segundo Luna (ARG), Roberto Figueroa (URU), Pedro Petrone (URU) & Hector Scarone (URU)", 3),
            new Tournament(1929, "Argentina", "Paraguay", "Uruguay & Peru", "Aurelio Gonzalez (PAR)", 5),
            new Tournament(1935, "Uruguay", "Argentina", "Peru & Chile", "Herminio Masantonio (ARG)", 4),
            new Tournament(1937, "Argentina", "Brazil", "Uruguay & Paraguay", "Raul Toro Julio (CHI)", 7),
            new Tournament(1939, "Peru", "Uruguay", "Paraguay & Chile", "Teodoro Fernandez (PER)", 7),
            new Tournament(1941, "Argentina", "Uruguay", "Chile & Peru", "Juan Marvezzi (ARG)", 5),
            new Tournament(1942, "Uruguay", "Argentina", "Brazil & Paraguay", "Herminio Masantonio (ARG) & Jose Manuel Moreno (ARG)", 7),
            new Tournament(1945, "Argentina", "Brazil", "Chile & Uruguay", "Norberto Mendez (ARG) & Heleno de Freitas (BRA)", 6),
            new Tournament(1946, "Argentina", "Brazil", "Paraguay & Uruguay", "Jose Maria Medina (ARG)", 7),
            new Tournament(1947, "Argentina", "Paraguay", "Uruguay & Chile", "Nicolas Falero (ARG)", 8),
            new Tournament(1949, "Brazil", "Paraguay", "Peru & Bolivia", "Jair (BRA)", 9),
            new Tournament(1953, "Paraguay", "Brazil", "Uruguay & Chile", "Francisco Molina (CHI)", 7),
            new Tournament(1955, "Argentina", "Chile", "Peru & Uruguay", "Rodolfo Micheli (ARG)", 8),
            new Tournament(1956, "Uruguay", "Chile", "Argentina & Brazil", "Enrique Hormazabal (CHI)", 4),
            new Tournament(1957, "Argentina", "Brazil", "Uruguay & Peru", "Humberto Maschio (ARG) & Javier Ambrois (URU)", 9),
            new Tournament(1959, "Argentina", "Brazil", "Paraguay & Peru", "Pele (BRA)", 8),
            new Tournament(1959, "Uruguay", "Argentina", "Brazil & Ecuador", "Jose Sanfilippo (ARG)", 6),
            new Tournament(1963, "Bolivia", "Paraguay", "Argentina & Brazil", "Carlos Alberto Raffo (ECU)", 6),
            new Tournament(1967, "Uruguay", "Argentina", "Chile & Paraguay", "Luis Artime (ARG)", 5),
            new Tournament(1975, "Peru", "Colombia", "Brazil & Uruguay", "Leopoldo Luque (ARG) & Ernesto Diaz (COL)", 4),
            new Tournament(1979, "Paraguay", "Chile", "Brazil & Peru", "Jorge Peredo (CHI) & Eugenio Morel (PAR)", 4),
            new Tournament(1983, "Uruguay", "Brazil", "Paraguay & Peru", "Jorge Burruchaga (ARG), Roberto Dinamite (BRA) & Carlos Aguilera (URU)", 3),
            new Tournament(1987, "Uruguay", "Chile", "Colombia", "Arnoldo Iguaran (COL)", 4),
            new Tournament(1989, "Brazil", "Uruguay", "Argentina", "Bebeto (BRA)", 6),
            new Tournament(1991, "Argentina", "Brazil", "Chile", "Gabriel Batistuta (ARG)", 6),
            new Tournament(1993, "Argentina", "Mexico", "Colombia", "Jose Luis Dolgetta (VEN)", 4),
            new Tournament(1995, "Uruguay", "Brazil", "Colombia", "Gabriel Batistuta (ARG) & Luis Garcia (MEX)", 4),
            new Tournament(1997, "Brazil", "Bolivia", "Mexico", "Luis Hernandez (MEX)", 6),
            new Tournament(1999, "Brazil", "Uruguay", "Mexico", "Rivaldo (BRA) & Ronaldo (BRA)", 5),
            new Tournament(2001, "Colombia", "Mexico", "Honduras", "Victor Aristizabal (COL)", 6),
            new Tournament(2004, "Brazil", "Argentina", "Uruguay", "Adriano (BRA)", 7),
            new Tournament(2007, "Brazil", "Argentina", "Mexico", "Robinho (BRA)", 6),
            new Tournament(2011, "Uruguay", "Paraguay", "Peru", "Paulo Guerrero (PER)", 5),
            new Tournament(2015, "Chile", "Argentina", "Peru", "Eduardo Vargas (CHI) & Paulo Guerrero (PER)", 4),
            new Tournament(2016, "Chile", "Argentina", "Colombia", "Eduardo Vargas (CHI)", 6),
            new Tournament(2019, "Brazil", "Peru", "Argentina", "Everton (BRA) & Paulo Guerrero (PER)", 3),
            new Tournament(2021, "Argentina", "Brazil", "Colombia", "Lionel Messi (ARG) & Luis Diaz (COL)", 4)

    );

    List<Tournament> championsLeague = Arrays.asList(
            new Tournament(1993, "Marseille (FRA)", "Milan (ITA)", "Rangers (SCO) & Goteborg (SWE)", "Franck Sauzee (FRA)", 5),
            new Tournament(1994, "Milan (ITA)", "Barcelona (SPA)", "Monaco (FRA) & Porto (POR)", "Ronald Koeman (NED) & Wynton Rufer (NZL)", 8),
            new Tournament(1995, "Ajax (NED)", "Milan (ITA)", "Bayern (GER) & PSG (FRA)", "George Weah (LBR)", 7),
            new Tournament(1996, "Juventus (ITA)", "Ajax (NED)", "Nantes (FRA) & Panathinaikos (GRE)", "Jari Litmanen (FIN)", 9),
            new Tournament(1997, "Borussia (GER)", "Juventus (ITA)", "ManU (ENG) & Ajax (NED)", "Milinko Pantic (YUG)", 5),
            new Tournament(1998, "Real (SPA)", "Juventus (ITA)", "Monaco (FRA) & Borussia (GER)", "Alessandro Del Piero (ITA)", 10),
            new Tournament(1999, "ManU (ENG)", "Bayern (GER)", "Juventus (ITA) & Dynamo (UKR)", "Andriy Shevchenko (UKR) & Dwight Yorke (TRI)", 7),
            new Tournament(2000, "Real (SPA)", "Valencia (SPA)", "Barcelona (SPA) & Bayern (GER)", "Mario Jardel (BRA), Rivaldo (BRA) & Raul (SPA)", 10),
            new Tournament(2001, "Bayern (GER)", "Valencia (SPA)", "Real (SPA) & Leeds (ENG)", "Raul (SPA)", 7),
            new Tournament(2002, "Real (SPA)", "Bayer (GER)", "ManU (ENG) & Barcelona (SPA)", "Ruud van Nistelrooy (NED)", 10),
            new Tournament(2003, "Milan (ITA)", "Juventus (ITA)", "Inter (ITA) & Real (SPA)", "Ruud van Nistelrooy (NED)", 12),
            new Tournament(2004, "Porto (POR)", "Monaco (FRA)", "Deportivo (SPA) & Chelsea (ENG)", "Fernando Morientes (SPA)", 9),
            new Tournament(2005, "Liverpool (ENG)", "Milan (ITA)", "PSV (NED) & Chelsea (ENG)", "Ruud van Nistelrooy (NED)", 8),
            new Tournament(2006, "Barcelona (SPA)", "Arsenal (ENG)", "Villarreal (SPA) & Milan (ITA)", "Andriy Shevchenko (UKR)", 9),
            new Tournament(2007, "Milan (ITA)", "Liverpool (ENG)", "Chelsea (ENG) & ManU (ENG)", "Kaka (BRA)", 10),
            new Tournament(2008, "ManU (ENG)", "Chelsea (ENG)", "Liverpool (ENG) & Barcelona (SPA)", "Cristiano Ronaldo (POR)", 8),
            new Tournament(2009, "Barcelona (SPA)", "ManU (ENG)", "Chelsea (ENG) & Arsenal (ENG)", "Leo Messi (ARG)", 9),
            new Tournament(2010, "Inter (ITA)", "Bayern (GER)", "Barcelona (SPA) & Lyon (FRA)", "Leo Messi (ARG)", 8),
            new Tournament(2011, "Barcelona (SPA)", "ManU (ENG)", "Real (SPA) & Schalke (GER)", "Leo Messi (ARG)", 12),
            new Tournament(2012, "Chelsea (ENG)", "Bayern (GER)", "Real (SPA) & Barcelona (SPA)", "Leo Messi (ARG)", 14),
            new Tournament(2013, "Bayern (GER)", "Borussia (GER)", "Real (SPA) & Barcelona (SPA)", "Cristiano Ronaldo (POR)", 12),
            new Tournament(2014, "Real (SPA)", "Atletico (SPA)", "Chelsea (ENG) & Bayern (GER)", "Cristiano Ronaldo (POR)", 17),
            new Tournament(2015, "Barcelona (SPA)", "Juventus (ITA)", "Real (SPA) & Bayern (GER)", "Cristiano Ronaldo (POR), Leo Messi (ARG), Neymar (BRA)", 10),
            new Tournament(2016, "Real (SPA)", "Atletico (SPA)", "Bayern (GER) & ManCity (ENG)", "Cristiano Ronaldo (POR)", 16),
            new Tournament(2017, "Real (SPA)", "Juventus (ITA)", "Atletico (SPA) & Monaco (FRA)", "Cristiano Ronaldo (POR)", 12),
            new Tournament(2018, "Real (SPA)", "Liverpool (ENG)", "Roma (ITA) & Bayern (GER)", "Cristiano Ronaldo (POR)", 15),
            new Tournament(2019, "Liverpool (ENG)", "Tottenham (ENG)", "Ajax (NED) & Barcelona (SPA)", "Leo Messi (ARG)", 12),
            new Tournament(2020, "Bayern (GER)", "PSG (FRA)", "Lyon (FRA) & Leipzig (GER)", "Robert Lewandowski (POL)", 15),
            new Tournament(2021, "Chelsea (ENG)", "ManCity (ENG)", "Real (SPA) & PSG (FRA)", "Erling Haaland (NOR)", 10)
    );

    List<Award> ballonDOr = Arrays.asList(
            new Award(1994, "Hristo Stoiczkow (BUL)", "Barcelona (SPA)", "Roberto Baggio (ITA)", "Juventus (ITA)", "Paolo Maldini (ITA)", "Milan (ITA)"),
            new Award(1995, "George Weah (LIB)", "PSG (FRA) & Milan (ITA)", "Jurgen Klinsmann (GER)", "Tottenham (ENG) & Bayern (GER)", "Jari Litmanen (FIN)", "Ajax (NET)"),
            new Award(1996, "Matthias Sammer (GER)", "Borussia (GER)", "Ronaldo (BRA)", "PSV (NET) & Barcelona (SPA)", "Alan Shearer (ENG)", "Blackburn (ENG) & Newcastle (ENG)"),
            new Award(1997, "Ronaldo (BRA)", "Barcelona (SPA) & Inter (ITA)", "Predrag Mijatovic (YUG)", "Real (SPA)", "Zinedine Zidane (FRA)", "Juventus (ITA)"),
            new Award(1998, "Zinedine Zidane (FRA)", "Juventus (ITA)", "Davor Suker (CRO)", "Real (SPA)", "Ronaldo (BRA)", "Inter (ITA)"),
            new Award(1999, "Rivaldo (BRA)", "Barcelona (SPA)", "David Beckham (ENG)", "ManU (ENG)", "Andriy Shevchenko (UKR)", "Dynamo (UKR) & Milan (ITA)"),
            new Award(2000, "Figo (POR)", "Barcelona (SPA) & Real (SPA)", "Zinedine Zidane (FRA)", "Juventus (ITA)", "Andriy Shevchenko (UKR)", "Milan (ITA)")
    );

}
