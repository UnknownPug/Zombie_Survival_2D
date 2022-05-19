package cz.cvut.fel.pjv.rastvdmy.zombie.game.elements;

public abstract class GetAndSetSettings {

    //TODO: To change player configuration, go to resources->settings.xml and set needed config
    private int hpStart, moneyStart, dmgStart, speedStart, foodStart, waterStart;
    private int enemy1Hp, enemy2Hp, enemy3Hp;
    private int enemy1Dmg, enemy2Dmg, enemy3Dmg;
    private int zombie1Yield;
    private int supplyConsumptionTime;

    private String itemsPath, itemsConfig;
    private String bgPath, bgOverlayPath, shopBg2Path;
    private String obstaclesPath, quitDialoguePath, upperMenuPath;
    private String maleWalkSpritePath, maleAttackSpritePath, maleDieSpritePath;
    private String enemy1WalkSpritePath, enemy1AttackSpritePath, enemy1DieSpritePath;
    private String healthIconPath, foodIconPath, waterIconPath, speedIconPath, dmgIconPath, moneyIconPath;

    private int[] maleWalkCells, maleAttackCells, maleDieCells;
    private int[] enemy1WalkCells, enemy1AttackCells, enemy1DieCells;

    private Double agrRange, escapedRange;
    private Double frameTime;
    private Double agrSpeedModifier;
    private Double enemy1Speed, enemy2Speed, enemy3Speed;

    protected GetAndSetSettings() {
    }

    public int getHpStart() {
        return hpStart;
    }

    public void setHpStart(int hpStart) {
        this.hpStart = hpStart;
    }

    public int getMoneyStart() {
        return moneyStart;
    }

    public void setMoneyStart(int moneyStart) {
        this.moneyStart = moneyStart;
    }

    public int getDmgStart() {
        return dmgStart;
    }

    public void setDmgStart(int dmgStart) {
        this.dmgStart = dmgStart;
    }

    public int getSpeedStart() {
        return speedStart;
    }

    public void setSpeedStart(int speedStart) {
        this.speedStart = speedStart;
    }

    public int getFoodStart() {
        return foodStart;
    }

    public void setFoodStart(int foodStart) {
        this.foodStart = foodStart;
    }

    public int getWaterStart() {
        return waterStart;
    }

    public void setWaterStart(int waterStart) {
        this.waterStart = waterStart;
    }

    public int getZombie1Yield() {
        return zombie1Yield;
    }

    public void setZombie1Yield(int zombie1Yield) {
        this.zombie1Yield = zombie1Yield;
    }

    public int getEnemy1Hp() {
        return enemy1Hp;
    }

    public void setEnemy1Hp(int enemy1Hp) {
        this.enemy1Hp = enemy1Hp;
    }

    public int getEnemy2Hp() {
        return enemy2Hp;
    }

    public void setEnemy2Hp(int enemy2Hp) {
        this.enemy2Hp = enemy2Hp;
    }

    public int getEnemy3Hp() {
        return enemy3Hp;
    }

    public void setEnemy3Hp(int enemy3Hp) {
        this.enemy3Hp = enemy3Hp;
    }

    public int getEnemy1Dmg() {
        return enemy1Dmg;
    }

    public void setEnemy1Dmg(int enemy1Dmg) {
        this.enemy1Dmg = enemy1Dmg;
    }

    public int getEnemy2Dmg() {
        return enemy2Dmg;
    }

    public void setEnemy2Dmg(int enemy2Dmg) {
        this.enemy2Dmg = enemy2Dmg;
    }

    public int getEnemy3Dmg() {
        return enemy3Dmg;
    }

    public void setEnemy3Dmg(int enemy3Dmg) {
        this.enemy3Dmg = enemy3Dmg;
    }

    public int getSupplyConsumptionTime() {
        return supplyConsumptionTime;
    }

    public void setSupplyConsumptionTime(int supplyConsumptionTime) {
        this.supplyConsumptionTime = supplyConsumptionTime;
    }

    public String getBgOverlayPath() {
        return bgOverlayPath;
    }

    public void setBgOverlayPath(String bgOverlayPath) {
        this.bgOverlayPath = bgOverlayPath;
    }

    public String getBgPath() {
        return bgPath;
    }

    public void setBgPath(String bgPath) {
        this.bgPath = bgPath;
    }

    public String getMaleWalkSpritePath() {
        return maleWalkSpritePath;
    }

    public void setMaleWalkSpritePath(String maleWalkSpritePath) {
        this.maleWalkSpritePath = maleWalkSpritePath;
    }

    public String getObstaclesPath() {
        return obstaclesPath;
    }

    public void setObstaclesPath(String obstaclesPath) {
        this.obstaclesPath = obstaclesPath;
    }

    public String getQuitDialoguePath() {
        return quitDialoguePath;
    }

    public void setQuitDialoguePath(String quitDialoguePath) {
        this.quitDialoguePath = quitDialoguePath;
    }

    public String getEnemy1WalkSpritePath() {
        return enemy1WalkSpritePath;
    }

    public void setEnemy1WalkSpritePath(String enemy1WalkSpritePath) {
        this.enemy1WalkSpritePath = enemy1WalkSpritePath;
    }

    public String getItemsPath() {
        return itemsPath;
    }

    public void setItemsPath(String itemsPath) {
        this.itemsPath = itemsPath;
    }

    public String getItemsConfig() {
        return itemsConfig;
    }

    public void setItemsConfig(String itemsConfig) {
        this.itemsConfig = itemsConfig;
    }

    public String getMaleAttackSpritePath() {
        return maleAttackSpritePath;
    }

    public void setMaleAttackSpritePath(String maleAttackSpritePath) {
        this.maleAttackSpritePath = maleAttackSpritePath;
    }

    public String getEnemy1AttackSpritePath() {
        return enemy1AttackSpritePath;
    }

    public void setEnemy1AttackSpritePath(String enemy1AttackSpritePath) {
        this.enemy1AttackSpritePath = enemy1AttackSpritePath;
    }

    public String getMaleDieSpritePath() {
        return maleDieSpritePath;
    }

    public void setMaleDieSpritePath(String maleDieSpritePath) {
        this.maleDieSpritePath = maleDieSpritePath;
    }

    public String getEnemy1DieSpritePath() {
        return enemy1DieSpritePath;
    }

    public void setEnemy1DieSpritePath(String enemy1DieSpritePath) {
        this.enemy1DieSpritePath = enemy1DieSpritePath;
    }

    public String getUpperMenuPath() {
        return upperMenuPath;
    }

    public void setUpperMenuPath(String upperMenuPath) {
        this.upperMenuPath = upperMenuPath;
    }

    public String getHealthIconPath() {
        return healthIconPath;
    }

    public void setHealthIconPath(String healthIconPath) {
        this.healthIconPath = healthIconPath;
    }

    public String getFoodIconPath() {
        return foodIconPath;
    }

    public void setFoodIconPath(String foodIconPath) {
        this.foodIconPath = foodIconPath;
    }

    public String getWaterIconPath() {
        return waterIconPath;
    }

    public void setWaterIconPath(String waterIconPath) {
        this.waterIconPath = waterIconPath;
    }

    public String getSpeedIconPath() {
        return speedIconPath;
    }

    public void setSpeedIconPath(String speedIconPath) {
        this.speedIconPath = speedIconPath;
    }

    public String getDmgIconPath() {
        return dmgIconPath;
    }

    public void setDmgIconPath(String dmgIconPath) {
        this.dmgIconPath = dmgIconPath;
    }

    public String getMoneyIconPath() {
        return moneyIconPath;
    }

    public void setMoneyIconPath(String moneyIconPath) {
        this.moneyIconPath = moneyIconPath;
    }

    public String getShopBg2Path() {
        return shopBg2Path;
    }

    public void setShopBg2Path(String shopBg2Path) {
        this.shopBg2Path = shopBg2Path;
    }

    public int[] getMaleWalkCells() {
        return maleWalkCells;
    }

    public void setMaleWalkCells(int[] maleWalkCells) {
        this.maleWalkCells = maleWalkCells;
    }

    public int[] getEnemy1WalkCells() {
        return enemy1WalkCells;
    }

    public void setEnemy1WalkCells(int[] enemy1WalkCells) {
        this.enemy1WalkCells = enemy1WalkCells;
    }

    public int[] getMaleAttackCells() {
        return maleAttackCells;
    }

    public void setMaleAttackCells(int[] maleAttackCells) {
        this.maleAttackCells = maleAttackCells;
    }

    public int[] getEnemy1AttackCells() {
        return enemy1AttackCells;
    }

    public void setEnemy1AttackCells(int[] enemy1AttackCells) {
        this.enemy1AttackCells = enemy1AttackCells;
    }

    public int[] getMaleDieCells() {
        return maleDieCells;
    }

    public void setMaleDieCells(int[] maleDieCells) {
        this.maleDieCells = maleDieCells;
    }

    public int[] getEnemy1DieCells() {
        return enemy1DieCells;
    }

    public void setEnemy1DieCells(int[] enemy1DieCells) {
        this.enemy1DieCells = enemy1DieCells;
    }

    public Double getAgrRange() {
        return agrRange;
    }

    public void setAgrRange(Double agrRange) {
        this.agrRange = agrRange;
    }

    public Double getEscapedRange() {
        return escapedRange;
    }

    public void setEscapedRange(Double escapedRange) {
        this.escapedRange = escapedRange;
    }

    public Double getFrameTime() {
        return frameTime;
    }

    public void setFrameTime(Double frameTime) {
        this.frameTime = frameTime;
    }

    public Double getAgrSpeedModifier() {
        return agrSpeedModifier;
    }

    public void setAgrSpeedModifier(Double agrSpeedModifier) {
        this.agrSpeedModifier = agrSpeedModifier;
    }

    public Double getEnemy1Speed() {
        return enemy1Speed;
    }

    public void setEnemy1Speed(Double enemy1Speed) {
        this.enemy1Speed = enemy1Speed;
    }

    public Double getEnemy2Speed() {
        return enemy2Speed;
    }

    public void setEnemy2Speed(Double enemy2Speed) {
        this.enemy2Speed = enemy2Speed;
    }

    public Double getEnemy3Speed() {
        return enemy3Speed;
    }

    public void setEnemy3Speed(Double enemy3Speed) {
        this.enemy3Speed = enemy3Speed;
    }
}
