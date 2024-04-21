package com.mowitnow.domain.mowing;


import com.mowitnow.domain.position.Coordinates;

import java.io.Serializable;
import java.util.List;

public record MowingRequest(Coordinates lawnBoundaries, List<MowerSetup> mowerInstructions) implements Serializable {
}
