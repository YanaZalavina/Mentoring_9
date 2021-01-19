package service;

import java.util.ResourceBundle;

public class TestNodesSelector {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("node"));

    public static String getTestNode(String key){
        return resourceBundle.getString(key);
    }
}
