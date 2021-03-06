package com.jcloisterzone.game.phase;

import java.util.Random;

import com.jcloisterzone.config.Config;
import com.jcloisterzone.game.Capability;
import com.jcloisterzone.game.capability.AbbeyCapability;
import com.jcloisterzone.game.capability.BuilderCapability;
import com.jcloisterzone.game.capability.BuilderState;
import com.jcloisterzone.game.state.Flag;
import com.jcloisterzone.game.state.GameState;

/**
 *  End of turn part. For builder double repeat turn otherwise proceed to real end of turn.
 */
public class CleanUpTurnPartPhase extends Phase {

    public CleanUpTurnPartPhase(Config config, Random random) {
        super(config, random);
    }

    @Override
    public StepResult enter(GameState state) {
        BuilderState builderState = state.getCapabilityModel(BuilderCapability.class);
        boolean builderTakeAnotherTurn = builderState == BuilderState.USED;

        for (Capability<?> cap : state.getCapabilities().toSeq()) {
            state = cap.onTurnPartCleanUp(state);
        }

        if (!state.getFlags().isEmpty()) {
            state = state.setFlags(state.getFlags()
                .remove(Flag.PORTAL_USED)
                .remove(Flag.PRINCESS_USED)
            );
        }

        if (builderTakeAnotherTurn) {
            return next(state, state.getCapabilities().contains(AbbeyCapability.class) ? AbbeyPhase.class : TilePhase.class);
        } else {
            return next(state);
        }
    }
}
