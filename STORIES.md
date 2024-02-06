# Building a Learning Management System

You will build further on the project that a previous class delivered.

[Backend](https://github.com/switchfully/final-project-backend)

[Frontend](https://github.com/switchfully/final-project-frontend)

[Board](https://miro.com/app/board/uXjVO5iZWjs=/)

## Stories

### HOM-1 - (MUST)

**As a visitor I will see the home page when visiting the LMS application**

    When a visitor navigates to our application, he/she will see arrive at our home page first.
    The home page contains information on what the LMS system is about.

![Home](images/home.png)

### ACC-1

**As a visitor I can register an account**

    When a student wants to use our LMS, he/she first needs to create an account.
    To create an account the visitor can go the register page using the navigation bar.
    After filling in the form the visitor is registered in the system.

#### Screen

![Register](images/register.png)

#### Acceptance criteria

| Given                                                                                | When                         | Then                                                         |
|--------------------------------------------------------------------------------------|------------------------------|--------------------------------------------------------------|
| As a visitor, I have filled in my: display name, email, password and repeat password | Clicking the register button | I can log into my account                                    |
| As a visitor I forget to fill in a field                                             | Clicking the register button | I get a message the the field is required                    |
| As a visitor I register with an already registered email address                     | Clicking the register button | I get a message that the email address is already registered |
| As a visitor I fill in two different passwords                                       | Clicking the register button | I get a message that the password do not match               |
| As a visitor I fill an e-mail that is in the wrong format                            | Clicking the register button | I get a message that the e-mail is not correctly formatted   |

### ACC-2 - (MUST)

**As a student I can log into my student account**

    When a student wants to login, he/she first need to navigate to the login page.
    On the login page the visitor can enter his/her credentials.
    When pressing the login button, if the credentials are correct, the student is logged in.
    The student will then be navigated to their profile page and their name will appear in the navigation bar as: 'logged in as student <student_name>'

#### Screen

![Login](images/login.png)

| Given                                 | When                                                                                   | Then                                                                                                                   |
|---------------------------------------|----------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|
| A student with an existing account    | Going to the login page<br/>Filling in their credentials<br/>Pressing the login button | Student will be navigated to their profile page<br/>The message 'Logged in as student <student_name>' will be visible. |
| A student with a non-existing account | Going to the login page<br/>Filling in their credentials<br/>Pressing the login button | A warning message will appear: 'Invalid credentials'                                                                   |
| A student with a existing account     | Going to the login page<br/>Filling in the wrong password                              | A warning message will appear: 'Invalid credentials'                                                                   |

### ACC-3 - (MUST)

**As a student I can view my profile**

    Every student has a profile page. In here a student can view his/her profile.
    In the profile page you can view your: username and display name.

#### Screen

![Profile](images/edit-profile.png)

| Given                             | When                                                      | Then                                                                                |
|-----------------------------------|-----------------------------------------------------------|-------------------------------------------------------------------------------------|
| A student logs into their account | Login successful                                          | The student is navigated to their profile page, viewing their name and display name |
| A student is logged in            | Navigating to their profile page using the navigation bar | The student is navigated to their profile page, viewing their name and display name |

### ACC-4

**As a student I can edit my profile**

    Every student has a profile page. In here a student can change his/her profile.
    In the edit profile page you can change your: username, display name and password.

#### Screen

![Edit-Profile](images/edit-profile.png)

| Given                                   | When                                                                        | Then                                                                                                      |
|-----------------------------------------|-----------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| A student is on their profile page      | When clicking the 'edit' button                                             | The student is navigated to the edit profile page                                                         |
| A student is on their edit profile page | Changing either their display name, email or password and pressing 'save'   | The respective properties will have changed<br/>The student will be navigated back to their profile page. |
| A student is on their edit profile page | Changing either their display name, email or password and pressing 'cancel' | Nothing will have changed<br/>The student will be navigated back to their profile page.                   |
| A student is on their edit profile page | Changing their email to an already existing email and pressing 'save'       | The student will get a warning message: 'email already exists'                                            |
| A student is on their edit profile page | Changing their password but passwords do not match                          | The student will get a warning message: 'passwords do not match'                                          |

### COA-1 - (MUST)

**As a coach I can log into my account**

#### Screen
![Login](images/login.png)

### CLA-1  - (MUST)

**As a coach I can create a class**

    Every coach can create a class. This class will be tied to that specific coach.
    
| Given                               | When                                                | Then                                        |
|-------------------------------------|-----------------------------------------------------|---------------------------------------------|
| A coach is viewing his profile page | When clicking the 'create class' button             | The coach is taken to the create class page |
| A coach is in the create class page | When entering a name and clicking the 'save' button | The class is saved and tied to the coach    |

### CLA-2 - (MUST)

**As a student I can choose my class in my profile**

    Every student is a member of a class. The student can go to his/her profile and update it to match his/her corresponding class.

| Given                                | When                          | Then                                                             |
|--------------------------------------|-------------------------------|------------------------------------------------------------------|
| A student is on his/her profile page | When viewing his/her class    | The student sees his/her class                                   |
| A student edits his/her profile page | When trying to select a class | The student sees a list of classes and can select his/her class. |

### CLA-3 - (MUST)

**As a student/coach I can view the class overview screen**

    Every class has an overview screen. Both students and coaches can see the overview screen of their corresponding class. A class overview screen shows all students and coaches that are part of the class. Coaches should be able to click through to a specific student's profile page.
    
| Given                                     | When                                      | Then                                          |
|-------------------------------------------|-------------------------------------------|-----------------------------------------------|
| A coach is viewing his/her profile page   | When clicking a class tied to the coach   | The coach is taken to the class overview page |
| A student is viewing his/her profile page | When clicking a class tied to the student | The student is taken to the class overview    |

### MOD-1 - (MUST)

**As a coach I can create a module**

    Every coach can create a module. A module is a collection of sub-modules and codelabs connected to a specific course. For example: "Java Fundamentals"
    
| Given                                  | When                                     | Then                                         |
|----------------------------------------|------------------------------------------|----------------------------------------------|
| A coach is on the course overview page | When clicking the "create module" button | The coach is taken to the create module page |
| A coach is on the create module page   | When entering the module name            | The module is saved                          |

### MOD-2

**As a coach I can edit the name of a module**

    Every coach can edit existing modules names.

### MOD-3 - (MUST)

**As a student I can get an overview of all modules**

    Every student can see an overview of all available modules.

### SUB-1

**As a coach I can create a sub-module**

    Every coach can create sub-modules. A sub-module is a specific part of an upper-lying module. For example: Java Fundamentals is a module, Object-Oriented Programming is a sub-module of said Java Fundamentals module.
    
| Given                                    | When                                         | Then                                                 |
|------------------------------------------|----------------------------------------------|------------------------------------------------------|
| A coach is on a module's overview page   | When clicking the "create sub-module" button | The coach is taken to the create sub-module page     |
| A coach is on the create sub-module page | When entering the sub-module name            | The sub-module is saved and tied to the upper module |

### SUB-2

**As a coach I can edit the name of a sub-module**

    Every coach can edit a sub-module's name.
### SUB-3

**As a student I can get an overview of all sub-modules**

    Every student can request an overview of all sub-modules.

### COU-1 - (MUST)

**As a coach I can create a course**

    Every coach can create a course. A course contains connected modules and their sub-modules and codelabs.
    
| Given                                | When                                     | Then                                         |
|--------------------------------------|------------------------------------------|----------------------------------------------|
| A coach is on the main dashboard     | When clicking the "create course" button | The coach is taken to the create course page |
| A coach is on the create course page | When entering the course name            | The course is saved                          |

### COU-2 - (MUST)

**As a coach I can edit the name of a course**

    Every coach can edit the name of a course.

### COU-3 - (MUST)

**As a student I get an overview of all courses in a modules**

    Every student can request an overview of all courses available in a module.

### COD-1 - (MUST)

**As a coach I can create a codelab**

| Given                                 | When                                      | Then                                          |
|---------------------------------------|-------------------------------------------|-----------------------------------------------|
| A coach is on the main dashboard      | When clicking the "create codelab" button | The coach is taken to the create codelab page |
| A coach is on the create codelab page | When entering the codelab name            | The codelab is saved                          |

### COD-2 - (MUST)

**As a coach I edit a codelab**

| Given                               | When                                                  | Then                                        |
|-------------------------------------|-------------------------------------------------------|---------------------------------------------|
| A coach is on the codelab page      | When clicking the "edit codelab" button               | The coach is taken to the edit codelab page |
| A coach is on the edit codelab page | When entering the codelab details (name, connections) | The codelab is saved                        |

### COD-3 - (MUST)

**As a student I can get an overview of all codelabs in a course**

    As a student I want to see a list of codelabs when selecting a course.

#### Screen
![Codelabs-overview](images/codelabs-overview.png)

| Given | When                         | Then                                 |
|-------|------------------------------|--------------------------------------|
|       | I click on the overview link | I see an overview of all the courses |


### PRO-1

**As a student I can mark my progress of a codelab**
    
    When visiting the codelabs overview page, you can select your progression for each codelab.
    This progression will be saved if you press the 'save' button.
    The options you can choose from are: Not started, Busy, Feedback needed, Stuck, Testing, Refactoring, Done

#### Screen
![Codelabs-overview](images/codelabs-overview.png)


| Given                                                                    | When                                                   | Then                                                                         |
|--------------------------------------------------------------------------|--------------------------------------------------------|------------------------------------------------------------------------------|
| I am in the codelabs overview page<br/>All codelabs are in 'Not Started' | I select 'Done' for the first codelab and press 'save' | I refresh the codelab overview page. I see that the first codelab is in done |
| Scenario#1 + I log in as a different student with no progression         | I navigate to the codelab overview page                | All codelabs are still in 'Not Started'                                      |


### PRO-2

**As a student a sub-module is marked as done if I have marked all codelabs of that course as done**

### PRO-3 

**As a student a module is marked as done if I have marked all codelabs of that module as done**

### PRO-4 

**As a student a course is marked as done if I have marked all modules of that course as done**

### PRO-5
**As a coach I can get an overview of the progress of all the students**

    When visiting the progress overview page as a coach. I see an overview of all students.
    Under each student name there will be a progress bar showing the progression of that student.

#### Screen
![Progression-overview](images/progression-overview.png)

| Given                                                                                                       | When                                | Then                                              |
|-------------------------------------------------------------------------------------------------------------|-------------------------------------|---------------------------------------------------|
| A student that did has put 0 of the 6 codelabs on 'Done' or 'Feedback Needed'<br/>I am logged in as a coach | Visiting the progress overview page | I see an empty progress bar under the student     |
| A student that did has put 3 of the 6 codelabs on 'Done' or 'Feedback Needed'<br/>I am logged in as a coach | Visiting the progress overview page | I see an half full progress bar under the student |
| 6 students exist in the application<br/>I am logged in as a coach                                           | Visiting the progress overview page | I see the progress of 6 students                  |

### CDD-1 - (MUST)

**As a student I can go to the detail of a codelab**

### CDD-2 - (MUST)

**As a student I can add a personal comment to the detail of a codelab**
