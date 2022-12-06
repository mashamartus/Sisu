On the welcome screen user can choose to continue planning from the saved session or create a new plan. 

To create a new plan 
Input a student name and by-default plan name and starting year*. Then from the list of degrees choose one you want to plan. One plan can have only single degree in it. Degree names are taken from Sisu api and sorted in alphabetical order of its english name. With 'Create new plan' button user gets to the main window. 
It may take some time, up to a minute to prepare the window. 

To continue from the saved plan
In the left pane of the start window choose the plan name of existing saved plans from the drop down menu and press 'Use saved plan' button. If for some reason listed file is not contained anymore in Sisu/src/main/resources directory, user gets warning pop-up message. 
Then saved session is restored, including program structure, taken courses and grades. We assume that program in sisu is not going to change and student program courses are uploaded from sisu api every time anew.
It may take some time, up to a minute to prepare the window. 

Course box
Each course row contas the course name in English or Finnish, grade section when possible or getten grade is displayed, amount of credits for the course and course control buttons. Initially course box is blue. When course is taken its color changes to white. 

Picking the course 
In main window user can pick the courses to his plan from the course tree with Button Take course. Taken course box change color and buttons Drop and Grade appear instead of 'Take course'. Progress on the left is updated then, including picked course into the planned credits and diagram is updated. Also the planned credit amount of the current module is updated**. When course is "taken" student can drop it (and progress updates accordingly) or grade with buttons on the course.

Grading the course:
Grading of the course can't be undone. When course is graded the course is considered to be completed and course control buttons gets disabled. The grade gets shown in the course box. Fields completed courses and average grade is updated.
Grading depends on the course - if course is assessed as pass/fail, then pressing a Grade button just marks the course as passed. If course is gradable with 1-5 mark, then dialog window is popping up. User can input there his grade for that course and press OK. If the grade is not 1-5 then the warning window pops up. In this case, and if user press Cancel, nothing happens.
Average grade displayed is not weighted average, but the plain average.

Show all courses/Show taken courses
Above the course tree there is a Show all courses/Show taken courses switch. 'Show taken courses' leave only courses chosen by student in the tree. 'Show all courses' return hidden courses. There is known bug, see ***

Change the language
With switch en/fi user can change the displayed language of the course. There is known bug, see ***

Save the plan
Pressing that button opens a dialog where user can input the plan name. Currently the program doesn't check whether such name already used, so be careful to not overwrite your previous plan accidentally. With Save button the file in json format is saved in Sisu/src/main/resources. 

Start over
If user doesn't want to continue to modify current plan he can go to the start window with Start over button. It will ask user does he wants to save his plan first. If yes, then look Save the plan section of this instruction. After that, or if user choose not to save the plan, start window will open. At the same moment list of the available saved plans will be updated, so user can find his just saved plan there as well. 

Quit the program
User can quit the program with 'Quit' button. Save window will be displayed to the user after that. After pressing Save or Cancel buttons program will exit.

*) For now starting year is just stored, but in future it is aimed to control the output of sisu api. List of available programs is different for different years. 

**) For now only the current module planned credits is updated, but it not update parental modules credits.

***) Some programs contain same course in few different modules. It causes the issue that all instances of those doubled courses except last doesn't get hidden at 'Show taken courses' and not changing the language. It may cause other not founded issues.
