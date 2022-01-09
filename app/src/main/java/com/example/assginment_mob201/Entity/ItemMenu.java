package com.example.assginment_mob201.Entity;

public class ItemMenu {
    private int IdItemMenu;
    private String urlImage;
    private String TitleItem;

    public ItemMenu(int idItemMenu, String urlImage, String titleItem) {
        IdItemMenu = idItemMenu;
        this.urlImage = urlImage;
        TitleItem = titleItem;
    }

    public int getIdItemMenu() {
        return IdItemMenu;
    }

    public void setIdItemMenu(int idItemMenu) {
        IdItemMenu = idItemMenu;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getTitleItem() {
        return TitleItem;
    }

    public void setTitleItem(String titleItem) {
        TitleItem = titleItem;
    }
}
