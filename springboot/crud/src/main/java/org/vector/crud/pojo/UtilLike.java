package org.vector.crud.pojo;

public class UtilLike {

    private Long id;

    private String userName;

    private String workId;

    private String sex;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UtilLike{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", workId='" + workId + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
