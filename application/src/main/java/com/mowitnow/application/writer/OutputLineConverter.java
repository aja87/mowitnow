package com.mowitnow.application.writer;


import com.mowitnow.domain.position.Position;
public class OutputLineConverter {

    public String convert(Position position) {
       return  String.join(" ",
               String.valueOf(position.coordinates().x()),
               String.valueOf(position.coordinates().y()),
               String.valueOf(position.orientation().getCode()));

    }
}
