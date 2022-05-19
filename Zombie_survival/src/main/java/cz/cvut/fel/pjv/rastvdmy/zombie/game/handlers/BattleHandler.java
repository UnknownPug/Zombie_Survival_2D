package cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.animation.AnimationQueue;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Sprite;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.animation.AnimationView;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class BattleHandler {
    public static final Logger LOGGER = Logger.getGlobal();

    public BattleHandler() {
    }

    public static void launchAttack(Sprite attacker, Sprite defender,
                                    AnimationQueue queue) throws FileNotFoundException {
        if (attacker.getMovementHandler().getWalkView().isActive() ||
                attacker.getMovementHandler().getWalkView().getBaseType().equals("male")) {
            attacker.getMovementHandler().getWalkView().setActive(true);
            if (queue != null) {
                queue.reset();
            }
            AnimationView.stopAnimation(attacker.getMovementHandler().getWalkView());
            attacker.getMovementHandler().getWalkView().initView("Attack");
            int dir;
            if (attacker.getMovementHandler().getWalkView().getBaseType().equals("male")) {
                dir = MovementHandler.findDirection(defender.getMovementHandler().getDirX(),
                        defender.getMovementHandler().getDirY());
                dir = MovementHandler.reflectDirection(dir);
            } else {
                dir = MovementHandler.findDirection(attacker.getMovementHandler().getDirX(),
                        attacker.getMovementHandler().getDirY());
            }
            attacker.getMovementHandler().getWalkView().launchAnimation(dir, attacker.getMovementHandler().speed);
            int tempHp = defender.getHp() - attacker.dmg;
            defender.setHp(Math.max(tempHp, 0));
        }
        LOGGER.info("Player attack has been initialized\n");
    }
}