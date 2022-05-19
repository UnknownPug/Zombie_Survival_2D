package cz.cvut.fel.pjv.rastvdmy.zombie.game.item;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.animation.AnimationView;

public abstract class GetAndSetItemInfo {
    public String stats;

    private String name, desc;
    private int price;
    private int dmg;
    private int hp;
    private int hpRegen;
    private int foodRegen;
    private int waterRegen;
    private int id;
    private double speed;
    private boolean isUsed;
    private AnimationView icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHpRegen() {
        return hpRegen;
    }

    public void setHpRegen(int hpRegen) {
        this.hpRegen = hpRegen;
    }

    public int getFoodRegen() {
        return foodRegen;
    }

    public void setFoodRegen(int foodRegen) {
        this.foodRegen = foodRegen;
    }

    public int getWaterRegen() {
        return waterRegen;
    }

    public void setWaterRegen(int waterRegen) {
        this.waterRegen = waterRegen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public AnimationView getIcon() {
        return icon;
    }

    public void setIcon(AnimationView icon) {
        this.icon = icon;
    }

}
