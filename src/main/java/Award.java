public class Award{

    private int awardYear;
    private String awardWinner;
    private String awardWinnersClub;
    private String awardSecondPlace;
    private String awardSecondPlacesClub;
    private String awardThirdPlace;
    private String awardThirdPlacesClub;

    public Award(int awardYear, String awardWinner, String awardWinnersClub, String awardSecondPlace, String awardSecondPlacesClub, String awardThirdPlace, String awardThirdPlacesClub) {
        this.awardYear = awardYear;
        this.awardWinner = awardWinner;
        this.awardWinnersClub = awardWinnersClub;
        this.awardSecondPlace = awardSecondPlace;
        this.awardSecondPlacesClub = awardSecondPlacesClub;
        this.awardThirdPlace = awardThirdPlace;
        this.awardThirdPlacesClub = awardThirdPlacesClub;
    }

    public int getAwardYear() {
        return awardYear;
    }

    public void setAwardYear(int awardYear) {
        this.awardYear = awardYear;
    }

    public String getAwardWinner() {
        return awardWinner;
    }

    public void setAwardWinner(String awardWinner) {
        this.awardWinner = awardWinner;
    }

    public String getAwardWinnersClub() {
        return awardWinnersClub;
    }

    public void setAwardWinnersClub(String awardWinnersClub) {
        this.awardWinnersClub = awardWinnersClub;
    }

    public String getAwardSecondPlace() {
        return awardSecondPlace;
    }

    public void setAwardSecondPlace(String awardSecondPlace) {
        this.awardSecondPlace = awardSecondPlace;
    }

    public String getAwardSecondPlacesClub() {
        return awardSecondPlacesClub;
    }

    public void setAwardSecondPlacesClub(String awardSecondPlacesClub) {
        this.awardSecondPlacesClub = awardSecondPlacesClub;
    }

    public String getAwardThirdPlace() {
        return awardThirdPlace;
    }

    public void setAwardThirdPlace(String awardThirdPlace) {
        this.awardThirdPlace = awardThirdPlace;
    }

    public String getAwardThirdPlacesClub() {
        return awardThirdPlacesClub;
    }

    public void setAwardThirdPlacesClub(String awardThirdPlacesClub) {
        this.awardThirdPlacesClub = awardThirdPlacesClub;
    }
}
