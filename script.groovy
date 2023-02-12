def Build(){
    echo "Building the application..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-id-pass', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        sh 'docker login -u $DOCKER_USER -p $DOCKER_PASS'
        sh "docker build -t mywebsite:1.1 ."
        sh "docker tag mywebsite:1.0 ankitraz/mywebsite:1.1"
        /* if you want to push to dockerhub you need to build image with your repo name and tag.
        otherwise you"ll have to tag image manually like i did above.*/
        sh "docker push ankitraz/mywebsite:1.0"
    }
}

def Test(){
    echo "Testing the application..."
}

def Deploy(){
    echo "Deploying the application..."
    def dockerCmd = "docker run -d  -p 81:80 ankitraz/mywebsite:1.1"
    sshagent(['github-ssh-key']) {
        sh "ssh -o StrictHostKeyChecking=no root@206.189.140.139 ${dockerCmd}"
    }
}

return this