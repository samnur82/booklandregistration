pipeline{
    environment {
		registry = 'samnur82/booklandregistration'
		registryCred = 'my_dockerhub'
		dockerImage = ''
    }

    agent any

    stages {
        stage('Check Prerequisite') {
            steps {
                sh 'git --version | ant -version | java -version | docker --version'
            }
        }
        stage('clone repo') {
            steps {
               sh 'git pull https://github.com/samnur82/booklandregistration.git master'
            }
        }
        stage('Build BookLandRegistration War') {
            steps{
                sh 'ant -Dj2ee.server.home=/opt/tomcat9 -Dlibs.CopyLibs.classpath=/opt/AntAdditionalJars/org-netbeans-modules-java-j2seproject-copylibstask.jar -Dfile.reference.mysql-connector-java-8.0.17.jar=/opt/AntAdditionalJars/mysql-connector-java-8.0.17.jar clean compile dist'
            }
        }
        stage('Build BookLandRegistration Docker Image') {
            steps{
	 	script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
		}
            }
        }
    }
}

