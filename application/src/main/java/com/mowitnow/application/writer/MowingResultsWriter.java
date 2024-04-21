package com.mowitnow.application.writer;

import com.mowitnow.application.configuration.ContextData;
import com.mowitnow.domain.position.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.util.FileUtils;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class MowingResultsWriter implements Tasklet, StepExecutionListener {

    private List<Position> result;
    private final OutputLineConverter outputLineConverter;

    @Value("#{jobParameters['resultDestination']}") String resultDestination;
    private FileUtils fileUtils;
    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.result = (List<Position>) executionContext.get(ContextData.MOWING_RESULT);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultDestination.concat(UUID.randomUUID().toString())))) {
            for (Position position: result ) {
                writer.write(outputLineConverter.convert(position));
                writer.newLine();
            }
        }

        return RepeatStatus.FINISHED;
    }
}
