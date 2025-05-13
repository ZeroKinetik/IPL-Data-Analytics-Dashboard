package io.zerokinetik.ipldataanalyticsdashboard.data;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import io.zerokinetik.ipldataanalyticsdashboard.model.Match;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final String[] INPUT_FIELDS = new String[] {
            "id", "season", "city", "date", "match_type", "player_of_match", "venue", "team1", "team2", "toss_winner",
            "toss_decision", "winner", "result", "result_margin", "target_runs", "target_overs", "super_over", "method",
            "umpire1", "umpire2"
    };

    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>()
                .name("MatchItemReader")
                .resource(new ClassPathResource("matches-dataset.csv"))
                .linesToSkip(1)
                .delimited()
                .names(INPUT_FIELDS)
                .targetType(MatchInput.class)
                .build();
    }

    @Bean
    public MatchInputDataProcessor processor() {
        return new MatchInputDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .sql("INSERT INTO match (id, season, city, date, match_type, player_of_match, venue, team1, team2, toss_winner, toss_decision, winner, result, result_margin, target_runs, target_overs, super_over, umpire1, umpire2) "
                        + " VALUES (:id, :season, :city, :date, :matchType, :playerOfMatch, :venue, :team1, :team2, :tossWinner, :tossDecision, :winner, :result, :resultMargin, :targetRuns, :targetOvers, :superOver, :umpire1, :umpire2)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
            FlatFileItemReader<MatchInput> reader, MatchInputDataProcessor processor, JdbcBatchItemWriter<Match> writer) {
        return new StepBuilder("step1", jobRepository)
                .<MatchInput, Match>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
