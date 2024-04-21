package com.mowitnow.application.reader;

import com.mowitnow.domain.position.Coordinates;
public class LawnLineConverter extends InputLineConverter<Coordinates> {


    @Override
    protected boolean isValid(String line) {
        return line.matches("(\\d+) (\\d+)");
    }

    @Override
    public Coordinates convert(String line) {
        String[] pos = line.split(" ");
        return new Coordinates(Integer.valueOf(pos[0]), Integer.valueOf(pos[1]));
    }
}
