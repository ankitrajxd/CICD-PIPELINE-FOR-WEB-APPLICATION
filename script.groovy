def Build(){
    echo "Building the application..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-id-pass', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        sh 'docker login -u $DOCKER_USER -p $DOCKER_PASS'  // login to dockerhub
        sh "docker build -t ${env.APP_NAME}:${env.TAG} ."  // build image with tag
        sh "docker tag ${env.APP_NAME}:${env.TAG} ${env.REPO_NAME}:${env.TAG}"  // tag image with your repo name
        /* if you want to push to dockerhub you need to build image with your repo name and tag.
        otherwise you"ll have to tag image manually like i did above.*/
        sh "docker push ${env.REPO_NAME}:${env.TAG}"  // push image to dockerhub
    }
}

def Test(){
    echo "Testing the application..." // you can add your test cases here
}

def Deploy(){
    echo "Deploying the application..."
    def makedir = "mkdir /root/new"  // creating a new directory on remote server
    def cd = "cd /root/new"  // changing directory to new directory on remote server where we copied docker-compose.yml file
    def dockerCmd = "TAG=${env.TAG} REPO_NAME=${env.REPO_NAME} docker-compose up -d" // running docker-compose up command on remote server using TAG variable and REPO_NAME variable
    
    sshagent(['jenkins-private-key']) {  // using  private ssh key of jenkins to connect to remote production server
        //copying docker-compose.yml file to remote server
        sh "ssh -o StrictHostKeyChecking=no ${env.SERVER_USER}@${env.SERVER_IP} '${makedir};${cd}'"  // running commands on remote server, you can add more commands here seperating them with semicolon
        sh "scp docker-compose.yml ${env.SERVER_USER}@${env.SERVER_IP}:/root/new"  // copy docker-compose.yml file to remote server
        sh "ssh -o StrictHostKeyChecking=no ${env.SERVER_USER}@${env.SERVER_IP} '${cd};${dockerCmd}'"  // running commands on remote server, you can add more commands here seperating them with semicolon
        
    }
}

return this