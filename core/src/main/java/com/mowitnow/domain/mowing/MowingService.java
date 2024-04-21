package com.mowitnow.domain.mowing;

import com.mowitnow.domain.MowingPort;

import com.mowitnow.domain.position.Position;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MowingService implements MowingPort {
    @Override
    public List<Position> mow(MowingRequest request) {
        MowingSession session = new MowingSession(new Lawn(request.lawnBoundaries()));
        for (MowerSetup mowerDeployment : request.mowerInstructions()) {
            session.deploy(mowerDeployment);
        }

        session.start();
        return session.getPositions();

    }
}
