package io.zerokinetik.ipldataanalyticsdashboard.model;

import java.time.LocalDate;
import java.time.YearMonth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Match {
    
    @Id
    private long id;
    private YearMonth season;
    private String city;
    private LocalDate date;
    private String matchType;
    private String playerOfMatch;
    private String venue;
    private String team1;
    private String team2;
    private String tossWinner;
    private String tossDecision;
    private String winner;
    private String result;
    private int resultMargin;
    private int targetRuns;
    private int targetOvers;
    private String superOver;
    private String umpire1;
    private String umpire2;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public YearMonth getSeason() {
        return season;
    }
    public void setSeason(YearMonth season) {
        this.season = season;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getMatchType() {
        return matchType;
    }
    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }
    public String getPlayerOfMatch() {
        return playerOfMatch;
    }
    public void setPlayerOfMatch(String playerOfMatch) {
        this.playerOfMatch = playerOfMatch;
    }
    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public String getTeam1() {
        return team1;
    }
    public void setTeam1(String team1) {
        this.team1 = team1;
    }
    public String getTeam2() {
        return team2;
    }
    public void setTeam2(String team2) {
        this.team2 = team2;
    }
    public String getTossWinner() {
        return tossWinner;
    }
    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }
    public String getTossDecision() {
        return tossDecision;
    }
    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }
    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public int getResultMargin() {
        return resultMargin;
    }
    public void setResultMargin(int resultMargin) {
        this.resultMargin = resultMargin;
    }
    public int getTargetRuns() {
        return targetRuns;
    }
    public void setTargetRuns(int targetRuns) {
        this.targetRuns = targetRuns;
    }
    public int getTargetOvers() {
        return targetOvers;
    }
    public void setTargetOvers(int targetOvers) {
        this.targetOvers = targetOvers;
    }
    public String getSuperOver() {
        return superOver;
    }
    public void setSuperOver(String superOver) {
        this.superOver = superOver;
    }
    public String getUmpire1() {
        return umpire1;
    }
    public void setUmpire1(String umpire1) {
        this.umpire1 = umpire1;
    }
    public String getUmpire2() {
        return umpire2;
    }
    public void setUmpire2(String umpire2) {
        this.umpire2 = umpire2;
    }
}
