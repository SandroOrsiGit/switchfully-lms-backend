package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.users.CreateStudentDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentDto;
import com.switchfully.switchfullylmsbackend.dtos.users.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void whenCreateUser_thenUserIsCreatedAndSavedToRepository() {
        // given
        CreateStudentDto createStudentDto = new CreateStudentDto(
                "testDisplayName",
                "test@mail.com",
                "password"
        );

        // when
        StudentDto studentDto = userService.createStudent(createStudentDto, "123");

        // then
        assertThat(createStudentDto.getDisplayName()).isEqualTo(studentDto.getDisplayName());
        assertThat(createStudentDto.getEmail()).isEqualTo(studentDto.getEmail());
    }

    @Test
    void givenUserId_whenGetUserById_thenGetUserDto() {
        // given
        Long userId = 1L;

        // when
        UserDto userDto = userService.getUserById(userId);

        // then
        assertThat(userDto.getId()).isEqualTo(userId);
    }

//	AbstractAuthenticationToken authentication;
//	StudentMapper studentMapper;
//	KeycloakService keycloakService;
//	UserRepository userRepository;
//	UserMapper userMapper;
//	UserService userService;
//	CreateUserDto createUserDto;
//	Student student;
//	Coach coach;
//	Long userId;
//
//	@BeforeEach
//	void init() {
//		authentication = mock(AbstractAuthenticationToken.class);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		studentMapper = mock(StudentMapper.class);
//		keycloakService = mock(KeycloakService.class);
//		userRepository = mock(UserRepository.class);
//		userMapper = mock(UserMapper.class);
//
//		userService = new UserService(studentMapper, userRepository, keycloakService, userMapper);
//
//		student = new Student();
//		coach = new Coach();
//		userId = 123L;
//	}
//
////	@Test
////	void givenCreateUserDto_whenAddUser_thenVerifyCorrectBeansAreCalled() {
////		//GIVEN
////		createUserDto = new CreateUserDto(student.getDisplayName(), student.getEmail(), "test123");
////		when(studentMapper.mapCreateUserDtoToStudent(createUserDto)).thenReturn(student);
////
////		//WHEN
////		userService.addUser(createUserDto);
////
////		//THEN
////		verify(studentMapper).mapCreateUserDtoToStudent(createUserDto);
////		verify(keycloakService).addUser(createUserDto);
////		verify(userRepository).save(student);
////	}
//
//	@Test
//	void givenUpdateUserDto_whenUpdateUser_thenVerifyCorrectBeansAreCalled() {
//		//GIVEN
//		UpdateUserDto updateUserDto = new UpdateUserDto();
//		when(userRepository.findByEmail(updateUserDto.getEmail())).thenReturn(student);
//		when(userRepository.findById(updateUserDto.getId())).thenReturn(Optional.ofNullable(student));
//
//		//WHEN
//		userService.updateUser(updateUserDto);
//
//		//THEN
//		verify(userRepository).findByEmail(updateUserDto.getEmail());
//		verify(userRepository).findById(updateUserDto.getId());
//		verify(userRepository).save(student);
//	}
//
//	@Test
//	void givenUpdateUserDtoWithEmailAlreadyInUse_whenUpdateUser_thenThrowIllegalArgumentException() {
//		//GIVEN
//		UpdateUserDto updateUserDto = new UpdateUserDto(userId, student.getEmail(), student.getDisplayName());
//		when(userRepository.findByEmail(updateUserDto.getEmail())).thenReturn(student);
//
//		//WHEN & THEN
//		assertThatThrownBy(() -> userService.updateUser(updateUserDto))
//				.isInstanceOf(IllegalArgumentException.class)
//				.hasMessageContaining("email already in use");
//	}
//
//	@Test
//	void givenUpdateUserWithInvalidId_whenUpdateUser_thenThrowIllegalArgumentException() {
//		//GIVEN
//		UpdateUserDto updateUserDto = new UpdateUserDto(student.getId(), student.getEmail(), student.getDisplayName());
//		when(userRepository.findByEmail(updateUserDto.getEmail())).thenReturn(student);
//
//		//WHEN & THEN
//		assertThatThrownBy(() -> userService.updateUser(updateUserDto))
//				.isInstanceOf(IllegalArgumentException.class)
//				.hasMessageContaining("User not found");
//	}
//
//	@Test
//	void givenUserIdForStudent_whenGetUserById_thenReturnUserDtoWithRoleStudent() {
//		//GIVEN
//		UserDto userDto = new UserDto(userId, student.getEmail(), student.getDisplayName(), "student");
//		when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(student));
//		when(userMapper.mapAbstractUserToUserDto(student, "student")).thenReturn(userDto);
//
//		//WHEN
//		UserDto actual = userService.getUserById(userId);
//
//		//THEN
//		verify(userRepository).findById(userId);
//		verify(userMapper).mapAbstractUserToUserDto(student, "student");
//		assertThat(actual.getId()).isEqualTo(userId);
//		assertThat(actual.getRole()).isEqualTo("student");
//	}
//	@Test
//	void givenUserIdForCoach_whenGetUserById_thenReturnUserDtoWithRoleCoach() {
//		//GIVEN
//		UserDto userDto = new UserDto(userId, coach.getEmail(), coach.getDisplayName(), coach.getRole());
//		when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(coach));
//		when(userMapper.mapAbstractUserToUserDto(coach, "coach")).thenReturn(userDto);
//
//		//WHEN
//		UserDto actual = userService.getUserById(userId);
//
//		//THEN
//		verify(userRepository).findById(userId);
//		verify(userMapper).mapAbstractUserToUserDto(coach, "coach");
//		assertThat(actual.getId()).isEqualTo(userId);
//		assertThat(actual.getRole()).isEqualTo("coach");
//	}
//
//	@Test
//	void givenInvalidUserId_whenGetUserById_thenThrowIdNotFoundException() {
//		//GIVEN
//		when(userRepository.findById(userId)).thenReturn(Optional.empty());
//
//		//WHEN & THEN
//		assertThatThrownBy(() -> userService.getUserById(userId))
//				.isInstanceOf(IdNotFoundException.class)
//				.hasMessageContaining("User with id " + userId + " not found");
//	}
//
//	@Test
//	void givenAccessToken_whenGetUserByToken_thenReturnUser() {
//		//GIVEN
//		String accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJVdDNBekY0Z0lrUFpIaDJGZDlsUHczRTdWc3N6M0ExNFNqUXJONWFhS0J3In0.eyJleHAiOjE3MDU2NzE2NDcsImlhdCI6MTcwNTY3MTM0NywianRpIjoiMjViZjY5YzAtYTI3MC00NDM4LWFiNWQtMGQwM2ZkZTA4YTY4IiwiaXNzIjoiaHR0cHM6Ly9rZXljbG9hay5zd2l0Y2hmdWxseS5jb20vcmVhbG1zL2phdmEtMjAyMy0xMCIsImF1ZCI6WyJzd2l0Y2hmdWxseS1sbXMiLCJhY2NvdW50Il0sInN1YiI6ImQwNDk1NTdlLTdlODQtNDg0ZS05YjkyLTAzMjQyY2UzZjkyNSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImtleWNsb2FrLWV4YW1wbGUiLCJzZXNzaW9uX3N0YXRlIjoiMTdiNzM0ZDEtNzY1NC00N2JkLWI3NDItMDk3ZDA2NzU1NDkwIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImRlZmF1bHQtcm9sZXMtamF2YS0yMDIzLTEwIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJzd2l0Y2hmdWxseS1sbXMiOnsicm9sZXMiOlsic3R1ZGVudCJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwic2lkIjoiMTdiNzM0ZDEtNzY1NC00N2JkLWI3NDItMDk3ZDA2NzU1NDkwIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ0ZXN0QGxtcy5jb20iLCJlbWFpbCI6InRlc3RAbG1zLmNvbSJ9.UMeb694TESqbjsukvQtXtGkMgMs_e8A6EoO7cplpZc8dLQPUdF-swWfya1UbawgOFRXf2_GUru5KCe7pOrwJqAqgAbp914a2dRpCecQla0o-FYSP-Biamzw6xtILaOrXVEBQA53e9t4BGGObkWZ6BJsnZekFUnD44Z0PkM8I8EyTLIQ9IZyI4IW8XnOklr2_ZiNCNt-BcstnvdZiWHGww8KLPoZQX-kbLfLKfIh7YOaupyZfHhoD_OK4bG36esjHguTO3mkDak8jReBePhIG96I2Fe9AI_Y9-LQG2jjnZ5uKYKQbSI3Hv_OOCnFqjBFN7SS3QGehWF6ScC2f4f4iJw";
//		when(userRepository.findByEmail("test@lms.com")).thenReturn(student);
//		when(authentication.getAuthorities())
//				.thenReturn(Collections.singletonList(() -> "student"));
//		when(userMapper.mapAbstractUserToUserDto(student, "student")).thenReturn(new UserDto(student.getId(), student.getEmail(), student.getDisplayName(), student.getRole()));
//
//		//WHEN
//<<<<<<< HEAD
//		UserDto actual = userService.getUserDtoByToken(accessToken);
//=======
//		AbstractUser actual = userService.getUserByToken(accessToken);
//>>>>>>> 1a2509743b601c8c75ba91c44064f5150f6c4e15
//
//		//THEN
//		verify(userRepository).findByEmail("test@lms.com");
//		verify(authentication).getAuthorities();
//		verify(userMapper).mapAbstractUserToUserDto(student, "student");
//		assertThat(actual.getId()).isEqualTo(student.getId());
//		assertThat(actual.getRole()).isEqualTo("student");
//	}
}
