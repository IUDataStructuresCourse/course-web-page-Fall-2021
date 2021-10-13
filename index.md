## CSCI H343 Data Structures

Indiana University, Fall 2021


This course studies the fundamental ideas for efficiently analyzing
large amounts of data, such as DNA sequence databases and geographic
information. These fundamental ideas come in two kinds: algorithms and
data structures. Algorithms are instructions for solving problems and
data structures are strategies for organizing information on
computers. Efficient algorithms require appropriate data structures,
and vice versa, so the study of algorithms and data structures is
tightly linked. In this course we learn about the algorithms and data
structures that form the building blocks for many of Today's
large-scale computer systems. We apply these ideas to solve
challenging problems in bioinformatics and geographic information
systems. Warning: a possible side-effect of taking this course is
doing better on job interview questions.

**Lecture** 

Mondays and Wednesdays 1:10pm-2:25pm, Student Building, Room 138.

**Lab** 

Fridays 10:00am-11:55am, Ballantine Hall 118.

**Instructors and Office Hours**

* Jeremy Siek (jsiek), office hours: Tue 1-2pm, Wed 11am-noon, in Luddy 3014 (or nearby).
* Annie Pompa (apompa), office hours: Thu 5-6pm, Fri 3-4pm, in Luddy 3014.
* Sophia Zhang (zhanso), office hours: Tue 2:30-3:30pm, Fri 2:45-3:45pm, in Luddy 3014.

**Textbook**

*Data Structures and Algorithm Analysis in Java*, 3rd Ed. by Mark A. Weiss

**Slack (communicating with instructors and other students)**

[Workspace](https://h343datastruc-yln1475.slack.com)
 ([signup](https://join.slack.com/t/indiana-izt3285/shared_invite/zt-ucnml3au-jps1wf8czRc0iNM_1khQ5g))

**Schedule**

Day     | Lecture Topic         | Reading Due    | Assignment Due
Aug. 23 | [Introduction](./lectures/Aug-23.md) |   |
Aug. 25 | [Arrays, Rotation, Correctness](./lectures/Aug-25.md) | Ch. 1 | 
Aug. 27 |                       |                | Lab: Array [Search](https://iu.instructure.com/courses/1996307/assignments/12618347), [submit](https://autograder.sice.indiana.edu/web/project/321)
Aug. 30 | [Code Review & Algorithm Analysis](./lectures/Aug-30.md) | Ch. 2 |
Sep. 1  | [Algo. Analysis cont'd](./lectures/Sep-1.md) |                |
Sep. 3  |                       |                | Project: [Flood it!](https://iu.instructure.com/courses/1996307/assignments/12641907), [submit](https://autograder.sice.indiana.edu/web/project/322)
Sep. 6  | Labor Day             |                |
Sep. 8  | [Linked Lists & Streams](./lectures/Sep-8.md) | Ch. 3 sec. 1-5 | [Homework 1](./HW1.md)
Sep. 10 |                       |                | Lab: [Streams](https://iu.instructure.com/courses/1996307/assignments/12670249), [submit](https://autograder.sice.indiana.edu/web/project/323)
Sep. 13 | [Iterators, Merge Sort, Stack, Queue, Set](./lectures/Sep-13.md) | Ch. 3 sec. 6-7 |
Sep. 15 | [Binary Trees](./lectures/Sep-15.md) | Ch. 4 sec. 1-2                 | Assignment: [MergeSort](https://iu.instructure.com/courses/1996307/assignments/12679749), [submit](https://autograder.sice.indiana.edu/web/project/324)
Sep. 17 |              |                                | Lab: [BinaryTree](https://iu.instructure.com/courses/1996307/assignments/12685293) [submit](https://autograder.sice.indiana.edu/web/project/325), Quiz
Sep. 20 | [Binary Search Trees](./lectures/Sep-20.md) | Ch. 4 sec. 3 and 7      | 
Sep. 22 | [Loop Invariants, Correctness of Recursive Functions](./lectures/Sep-22.md) | Ch. 4 sec. 4             | Assignment: [AVLTree](https://iu.instructure.com/courses/1996307/assignments/12693260) [submit](https://autograder.sice.indiana.edu/web/project/328)
Sep. 24 |              |                                | Lab: [NextPrevBinaryTree](https://iu.instructure.com/courses/1996307/assignments/12699457) [submit](https://autograder.sice.indiana.edu/web/project/329)
Sep. 27 | [AVL Trees](./lectures/Sep-27.md) |
Sep. 29 | [BST and AVL Remove, Segment Intersection](./lectures/Sep-29.md) |
Oct. 1  |              |                                | [Project: Segment Intersection](https://iu.instructure.com/courses/1996307/assignments/12720024) [submit](https://autograder.sice.indiana.edu/web/project/332)
Oct. 4  | [Hash tables](./lectures/Oct-4.md)  | Ch. 5 sec. 1-3 |
Oct. 6  | [Heaps and Priority Queues](./lectures/Oct-6.md) | Ch. 6 sec. 1-4, 9  |
Oct. 8  | **Fall Break**   |                             |
Oct. 11 | [Review for Midterm Exam](./lectures/Oct-11.md) |                      |
Oct. 13 | **Midterm Exam** |                             | 
Oct. 15 |                  |                             | Lab: [HashTable](https://iu.instructure.com/courses/1996307/assignments/12745883) [submit](https://autograder.sice.indiana.edu/web/project/334)
Oct. 18 | Binomial Queues  | Ch. 6 sec. 8                | 
Oct. 20 | Quicksort        | Ch. 7, sec. 1-7             | 
Oct. 22 |                  |                             | Lab: BinomialHeap
Oct. 25 | Sorting in Linear Time    | Ch. 7, sec. 11     | 
Oct. 27 | Graphs, Topological Order | Ch. 9, sec. 1-2    | 
Oct. 29 |                  |                             | Lab: QuickSort
Nov. 1  | Breadth-first search | Ch. 9, sec. 3           | 
Nov. 3  | Shortest Paths | Ch. 8                         | 
Nov. 5  |                  |                             | Lab: ConnectedComponents
Nov. 8  | Union-Find       | Ch. 9, sec. 5
Nov. 10 | Minimum Spanning Trees | Ch. 10, sec. 1
Nov. 12 |                  |                             | Project: RoutingWires
Nov. 15 | Greedy Algorithms | Ch. 10, sec. 2
Nov. 17 | Dynamic Programming | Ch. 10, sec. 3
Nov. 19 |                  |                             | Lab: HuffmanCoding
Nov. 22-26 | **Thanksgiving Break**
Nov. 29 | DNA Alignment    | 
Dec. 1  | Backtracking     | Ch. 10, sec. 5
Dec. 3  |                  |                             | Lab: SeamCarving
Dec. 6  | TBD              |
Dec. 8  | Review for Final Exam | 
Dec. 10 |                  |                             | Project: DNA Alignment
Dec. 17 | **Final Exam** | 12:35-2:35 PM in class

**Resources**

* [Autograder](https://autograder.sice.indiana.edu/web/course/25) for
  submitting coding assignments.

* Code Editor and Debugger: 
  [IntelliJ IDEA](https://www.jetbrains.com/idea/download) (Community Edition)

**Grade Weighting**

* Assignments (30%)
* Quizzes (10%)
* Midterm Exam (25%)
* Final Exam (35%)


**COVID Policies and Precautions**

All students signed the Community Responsibility Acknowledgement
(CRA).  Your agreement to the public health measures in the CRA is a
condition of physical presence on the campus this fall.  Included in
that commitment were the requirements to be vaccinated, for wearing
masks in all IU buildings, and maintaining social distancing in all IU
buildings. These are classroom requirements.

These requirements are necessary for us to protect each other.

Therefore, if a student is present in a class without a mask, the
student will be asked to put on a mask and I will report the student
to the Division of Student Affairs: Office of Student Conduct.

* If a student refuses to put a mask on after being instructed to do
  so, the instructor may end the class immediately, and contact the
  Office of Student Conduct.  Violation of the mask rule is a threat
  to public safety within the meaning of the Summary Suspension Policy.
* If a student comes to class without a mask twice, the student’s
  final grade will be reduced by one letter (e.g., from an A to a B,
  for instance).
* If the student comes to class without a mask three times, the
  student will be withdrawn from the class without refund of tuition
  and reported to the Office of Student Conduct.
* If Student Conduct receives three cumulative reports from any
  combination of instructors or staff members that a student is not
  complying with the requirements of masking and physical distancing,
  the student will be summarily suspended from the university for the
  semester.

If you have a positive COVID-19 test, have COVID-like symptoms, or
have been instructed to quarantine you should not attend class.
Please work with the instructor to determine a path to continue your
progress in the class during these absences.

**Bias-Based Incident Reporting.**

Bias-based incident reports can be made by students, faculty and
staff. Any act of discrimination or harassment based on race,
ethnicity, religious affiliation, gender, gender identity, sexual
orientation or disability can be reported through any of the options:

1) email biasincident@indiana.edu or incident@indiana.edu;

2) call the Dean of Students Office at (812) 855-8188 or

3) use the IU mobile App (m.iu.edu). Reports can be made anonymously.

**Dean on Call.**

The Dean of Students office provides support for students dealing with
serious or emergency situations after 5 p.m. in which an immediate
response is needed and which cannot wait until the next business
day. Faculty or staff who are concerned about a student’s welfare
should feel free to call the Dean on Call at (812) 856-7774. This
number is not to be given to students or families but is for internal
campus use only. If someone is in immediate danger or experiencing an
emergency, call 911.

**Boost.**

Indiana University has developed an award-winning smartphone app to
help students stay on top of their schoolwork in Canvas. The app is
called “Boost,” it is available for free to all IU students, and it
integrates with Canvas to provide reminders about deadlines and other
helpful notifications. For more information, see
https://kb.iu.edu/d/atud.

**Counseling and Psychological Services.**

CAPS has expanded their services. For information about the variety of
services offered to students by CAPS visit:
http://healthcenter.indiana.edu/counseling/index.shtml.


**Disability Services for Students (DSS).**

The process to establish accommodations for a student with a
disability is a responsibility shared by the student and the DSS
Office. Only DSS approved accommodations should be utilized in the
classroom. After the student has met with DSS, it is the student’s
responsibility to share their accommodations with the faculty
member. For information about support services or accommodations
available to students with disabilities and for the procedures to be
followed by students and instructors, please visit:
https://studentaffairs.indiana.edu/disability-services-students/.

**Reporting Conduct and Student Wellness Concerns.**

All members of the IU community including faculty and staff may report
student conduct and wellness concerns to the Division of Student
Affairs using an online form located at
https://studentaffairs.indiana.edu/dean-students/student-concern/index.shtml.

**Students needing additional financial or other assistance.**

The Student Advocates Office (SAO) can help students work through
personal and academic problems as well as financial difficulties and
concerns. SAO also assists students working through grade appeals and
withdrawals from all classes. SAO also has emergency funds for IU
students experiencing emergency financial crisis
https://studentaffairs.indiana.edu/student- advocates/.

**Disruptive Students.**

If instructors are confronted by threatening behaviors from students
their first obligation is to insure the immediate safety of the
classroom. When in doubt, call IU Police at 9-911 from any campus
phone or call (812) 855-4111 from off-campus for immediate or
emergency situations. You may also contact the Dean of Students Office
at (812) 855-8188. For additional guidance in dealing with difficult
student situations:
https://ufc.iu.edu/doc/policies/disruptive-students.pdf.

**Academic Misconduct.**

If you suspect that a student has cheated, plagiarized or otherwise committed academic misconduct, refer to the Code of Student Rights, Responsibilities and Conduct:
http://studentcode.iu.edu/.

**Sexual Misconduct.**

As your instructor, one of my responsibilities is to create a positive
learning environment for all students. Title IX and IU’s Sexual
Misconduct Policy prohibit sexual misconduct in any form, including
sexual harassment, sexual assault, stalking, and dating and domestic
violence. If you have experienced sexual misconduct, or know someone
who has, the University can help.

If you are seeking help and would like to speak to someone
confidentially, you can make an appointment with:

* The Sexual Assault Crisis Services (SACS) at (812) 855-8900
  (counseling services)

* Confidential Victim Advocates (CVA) at (812) 856-2469 (advocacy and
  advice services)

* IU Health Center at (812) 855-4011 (health and medical services)

It is also important that you know that Title IX and University policy
require me to share any information brought to my attention about
potential sexual misconduct, with the campus Deputy Title IX
Coordinator or IU’s Title IX Coordinator. In that event, those
individuals will work to ensure that appropriate measures are taken
and resources are made available. Protecting student privacy is of
utmost concern, and information will only be shared with those that
need to know to ensure the University can respond and assist.  I
encourage you to visit
stopsexualviolence.iu.edu to learn more.
