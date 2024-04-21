package com.mowitnow.application.configuration;

import com.mowitnow.application.processor.MowingInstructionsProcessor;
import com.mowitnow.application.reader.CommandsLineConvertor;
import com.mowitnow.application.reader.LawnLineConverter;
import com.mowitnow.application.reader.MowerPositionLineConvertor;
import com.mowitnow.application.reader.MowingInputReader;
import com.mowitnow.application.writer.MowingResultsWriter;
import com.mowitnow.application.writer.OutputLineConverter;
import com.mowitnow.domain.mowing.MowingService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("mowingAutomationJob", jobRepository)
                .start(readInstructions(jobRepository, transactionManager))
                .next(processInstructions(jobRepository, transactionManager))
                .next(writeResults(jobRepository, transactionManager))
                .build();
    }

    @Bean
    protected Step readInstructions(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("readInstructions", jobRepository)
                .tasklet(mowingInputReader(), transactionManager)
                .build();
    }

    @Bean
    protected Step processInstructions(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("processInstructions", jobRepository)
                .tasklet(mowingInstructionsProcessor(), transactionManager)
                .build();
    }

    @Bean
    protected Step writeResults(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("writeResults", jobRepository)
                .tasklet(mowingResultsWriter(), transactionManager)
                .build();
    }


    @Bean
    @StepScope
    protected MowingInputReader mowingInputReader() {
        return new MowingInputReader(new LawnLineConverter(), new MowerPositionLineConvertor(), new CommandsLineConvertor());
    }

    @Bean
    @StepScope
    protected MowingInstructionsProcessor mowingInstructionsProcessor() {
        return new MowingInstructionsProcessor(new MowingService());
    }

    @Bean
    @StepScope
    protected MowingResultsWriter mowingResultsWriter() {
        return new MowingResultsWriter(new OutputLineConverter());
    }


    @Bean(name = "transactionManager")
    public PlatformTransactionManager platformTransactionManager() {
        return new ResourcelessTransactionManager();
    }
}
