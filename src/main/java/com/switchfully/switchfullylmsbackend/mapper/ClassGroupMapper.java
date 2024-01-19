package com.switchfully.switchfullylmsbackend.mapper;

import com.switchfully.switchfullylmsbackend.dto.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dto.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entity.ClassGroup;
import org.springframework.stereotype.Component;

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
                createClassGroupDto.getName()
        );
    }

    public ClassGroupDto mapClassGroupToClassGroupDto(ClassGroup addedClassGroup) {
        return new ClassGroupDto(
                addedClassGroup.getId(),
                addedClassGroup.getName(),
                courseMapper.mapCourseToCourseDto(addedClassGroup.getCourse()),
                addedClassGroup.getCoaches().stream()
                        .map(coachMapper::mapCoachToCoachDto)
                        .toList(),
                addedClassGroup.getStudents().stream()
                        .map(studentMapper::mapStudentToStudentDto)
                        .toList()
        );
    }
}