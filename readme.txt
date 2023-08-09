Java version 8
Gradle version 7.4

        app.getSessionHelper().fillLoginForm(new User().withEmail("dykestones@gmail.com").withPassword("12131415$Aa"));


------------------

Gradlew start/run commands/pipelines:

-----------------
./gradlew clean test - run all tests
./gradlew clean myTest - run all tests from the task myTest (suite -> else)
./gradlew clean -Dsuite=login myTest - run tests for login from "if"
commit: 6b92aa0
----------------
./gradlew clean -Pbrowser=firefox myTest - run with firefox
./gradlew clean -Pbrowser=chrome myTest  - run with chrome
./gradlew clean newtest - run with chrome
commit 11be5ad
----------------
to add all current settings:
./gradlew clean -Pbrowser=firefox -Dsuite=login myTest
-----------------
groups:
./gradlew clean -Pbrowser=firefox -Dsuite=login -Dgroups=smoke myTest
-------------------
properties file:
 ./gradlew clean -Pbrowser=firefox -Ptarget=preprod -Dsuite=login -Dgroups=smoke myTest
---------------------------



trello.com -> prod
preprod.trello.com -> pre prod
qa-25.trello.com -> env for qa (personal)

_____________________

let str = `some var in the text ${varName}`
