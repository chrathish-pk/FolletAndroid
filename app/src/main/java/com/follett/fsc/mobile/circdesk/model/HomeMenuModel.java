package com.follett.fsc.mobile.circdesk.model;

public class HomeMenuModel {

    private String menuName;
    private int menuImg;

    public HomeMenuModel(String menuName, int menuImg) {
        this.menuName = menuName;
        this.menuImg = menuImg;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(int menuImg) {
        this.menuImg = menuImg;
    }
}
