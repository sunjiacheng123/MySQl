package com.haska.ui;

import java.util.ArrayList;
import java.util.List;

public class TableMetaData {
    private List<ColumnTitleAndPropertyName> titleNames = new ArrayList<>();

    public static class ColumnTitleAndPropertyName{
        private boolean isCheckbox = false;
        private String title;
        private String propertyName;

        public ColumnTitleAndPropertyName(String title, String propertyName) {
            this.title = title;
            this.propertyName = propertyName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public boolean isCheckbox() {
            return isCheckbox;
        }

        public void setCheckbox(boolean checkbox) {
            isCheckbox = checkbox;
        }
    }

    public void addMetaData(String title, String propertyName){
        titleNames.add(new ColumnTitleAndPropertyName(title, propertyName));
    }

    public void addMetaData(boolean ischeck, String title, String propertyName){
        ColumnTitleAndPropertyName entry = new ColumnTitleAndPropertyName(title, propertyName);
        entry.setCheckbox(ischeck);
        titleNames.add(entry);
    }
    public void addMetaData(List<String> titles, List<String> propertyNames){

        if (titles == null || propertyNames == null)
            return;

        if (titles.size() != titles.size())
            return;

        for (int i =0; i < titles.size(); ++i){
            titleNames.add(new ColumnTitleAndPropertyName(titles.get(i), propertyNames.get(i)));
        }
    }

    public List<ColumnTitleAndPropertyName> getTitleNames(){return titleNames;}
}
