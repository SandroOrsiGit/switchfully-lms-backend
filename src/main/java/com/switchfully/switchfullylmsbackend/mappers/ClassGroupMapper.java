package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.courses.CourseDto;
import com.switchfully.switchfullylmsbackend.dtos.users.CoachDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentNoCodelabProgressDto;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.entities.Coach;
import com.switchfully.switchfullylmsbackend.entities.Course;
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

    public ClassGroup mapCreateClassGroupDtoToClassGroup(CreateClassGroupDto createClassGroupDto, Course course, Coach coach) {
        return new ClassGroup(
                createClassGroupDto.getName(),
                course,
                createClassGroupDto.getEndDate(),
                createClassGroupDto.getStartDate(),
                List.of(coach)
        );
    }

    public ClassGroupDto mapClassGroupToClassGroupDto(ClassGroup addedClassGroup) {
            return new ClassGroupDto(
                    addedClassGroup.getId(),
                    addedClassGroup.getName(),
                    addedClassGroup.getStartDate(),
                    addedClassGroup.getEndDate(),
                    mapCourseToCourseDto(addedClassGroup),
                    mapCoachListToCoachDtoList(addedClassGroup),
                    mapStudentListToStudentNoCodelabProgressDtoList(addedClassGroup)
            );
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

    private List<StudentNoCodelabProgressDto> mapStudentListToStudentNoCodelabProgressDtoList(ClassGroup classGroup) {
        if (classGroup.getStudents().isEmpty()) {
            return new ArrayList<>();
        } else {
            return classGroup.getStudents().stream()
                    .map(studentMapper::mapStudentToStudentNoCodelabProgressDto)
                    .collect(Collectors.toList());
        }
    }
}
