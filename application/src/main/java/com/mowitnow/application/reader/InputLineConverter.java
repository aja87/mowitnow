package com.mowitnow.application.reader;

public abstract class InputLineConverter<T> {

    protected  abstract boolean isValid(String line);
    public abstract T convert(String line);
     T process(String line) {
         if(!isValid(line)) throw new InvalidLineException(line);

         return convert(line);
     }
}
