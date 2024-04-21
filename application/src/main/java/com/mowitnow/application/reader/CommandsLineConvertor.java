package com.mowitnow.application.reader;

import com.mowitnow.domain.move.Instruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandsLineConvertor extends InputLineConverter<List<Instruction>> {

    private final String validationRegex = "([" +
            Arrays.stream(Instruction.values())
                    .map(x -> String.valueOf(x.getCode()))
                    .collect(Collectors.joining()) +
            "]+)";

    @Override
    protected boolean isValid(String line) {
        return line.matches(this.validationRegex);
    }

    @Override
    public List<Instruction> convert(String line) {
        List<Instruction> instructions = new ArrayList<>();
        for (char c : line.toCharArray()) {
            instructions.add(Instruction.getByCode(c));
        }

        return instructions;

    }
}