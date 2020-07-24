package com.haska.ui;

import com.haska.dao.Course;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CSController {
    @FXML private VBox mainArea;
    // student
    @FXML private TextField userName;
    @FXML private TextField sn;
    @FXML private TextField classID;

    // course
    @FXML private TextField courseName;
    @FXML private TextField credit;
    @FXML private TextField classHour;

    // class
    @FXML private TextField className;
    @FXML private TextField headMaster;

    // teacher
    @FXML private TextField teacherName;
    @FXML private TextField teacherID;
    @FXML private TextField courseID;

    private void openDialog(ActionEvent actionEvent, String title, String xml)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent stu_dialog = (Parent) fxmlLoader.load(getClass().getClassLoader().getResource(xml));
            Stage stuDialog = new Stage();
            stuDialog.initModality(Modality.APPLICATION_MODAL);

            stuDialog.setTitle(title);
            stuDialog.setScene(new Scene(stu_dialog));
            stuDialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeDialog(ActionEvent actionEvent)
    {
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void handleLogin(ActionEvent actionEvent) {
    }

    public void handleInputStudtentInfo(ActionEvent actionEvent) {
        openDialog(actionEvent, "录入学生信息", "student_dialog.fxml");
    }

    public void handleInputCourseInfo(ActionEvent actionEvent) {
        openDialog(actionEvent, "录入课程信息", "course_dialog.fxml");
    }

    public void handleInputClassInfo(ActionEvent actionEvent) {
        openDialog(actionEvent, "录入班级信息", "class_dialog.fxml");
    }

    public void handleInputTeacherInfo(ActionEvent actionEvent) {
        openDialog(actionEvent, "录入教师信息", "teacher_dialog.fxml");
    }

    public void handleCancel(ActionEvent actionEvent) {
        // Close the dialog
        closeDialog(actionEvent);
    }

    public void handleSubmitClassInfo(ActionEvent actionEvent) {
        System.out.println("ClassName : " + className.getText());
        System.out.println("HeadMaster : " + headMaster.getText());

        // Close the dialog
        closeDialog(actionEvent);
    }

    public void handleSummitStudentInfo(ActionEvent actionEvent) {
        // Get the value from textfield
        System.out.println("UserName : " + userName.getText());
        System.out.println("sn : " + sn.getText());
        System.out.println("classID : " + classID.getText());

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/course_system?user=root");
            stmt = c.createStatement();
            String sql = "insert into student values("
                    +userName.getText() + "," + sn.getText() + "," + classID.getText() +")";
            System.out.println(sql);
            boolean r = stmt.execute(sql);
            if (!r){
                System.out.println("Insert failed\n");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null){
                    stmt.close();
                }
                if (c != null){
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Close the dialog
        closeDialog(actionEvent);
    }

    public void handleSubmitCourseInfo(ActionEvent actionEvent) {
        System.out.println("courseName: " + courseName.getText());
        System.out.println("credit: " + credit.getText());
        System.out.println("classHour: " + classHour.getText());
        // Close the dialog
        closeDialog(actionEvent);
    }

    public void handleSummitTeacherInfo(ActionEvent actionEvent) {
        System.out.println("TeacherName : " + teacherName.getText());
        System.out.println("TeacherID : " + teacherID.getText());
        System.out.println("CourseID : " + courseID.getText());
        // Close the dialog
        closeDialog(actionEvent);
    }

    public  class Student{
        private String name;
        private int sn;
        private int classid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSn() {
            return sn;
        }

        public void setSn(int sn) {
            this.sn = sn;
        }

        public int getClassid() {
            return classid;
        }

        public void setClassid(int classid) {
            this.classid = classid;
        }
    }
    public void handleQueryStudentInfo(ActionEvent actionEvent) {
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;

        mainArea.getChildren().clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/course_system?user=root");
            stmt = c.createStatement();
            String sql = "select * from student";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);

            List<Student> stu_list = new ArrayList<>();
           while (rs.next()){
               Student student = new Student();
               student.setName(rs.getString("username"));
               student.setSn(rs.getInt("sn"));
               student.setClassid(rs.getInt("classid"));
               stu_list.add(student);
           }

            TableMetaData metaDatas = new TableMetaData();
            metaDatas.addMetaData("学生姓名", "name");
            metaDatas.addMetaData("学号","sn");
            metaDatas.addMetaData("班级 ID", "classid");

            TableView<Student> tableView = TableViewFactory.createTableView(metaDatas.getTitleNames(), stu_list);

            mainArea.getChildren().add(tableView);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null){
                    stmt.close();
                }
                if (c != null){
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleQueryCourseInfo(ActionEvent actionEvent) {
        mainArea.getChildren().clear();

        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/course_system?user=root");
            stmt = c.createStatement();
            String sql = "select * from course";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);

            List<Course> course_list = new ArrayList<>();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseName(rs.getString("coursename"));
                course.setCredit(rs.getInt("credit"));
                course.setClassHour(rs.getInt("classhour"));
                course.setTeacherName(rs.getString("teachername"));
                course_list.add(course);
            }

            TableMetaData metaDatas = new TableMetaData();
            metaDatas.addMetaData(true, "Check", "check");
            metaDatas.addMetaData("课程名称", "courseName");
            metaDatas.addMetaData("学分", "credit");
            metaDatas.addMetaData("学时", "classHour");
            metaDatas.addMetaData("老师", "teacherName");

            TableView<Course> tableView = TableViewFactory.createTableView(metaDatas.getTitleNames(), course_list);
            HBox tableBox = new HBox(tableView);

            VBox.setVgrow(tableBox, Priority.ALWAYS);
            HBox.setHgrow(tableView, Priority.ALWAYS);

            Button ok = new Button("提交");
            ok.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (Course p : tableView.getItems()) {
                        System.out.println(p.isCheck() + " 2 " + p.getCourseName());
                    }
                }
            });
            Button cancel = new Button("清除");
            cancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (Course p : tableView.getItems()) {
                        p.setCheck(false);
                    }
                }
            });


            HBox buttonBox = new HBox(ok, cancel);
            tableView.setEditable(true);
            mainArea.getChildren().add(tableBox);
            mainArea.getChildren().add(buttonBox);
            System.out.println("query course info");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
