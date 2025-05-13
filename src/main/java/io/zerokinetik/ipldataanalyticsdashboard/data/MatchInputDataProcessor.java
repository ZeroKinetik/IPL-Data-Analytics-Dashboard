package io.zerokinetik.ipldataanalyticsdashboard.data;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.zip.DataFormatException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import io.zerokinetik.ipldataanalyticsdashboard.model.Match;

public class MatchInputDataProcessor implements ItemProcessor<MatchInput, Match> {
    private static final Logger log = LoggerFactory.getLogger(Match.class);

  @Override
  public Match process(final MatchInput matchInput) {
    Match match = new Match();

    match.setId(Long.parseLong(matchInput.getId()));

    DateTimeFormatter seasonFormatter =  DateTimeFormatter.ofPattern("yyyy/mm");
    YearMonth seasonParsed = YearMonth.parse(matchInput.getSeason(), seasonFormatter);
    match.setSeason(seasonParsed);

    match.setCity(matchInput.getCity());

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateParsed = LocalDate.parse(matchInput.getDate(), dateFormatter);
    match.setDate(dateParsed);

    match.setMatchType(matchInput.getMatch_type());
    match.setPlayerOfMatch(matchInput.getPlayer_of_match());
    match.setVenue(matchInput.getVenue());

    String firstInningsTeam, secondInningsTeam;
    if (matchInput.getToss_decision().equals("bat")) {
        firstInningsTeam = matchInput.getToss_winner();
        secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) 
        ? matchInput.getTeam2() 
        : matchInput.getTeam1();
    } else {
        secondInningsTeam = matchInput.getToss_winner();
        firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) 
        ? matchInput.getTeam2() 
        : matchInput.getTeam1();
    }

    match.setTeam1(firstInningsTeam);
    match.setTeam2(secondInningsTeam);
    match.setTossWinner(matchInput.getToss_winner());
    match.setTossDecision(matchInput.getToss_decision());
    match.setWinner(matchInput.getWinner());
    match.setResult(matchInput.getResult());

    match.setResultMargin(Integer.parseInt(matchInput.getResult_margin()));
    match.setTargetRuns(Integer.parseInt(matchInput.getTarget_runs()));
    match.setTargetOvers(Integer.parseInt(matchInput.getTarget_overs()));

    match.setSuperOver(matchInput.getSuper_over());
    match.setUmpire1(matchInput.getUmpire1());
    match.setUmpire2(matchInput.getUmpire2());

    return match;
  }

}
