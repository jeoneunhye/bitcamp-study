<Gradle을 이용한 디렉토리 생성>

PS C:\Users\user> cd git/practice
PS C:\Users\user\git\practice> gradle init

Select type of project to generate:
  1: basic
  2: application
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 2

Select implementation language:
  1: C++
  2: Groovy
  3: Java
  4: Kotlin
  5: Swift
Enter selection (default: Java) [1..5] 3

Select build script DSL:
  1: Groovy
  2: Kotlin
Enter selection (default: Groovy) [1..2] 1

Select test framework:
  1: JUnit 4
  2: TestNG
  3: Spock
  4: JUnit Jupiter
Enter selection (default: JUnit 4) [1..4] 1

Project name (default: practice):

Source package (default: practice): com.eunhye.cj


> Task :init
Get more help with your project: https://docs.gradle.org/6.0.1/userguide/tutorial_java_projects.html

BUILD SUCCESSFUL in 2m 5s
2 actionable tasks: 2 executed
PS C:\Users\user\git\practice> /tree /a /f 

C:.
|   .gitattributes
|   .gitignore
|   build.gradle
|   gradlew
|   gradlew.bat
|   settings.gradle
|
+---.gradle
|   +---6.0.1
|   |   |   gc.properties
|   |   |
|   |   +---executionHistory
|   |   |       executionHistory.bin
|   |   |       executionHistory.lock
|   |   |
|   |   +---fileChanges
|   |   |       last-build.bin
|   |   |
|   |   \---fileHashes
|   |           fileHashes.bin
|   |           fileHashes.lock
|   |
|   \---buildOutputCleanup
|           buildOutputCleanup.lock
|           cache.properties
|           outputFiles.bin
|
+---gradle
|   \---wrapper
|           gradle-wrapper.jar
|           gradle-wrapper.properties
|
\---src
    +---main
    |   +---java
    |   |   \---com
    |   |       \---eunhye
    |   |           \---cj
    |   |                   App.java
    |   |
    |   \---resources
    \---test
        +---java
        |   \---com
        |       \---eunhye
        |           \---cj
        |                   AppTest.java
        |
        \---resources