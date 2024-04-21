package com.mowitnow.application.processor;

import com.mowitnow.application.configuration.ContextData;
import com.mowitnow.domain.MowingPort;
import com.mowitnow.domain.mowing.MowingRequest;
import com.mowitnow.domain.position.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;

@RequiredArgsConstructor
public class MowingInstructionsProcessor  implements Tasklet, StepExecutionListener {

    private final MowingPort  mowingService;
    private MowingRequest request;

    private List<Position> result;
    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.request = (MowingRequest) executionContext.get(ContextData.MOWING_REQUEST);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        this.result = mowingService.mow(this.request);
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put(ContextData.MOWING_RESULT, result);
        return ExitStatus.COMPLETED;
    }
}
