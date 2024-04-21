package com.mowitnow.domain;

import com.mowitnow.domain.mowing.MowingRequest;
import com.mowitnow.domain.position.Position;

import java.util.List;

public interface MowingPort {

   List<Position> mow(MowingRequest request);

}
