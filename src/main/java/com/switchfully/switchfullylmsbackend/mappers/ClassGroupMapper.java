package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.courses.CourseDto;
import com.switchfully.switchfullylmsbackend.dtos.users.CoachDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentDto;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassGroupMapper {
    private final CourseMapper courseMapper;
    private final CoachMapper coachMapper;
    private final StudentMapper studentMapper;

    public ClassGroupMapper(CourseMapper courseMapper, CoachMapper coachMapper, StudentMapper studentMapper) {
        this.courseMapper = courseMapper;
        this.coachMapper = coachMapper;
        this.studentMapper = studentMapper;
    }

    public ClassGroup mapCreateClassGroupDtoToClassGroup(CreateClassGroupDto createClassGroupDto) {
        return new ClassGroup(
                createClassGroupDto.getName(),
                createClassGroupDto.getStartDate(),
                createClassGroupDto.getEndDate()
        );
    }

    public ClassGroupDto mapClassGroupToClassGroupDto(ClassGroup addedClassGroup) {
            return new ClassGroupDto(
                    addedClassGroup.getId(),
                    addedClassGroup.getName(),
                    addedClassGroup.getStartDate(),
                    addedClassGroup.getEndDate(),
                    mapCourseToCourseDto(addedClassGroup),
                    //TODO implement coach always existing
                    mapCoachListToCoachDtoList(addedClassGroup),
                    mapStudentListToStudentDtoList(addedClassGroup)
            );

//        return new ClassGroupDto(
//                addedClassGroup.getId(),
//                addedClassGroup.getName(),
//                addedClassGroup.getStartDate(),
//                addedClassGroup.getEndDate(),
//                null,
//                new ArrayList<>(),
//                new ArrayList<>()
//                courseMapper.mapCourseToCourseDto(addedClassGroup.getCourse()),
//                addedClassGroup.getCoaches().stream()
//                        .map(coachMapper::mapCoachToCoachDto)
//                        .toList(),
//                addedClassGroup.getStudents().stream()
//                        .map(studentMapper::mapStudentToStudentDto)
//                        .toList()
//        );
    }

    private CourseDto mapCourseToCourseDto (ClassGroup classGroup) {
        if (classGroup.getCourse() == null) {
            return null;
        } else {
            return courseMapper.mapCourseToCourseDto(classGroup.getCourse());
        }
    }

    private List<CoachDto> mapCoachListToCoachDtoList (ClassGroup classGroup) {
        if (classGroup.getCoaches().isEmpty()) {
            return null;
        } else {
            return classGroup.getCoaches().stream()
                    .map(coachMapper::mapCoachToCoachDto)
                    .collect(Collectors.toList());
        }
    }

    private List<StudentDto> mapStudentListToStudentDtoList(ClassGroup classGroup) {
        if (classGroup.getStudents().isEmpty()) {
            return new ArrayList<>();
        } else {
            return classGroup.getStudents().stream()
                    .map(studentMapper::mapStudentToStudentDto)
                    .collect(Collectors.toList());
        }
    }
}
