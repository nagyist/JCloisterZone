package com.jcloisterzone.game.phase;

import java.util.Random;

import com.jcloisterzone.config.Config;
import com.jcloisterzone.game.state.GameState;
import com.jcloisterzone.reducers.FinalScoring;


public class GameOverPhase extends Phase {

    public GameOverPhase(Config config, Random random) {
        super(config, random);
    }

    @Override
    public StepResult enter(GameState state) {
        state = state.setPlayerActions(null);
        state = (new FinalScoring()).apply(state);
        return promote(state);
    }
}
