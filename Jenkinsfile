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

        stage('Build docker images and push to dockerhub') {
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
                           
                            if [[ "$(docker images -q $image_name 2> /dev/null)" != "" ]]; then
                                docker rmi $image_name
                            fi

                           cd $(dirname $df)
                           docker build . --build-arg VERSION=$project_version -t $image_name
                           docker push $image_name
                           cd $base_dir
                    done
                '''
            }
        }
    }
}
