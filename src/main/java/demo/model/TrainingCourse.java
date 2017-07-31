package demo.model;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.model.Model;

import java.sql.Timestamp;

/**
 * Created by liangwenhui on 2017/7/31.
 */
public class TrainingCourse extends Model {
    @Id
    private Integer courseId;
    private Integer lecturerId;
    private String courseName;
    private Timestamp trainingTime;
    @Column(name="long_training_period")
    private String trainLast;
    @Column(name="registration_deadline")
    private Timestamp registrationDeadline;
    @Column(name="place_for_training")
    private String trainingPlace;
    private String courseIntroduction;
    private String picture;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Timestamp getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(Timestamp trainingTime) {
        this.trainingTime = trainingTime;
    }

    public String getTrainLast() {
        return trainLast;
    }

    public void setTrainLast(String trainLast) {
        this.trainLast = trainLast;
    }

    public Timestamp getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(Timestamp registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public String getTrainingPlace() {
        return trainingPlace;
    }

    public void setTrainingPlace(String trainingPlace) {
        this.trainingPlace = trainingPlace;
    }

    public String getCourseIntroduction() {
        return courseIntroduction;
    }

    public void setCourseIntroduction(String courseIntroduction) {
        this.courseIntroduction = courseIntroduction;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
