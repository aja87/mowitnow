package com.mowitnow.domain.position;


import java.io.Serializable;

public record Position(Coordinates coordinates, Orientation orientation) implements Serializable {

}
