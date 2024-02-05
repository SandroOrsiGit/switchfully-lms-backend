package com.switchfully.switchfullylmsbackend.dtos.classgroups;

public class AddStudentToClassGroupDto {
    private Long studentId;
    private Long classGroupId;

    public AddStudentToClassGroupDto() {
    }

    public AddStudentToClassGroupDto(Long studentId, Long classGroupId) {
        this.studentId = studentId;
        this.classGroupId = classGroupId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setClassGroupId(Long classGroupId) {
        this.classGroupId = classGroupId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getClassGroupId() {
        return classGroupId;
    }
}
