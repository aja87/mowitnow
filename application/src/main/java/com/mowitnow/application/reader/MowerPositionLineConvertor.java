package com.mowitnow.application.reader;

import com.mowitnow.domain.position.Coordinates;
import com.mowitnow.domain.position.Orientation;
import com.mowitnow.domain.position.Position;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MowerPositionLineConvertor extends InputLineConverter<Position> {

    private final String validationRegex = "^(\\d) (\\d) (" +
            Arrays.stream(Orientation.values())
                    .map(x -> String.valueOf(x.getCode()))
                    .collect(Collectors.joining("|")) +
            ")$";

    @Override
    protected boolean isValid(String line) {
        return line.matches(this.validationRegex);
    }

    @Override
    public Position convert(String line) {
        char[] textAsArray = line.toCharArray();

        Orientation orientation = Orientation.getByCode(textAsArray[4]);
        int x = Character.getNumericValue(textAsArray[0]);
        int y = Character.getNumericValue(textAsArray[2]);
        return new Position(new Coordinates(x, y), orientation);
    }
}
