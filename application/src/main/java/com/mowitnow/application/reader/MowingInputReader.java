package com.mowitnow.application.reader;


import com.mowitnow.application.configuration.ContextData;
import com.mowitnow.domain.move.Instruction;
import com.mowitnow.domain.mowing.MowerSetup;
import com.mowitnow.domain.mowing.MowingRequest;
import com.mowitnow.domain.position.Coordinates;
import com.mowitnow.domain.position.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MowingInputReader implements Tasklet, StepExecutionListener {

    private final LawnLineConverter lawnLineConverter;
    private final MowerPositionLineConvertor mowerPositionLineConvertor;
    private final CommandsLineConvertor commandsLineConvertor;

    @Value("#{jobParameters['inputFilePath']}") String inputFilePath;

    private List<MowerSetup> mowerInstructions = new ArrayList<>();
    private Coordinates boundaries;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        mowerInstructions = new ArrayList<>();
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            boundaries = lawnLineConverter.process(reader.readLine());

            String mowerFirstLine;
            while (null != (mowerFirstLine = reader.readLine())) {
                Position position = mowerPositionLineConvertor.process(mowerFirstLine);
                String secondLine = reader.readLine();
                List<Instruction> commands = commandsLineConvertor.process(secondLine);
                mowerInstructions.add(new MowerSetup(position, commands));
            }
        }

        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put(ContextData.MOWING_REQUEST, new MowingRequest(boundaries, mowerInstructions));
        return ExitStatus.COMPLETED;
    }
}

