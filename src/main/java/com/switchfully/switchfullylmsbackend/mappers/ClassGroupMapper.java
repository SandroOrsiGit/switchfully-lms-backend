package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
                null,
                new ArrayList<>(),
                new ArrayList<>()
//                courseMapper.mapCourseToCourseDto(addedClassGroup.getCourse()),
//                addedClassGroup.getCoaches().stream()
//                        .map(coachMapper::mapCoachToCoachDto)
//                        .toList(),
//                addedClassGroup.getStudents().stream()
//                        .map(studentMapper::mapStudentToStudentDto)
//                        .toList()
        );
    }
}
