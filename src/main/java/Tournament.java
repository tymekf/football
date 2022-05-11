public class Tournament {

    private int year;
    private String goldMedalist;
    private String silverMedalist;
    private String bronzeMedalist;
    private String topScorer;
    private int goalsScored;
    private String bestYoungPlayer;

    public Tournament(int year, String goldMedalist, String silverMedalist, String bronzeMedalist, String topScorer, int goalsScored, String bestYoungPlayer) {
        this.year = year;
        this.goldMedalist = goldMedalist;
        this.silverMedalist = silverMedalist;
        this.bronzeMedalist = bronzeMedalist;
        this.topScorer = topScorer;
        this.goalsScored = goalsScored;
        this.bestYoungPlayer = bestYoungPlayer;
    }

    public int getYear() {
        return year;
    }

    public String getGoldMedalist() {
        return goldMedalist;
    }

    public String getSilverMedalist() {
        return silverMedalist;
    }

    public String getBronzeMedalist() {
        return bronzeMedalist;
    }

    public String getTopScorer() {
        return topScorer;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public String getBestYoungPlayer() {
        return bestYoungPlayer;
    }

    public Tournament(int year, String goldMedalist, String silverMedalist, String bronzeMedalist, String topScorer, int goalsScored) {
        this.year = year;
        this.goldMedalist = goldMedalist;
        this.silverMedalist = silverMedalist;
        this.bronzeMedalist = bronzeMedalist;
        this.topScorer = topScorer;
        this.goalsScored = goalsScored;
    }




}
