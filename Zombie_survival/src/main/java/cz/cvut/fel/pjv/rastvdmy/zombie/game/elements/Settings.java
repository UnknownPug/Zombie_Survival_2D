package cz.cvut.fel.pjv.rastvdmy.zombie.game.elements;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.launch.InitScreen;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

public class Settings extends GetAndSetSettings {
    public static final Logger LOGGER = Logger.getGlobal();
    public static final String XML_SETTINGS_PATH = "/gameFiles/settings.xml";
    public static final int ITEM_COMMON_INDEX = 0;

    private final InitScreen initScreen;

    public InitScreen getInitScreen() {
        return initScreen;
    }

    public Settings() {
        loadSettings();
        System.out.println("Settings have been loaded\n");
        initScreen = new InitScreen();
        System.out.println("Screen info was initialized\n");
    }

    private void loadSettings() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(Objects.requireNonNull(
                    this.getClass().getResource(XML_SETTINGS_PATH)).toExternalForm());

            Node playerStart = document.getElementsByTagName("player_start").item(ITEM_COMMON_INDEX);
            NodeList nodes = playerStart.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                launchUserInfo(nodes, i);
            }

            Node enemies = document.getElementsByTagName("enemies").item(ITEM_COMMON_INDEX);
            nodes = enemies.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                launchEnemyInfo(nodes, i);
            }


            Node paths = document.getElementsByTagName("paths").item(ITEM_COMMON_INDEX);
            nodes = paths.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node element = nodes.item(i);
                launchMapSystem(element);
                launchWalkSystem(element);
                launchAttackSystem(element);
                launchDieSystem(element);
                launchItemSystem(element);
                launchIconSystem(element);
            }

            Node world = document.getElementsByTagName("World").item(ITEM_COMMON_INDEX);
            nodes = world.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node element = nodes.item(i);
                launchWorld(element);
            }

            Node animation = document.getElementsByTagName("Animation").item(ITEM_COMMON_INDEX);
            nodes = animation.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node element = nodes.item(i);
                charactersWalkCells(element);
                characterAttackCells(element);
                characterDieCells(element);
            }
        } catch (ParserConfigurationException | IOException | SAXException pce) {
            pce.printStackTrace();
        }
        LOGGER.info("Settings has been loaded successfully\n");
    }

    void launchUserInfo(NodeList nodes, int item) {
        Node element = nodes.item(item);
        switch (element.getNodeName()) {
            case "hp" -> setHpStart(Integer.parseInt(element.getTextContent()));
            case "gold" -> setMoneyStart(Integer.parseInt(element.getTextContent()));
            case "speed" -> setSpeedStart(Integer.parseInt(element.getTextContent()));
            case "dmg" -> setDmgStart(Integer.parseInt(element.getTextContent()));
            case "water" -> setWaterStart(Integer.parseInt(element.getTextContent()));
            case "food" -> setFoodStart(Integer.parseInt(element.getTextContent()));
        }
    }

    private void launchEnemyInfo(NodeList nodes, int item) {
        Node element = nodes.item(item);
        switch (element.getNodeName()) {
            case "enemy1_hp" -> setEnemy1Hp(Integer.parseInt(element.getTextContent()));
            case "enemy2_hp" -> setEnemy2Hp(Integer.parseInt(element.getTextContent()));
            case "enemy3_hp" -> setEnemy3Hp(Integer.parseInt(element.getTextContent()));
            case "enemy1_dmg" -> setEnemy1Dmg(Integer.parseInt(element.getTextContent()));
            case "enemy2_dmg" -> setEnemy2Dmg(Integer.parseInt(element.getTextContent()));
            case "enemy3_dmg" -> setEnemy3Dmg(Integer.parseInt(element.getTextContent()));
            case "enemy1_speed" -> setEnemy1Speed(Double.parseDouble(element.getTextContent()));
            case "enemy2_speed" -> setEnemy2Speed(Double.parseDouble(element.getTextContent()));
            case "enemy3_speed" -> setEnemy3Speed(Double.parseDouble(element.getTextContent()));
        }
    }

    private void launchMapSystem(Node element) {
        switch (element.getNodeName()) {
            case "bg" -> setBgPath(element.getTextContent());
            case "bg_overlay" -> setBgOverlayPath(element.getTextContent());
            case "obstacles" -> setObstaclesPath(element.getTextContent());
            case "quid_dialogue" -> setQuitDialoguePath(element.getTextContent());
        }
    }

    private void launchWalkSystem(Node element) {
        switch (element.getNodeName()) {
            case "player_male_walk" -> setMaleWalkSpritePath(element.getTextContent());
            case "enemy1_walk" -> setEnemy1WalkSpritePath(element.getTextContent());
        }
    }

    private void launchAttackSystem(Node element) {
        switch (element.getNodeName()) {
            case "player_male_attack" -> setMaleAttackSpritePath(element.getTextContent());
            case "enemy1_attack" -> setEnemy1AttackSpritePath(element.getTextContent());
        }
    }

    private void launchDieSystem(Node element) {
        switch (element.getNodeName()) {
            case "player_male_die" -> setMaleDieSpritePath(element.getTextContent());
            case "enemy1_die" -> setEnemy1DieSpritePath(element.getTextContent());
        }
    }

    private void launchItemSystem(Node element) {
        switch (element.getNodeName()) {
            case "items_config" -> setItemsConfig(element.getTextContent());
            case "items_path" -> setItemsPath(element.getTextContent());
            case "shop_bg2" -> setShopBg2Path(element.getTextContent());
            case "upper_menu" -> setUpperMenuPath(element.getTextContent());
        }
    }

    private void launchIconSystem(Node element) {
        switch (element.getNodeName()) {
            case "health_icon" -> setHealthIconPath(element.getTextContent());
            case "money_icon" -> setMoneyIconPath(element.getTextContent());
            case "food_icon" -> setFoodIconPath(element.getTextContent());
            case "water_icon" -> setWaterIconPath(element.getTextContent());
            case "dmg_icon" -> setDmgIconPath(element.getTextContent());
            case "speed_icon" -> setSpeedIconPath(element.getTextContent());
        }
    }

    private void launchWorld(Node element) {
        switch (element.getNodeName()) {
            case "agr_range" -> setAgrRange(Double.parseDouble(element.getTextContent()));
            case "escaped_range" -> setEscapedRange(Double.parseDouble(element.getTextContent()));
            case "agr_speed_modifier" -> setAgrSpeedModifier(Double.parseDouble(element.getTextContent()));
            case "zombie1_yield" -> setZombie1Yield(Integer.parseInt(element.getTextContent()));
            case "supply_time" -> setSupplyConsumptionTime(Integer.parseInt(element.getTextContent()));
        }
    }

    private void charactersWalkCells(Node element) {
        switch (element.getNodeName()) {
            case "player_male_walk_cells" -> {
                String[] arr = element.getTextContent().split(" ");
                setMaleWalkCells(Arrays.stream(arr).mapToInt(Integer::parseInt).toArray());
            }
            case "enemy1_walk_cells" -> {
                String[] arr = element.getTextContent().split(" ");
                setEnemy1WalkCells(Arrays.stream(arr).mapToInt(Integer::parseInt).toArray());
            }
        }
        if ("frame_time".equals(element.getNodeName())) {
            setFrameTime(Double.parseDouble(element.getTextContent()));
        }
    }

    private void characterAttackCells(Node element) {
        switch (element.getNodeName()) {
            case "player_male_attack_cells" -> {
                String[] arr = element.getTextContent().split(" ");
                setMaleAttackCells(Arrays.stream(arr).mapToInt(Integer::parseInt).toArray());
            }
            case "enemy1_attack_cells" -> {
                String[] arr = element.getTextContent().split(" ");
                setEnemy1AttackCells(Arrays.stream(arr).mapToInt(Integer::parseInt).toArray());
            }
        }
    }

    private void characterDieCells(Node element) {
        switch (element.getNodeName()) {
            case "player_male_die_cells" -> {
                String[] arr = element.getTextContent().split(" ");
                setMaleDieCells(Arrays.stream(arr).mapToInt(Integer::parseInt).toArray());
            }
            case "enemy1_die_cells" -> {
                String[] arr = element.getTextContent().split(" ");
                setEnemy1DieCells(Arrays.stream(arr).mapToInt(Integer::parseInt).toArray());
            }
        }
    }
}