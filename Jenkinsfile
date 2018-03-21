node {

    // env setup
    env.JAVA_HOME = tool 'jdk8_60'
    env.PATH = "${env.JAVA_HOME}/bin:${env.PATH}"

    step([$class: 'WsCleanup'])

    stage('Checkout') {
        git branch: "master", url: 'git@github.com:rsvanda/lunchbox.git'
    }

    stage('Build') {
        sh '/bin/bash ./gradlew build'
    }

    stage('Run') {
        sh 'java -jar build/libs/lunchbox-0.0.1-SNAPSHOT.jar'
    }
}
