package com.haska.ui;


import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.lang.reflect.Field;
import java.util.List;

public class TableViewFactory {

    public static  <T> TableView<T> createTableView(List<TableMetaData.ColumnTitleAndPropertyName> metaDatas,
                                            List<T> list){
        TableView tableView = new TableView();

        for (TableMetaData.ColumnTitleAndPropertyName metadata:metaDatas) {
            TableColumn<String, T> column = new TableColumn<>(metadata.getTitle());
            column.setCellValueFactory(new PropertyValueFactory<>(metadata.getPropertyName()));
            if (metadata.isCheckbox()){
                System.out.println("......");
                column.setCellFactory(tc -> new CheckBoxTableCell<>());
                column.setEditable(true);
            }
            tableView.getColumns().add(column);
        }

        for (T t: list) {
            tableView.getItems().add(t);
        }

        return tableView;
    }
}
