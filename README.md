# A simple project to study how to use spring boot and vue.js to achieve the following basic features:
    * data CRUD (spring boot JPA)
    * Displaying data in a html table using bootstrap styles (html, bootstrap)
    * search data by its fields(pure js/vue.js)
    * pagination(pure js/vue.js)
    * treeview CRUD(vue.js)

## prepare:
    1. Download OpenJDK and install it.
        https://learn.microsoft.com/zh-cn/java/openjdk/download
        Here we download OpenJDK 21 and install it.
    
    2. Open https://start.spring.io/, choose the following settings:
        a. Project -> Gradle(Default)
        b. Spring Boot -> 3.3.4(Default)
        c. Project Metadata:
            Group -> com.example
            Artiface -> springboot.demo
            Name -> springboot.demo
            Description -> Demo project for Spring Boot
            Package name -> com.example.springboot.demo
            Packaging -> War
            Java -> 21
        d. Dependencies:
            Spring Web
            Spring Boot DevTools
            Spring Data JPA
            Thymeleaf
            Lombok
            Validation
    3. Add the following dependencies:
        implementation 'org.xerial:sqlite-jdbc:3.46.1.3'
	    implementation 'org.hibernate:hibernate-core:6.6.1.Final'
	    implementation 'org.hibernate.orm:hibernate-community-dialects:6.6.1.Final'
## how to run:
    1. ./gradlew.bat bootRun
