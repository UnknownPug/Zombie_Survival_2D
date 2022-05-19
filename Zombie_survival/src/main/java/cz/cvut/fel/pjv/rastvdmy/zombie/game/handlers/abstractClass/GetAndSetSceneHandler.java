package cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers.abstractClass;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Settings;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Obstacles;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Sprite;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.gui.GUIInfo;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers.EnemyHandler;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers.ShopHandler;

public abstract class GetAndSetSceneHandler {
    private Sprite sprite;
    private Settings settings;
    private EnemyHandler enemyHandler;
    private Obstacles obstacles;
    private ShopHandler shop;
    private GUIInfo indicator;

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public EnemyHandler getEnemyHandler() {
        return enemyHandler;
    }

    public void setEnemyHandler(EnemyHandler enemyHandler) {
        this.enemyHandler = enemyHandler;
    }

    public Obstacles getObstacles() {
        return obstacles;
    }

    public void setObstacles(Obstacles obstacles) {
        this.obstacles = obstacles;
    }

    public ShopHandler getShop() {
        return shop;
    }

    public void setShop(ShopHandler shop) {
        this.shop = shop;
    }

    public GUIInfo getIndicator() {
        return indicator;
    }

    public void setIndicator(GUIInfo indicator) {
        this.indicator = indicator;
    }
}
