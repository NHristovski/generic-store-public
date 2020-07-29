pipeline {

    environment {
        registry = "nikolahristovski/generic-store"
        registryCredential = 'dockerhub'
        dockerImage = ''

    }
    agent any

    stages {

        stage('Maven build') {
            steps {
                sh "/opt/maven/bin/mvn -T 1C clean install"
            }
        }

        stage('ll test again') {
            steps {
                sh '''
                    docker_username=nikolahristovski
                    cat /var/lib/jenkins/docker_password.txt | docker login --username $docker_username --password-stdin

                    project_version=$(/opt/maven/bin/mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression=project.version -q -DforceStdout)

                    echo "project version is $project_version"

                    docker_files=$(find -name "Dockerfile")

                    base_dir=$(pwd)
                    for df in $docker_files
                    do
                            image_name="${docker_username}/$(basename $(dirname $df)):${project_version}"
                            echo "image name is $image_name"
                           docker rmi $image_name

                           cd $(dirname $df)
                           docker build . -t $image_name
                           cd $base_dir
                    done
                '''
            }
        }


//        stage('Building docker image') {
//            steps {
//                script {
//                    dockerImage = docker.build registry + ":latest"
//                }
//            }
//        }
//
//        stage('Deploy Image') {
//            steps {
//                script {
//                    docker.withRegistry('', registryCredential) {
//                        dockerImage.push()
//                    }
//                }
//            }
//        }
//
//        stage('Remove Unused docker image') {
//            steps {
//                sh "docker rmi $registry:latest"
//            }
//        }
//
//
//        stage('Pull docker image on host') {
//            steps {
//                sh 'ssh -o StrictHostKeyChecking=no nhristov@10.10.10.57 "cat ~/docker_password.txt | docker login --username nikolancaid --password-stdin"'
//
//                script {
//                    try {
//                        sh 'ssh -o StrictHostKeyChecking=no nhristov@10.10.10.57 "docker rm -f service-registry"'
//                    } catch (Exception e) {
//                        sh 'echo ======= Failed to delete container service-registry'
//                    }
//                }
//
//                sh 'ssh -o StrictHostKeyChecking=no nhristov@10.10.10.57 "docker pull nikolancaid/service-registry:latest"'
//            }
//        }
//
//        stage('Start the application') {
//            steps {
//                sh 'ssh -o StrictHostKeyChecking=no nhristov@10.10.10.57 "docker run --name service-registry --network=host -d nikolancaid/service-registry:latest"'
//            }
//        }
    }
}
