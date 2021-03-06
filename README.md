# momomo
[![Build Status](https://travis-ci.com/4806/momomo.svg?token=fuipTATSy6X1FH6yGRVP&branch=master)](https://travis-ci.com/4806/momomo)

## Overview
momomo is an application used for tracking competencies of employees of an organization. Competencies are associated with
specific learning materials, and as users go through their learning materials they gain competencies in certain areas.
Certain competencies also have sub-competencies, which means that a user is not able to learn a skill before learning other
prerequisite skills. If an employee is an assessor, they are able to query what competencies a given employee has. They are
also able to check the prerequisite tree for a given competency, and check which employees have a given competency.

## Sprint 1
For Sprint 1, we are going to build a simple user interface that allows a user to add employees and view all employees in
a list. This will interact with the model components that we made for this sprint, and use the repositories for storing and
retrieving data from the persistence database. We are also making it so there are multiple different types of learning 
materials, such as books, conferences, and courses. 

## Sprint 2
For Sprint 2, we will be expanding on the "skills" functionality. When a user "learns" from a learning material (i.e. a learning materials is added to a user), they will also "learn" the skills associated with that learning material. There will also be windows to review what skills an employee has and which learning materials they have "learned" from. There will also be a login page, and different priveleges associated with each of the user roles. A basic user can inspect users, an assessor can assign learning materials and skills, and an admininistrator can create and remove users.

## Sprint 3
For Sprint 3, we will be expanding on the Sub-skill features. Skills will be able to have sub-skills and prerequisites. If a learning material is applied to a user with a skill that is a sub-skill of a skill not yet learned by that user, an error is thrown and the user cannot "learn" without the prerequisite knowledge. We will also be refactoring the GUI to add CSS styling to improve the user experience. The final touches will be added to allow for a more stable release, with more unit tests and status popups.

## Heroku App Link
http://moskilla.herokuapp.com/
