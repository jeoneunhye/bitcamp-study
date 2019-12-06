<Gradle init 명령어를 이용한 디렉토리 생성>

1. powershell에서 만들고자 하는 상위 폴더 경로로 들어가 $ mkdir 프로젝트명 입력하여 폴더 생성
  (C:\Users\user> cd git, PS C:\Users\user\git> mkdir practice)
2. 생성한 폴더로 들어가 $ gradle init
  (C:\Users\user\git> cd practice, C:\Users\user\git\practice> gradle init)
3. 해당하는 선택사항에 답하기
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

Select build script DSL:                    -> 설정파일(build.gradle) 만들 때 특정 언어를 무엇으로 할 건지? (Domain Specific Language)
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

Source package (default: practice): com.eunhye.cj   -> source package 이름 설정 : 보통 팀이름.제품명


> Task :init
Get more help with your project: https://docs.gradle.org/6.0.1/userguide/tutorial_java_projects.html

BUILD SUCCESSFUL in 2m 5s
2 actionable tasks: 2 executed


PS C:\Users\user\git\practice> /tree /a /f 
  - 디렉터리 구조

C:.
|   .gitattributes
|   .gitignore
|   build.gradle
|   gradlew         : Unix, Mac 계열에서 실행 가능한 스크립트
|   gradlew.bat     : 윈도우에서 실행 가능한 스크립트
|   settings.gradle
|
+---.gradle   : Gradle 실행과 관련된 파일이 보관되는 곳
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
+---gradle    : Gradle 실행에 필요한 환경(실행 파일과 스크립트)을 정리한 wrapper 파일
|   \---wrapper
|           gradle-wrapper.jar
|           gradle-wrapper.properties
|
\---src        : 소스 코드 보관 폴더
    +---main
    |   +---java
    |   |   \---com
    |   |       \---eunhye
    |   |           \---cj
    |   |                   App.java
    |   |
    |   \---resources
    \---test   : 단위 테스트용 파일
        +---java
        |   \---com
        |       \---eunhye
        |           \---cj
        |                   AppTest.java
        |
        \---resources



<Gradle을 이용한 배치 파일 생성>
powershell에서 상위 폴더 practice로 이동하여 $ gradle build
->추가로 build 폴더가 생성됨

+---build
|   +---classes
|   |   \---java
|   |       +---main
|   |       |   \---com
|   |       |       \---eunhye
|   |       |           \---cj
|   |       |                   App.class
|   |       |
|   |       \---test
|   |           \---com
|   |               \---eunhye
|   |                   \---cj
|   |                           AppTest.class
|   |
|   +---distributions
|   |       practice.tar
|   |       practice.zip
|   | 압축해제\---practice
|   |        \---practice.bat
|   |
|   +---generated
|   |   \---sources
|   |       \---annotationProcessor
|   |           \---java
|   |               +---main
|   |               \---test
|   +---libs
|   |       practice.jar
|   |
|   +---reports
|   |   \---tests
|   |       \---test
|   |           |   index.html
|   |           |
|   |           +---classes
|   |           |       com.eunhye.cj.AppTest.html
|   |           |
|   |           +---css
|   |           |       base-style.css
|   |           |       style.css
|   |           |
|   |           +---js
|   |           |       report.js
|   |           |
|   |           \---packages
|   |                   com.eunhye.cj.html
|   |
|   +---scripts
|   |       practice
|   |       practice.bat
|   |
|   +---test-results
|   |   \---test
|   |       |   TEST-com.eunhye.cj.AppTest.xml
|   |       |
|   |       \---binary
|   |               output.bin
|   |               output.bin.idx
|   |               results.bin
|   |
|   \---tmp
|       +---compileJava
|       +---compileTestJava
|       \---jar
|               MANIFEST.MF


   - 배치 파일 실행 방법
  1. practice\build\distributions\practice.zip을 여기에 압축 해제
  2-1. powershell에서 practice\bin 폴더로 이동한 뒤 $ ./practice
 (윈도우 환경은 practice.bat를 실행하지만 입력시 .bat 생략 가능)
  2-2. powershell에서 build.gradle 파일이 있는 상위 폴더 practice로 이동한 뒤 $ gradle run
  3. 기본 설정된 결과값 'Hello world.'가 도출됨