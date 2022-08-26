package com.kaiserdavar.androidui;

public class Menu {

    private android.view.Menu mMenu;

    public Menu(android.view.Menu menu) {
        this.mMenu = menu;
    }

    public Menu menu(CharSequence title) {
        mMenu.add(title);
        return this;
    }
    public Menu menu(int titleRes) {
        mMenu.add(titleRes);
        return this;
    }
    public Menu menu(int groupId, int itemId, int order, CharSequence title) {
        mMenu.add(groupId, itemId, order, title);
        return this;
    }
    public Menu menu(int groupId, int itemId, int order, int titleRes) {
        mMenu.add(groupId, itemId, order, titleRes);
        return this;
    }



    public Menu subMenu(CharSequence title) {
        mMenu.addSubMenu(title);
        return this;
    }
    public Menu subMenu(int titleRes) {
        mMenu.addSubMenu(titleRes);
        return this;
    }
    public Menu subMenu(int groupId, int itemId, int order, CharSequence title) {
        mMenu.addSubMenu(groupId, itemId, order, title);
        return this;
    }
    public Menu subMenu(int groupId, int itemId, int order, int titleRes) {
        mMenu.addSubMenu(groupId, itemId, order, titleRes);
        return this;
    }

}
