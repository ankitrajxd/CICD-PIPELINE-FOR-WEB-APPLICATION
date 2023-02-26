def Build(){
    echo "Building the application..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-id-pass', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        sh 'docker login -u $DOCKER_USER -p $DOCKER_PASS'  // login to dockerhub
        sh "docker build -t mywebsite:${env.TAG} ."  // build image with tag
        sh "docker tag mywebsite:${env.TAG} ankitraz/mywebsite:${env.TAG}"  // tag image with your repo name
        /* if you want to push to dockerhub you need to build image with your repo name and tag.
        otherwise you"ll have to tag image manually like i did above.*/
        sh "docker push ankitraz/mywebsite:${env.TAG}"  // push image to dockerhub
    }
}

def Test(){
    echo "Testing the application..." // you can add your test cases here
}

def Deploy(){
    echo "Deploying the application..."
    def serverIp = ${env.SERVER_IP}  // server ip where you want to deploy your app
    def makedir = "mkdir /root/new"  // creating a new directory on remote server
    def cd = "cd /root/new"  // changing directory to new directory on remote server where we copied docker-compose.yml file
    def dockerCmd = "TAG=${env.TAG} docker-compose up -d" // running docker-compose up command on remote server using TAG variable
    
    sshagent(['github-ssh-key']) {
        //copying docker-compose.yml file to remote server
        sh "scp docker-compose.yml root@${serverIp}:/root/new"  // copy docker-compose.yml file to remote server
        sh "ssh -o StrictHostKeyChecking=no root@${serverIp} '${makedir};${cd};${dockerCmd}'"  // running commands on remote server, you can add more commands here seperating them with semicolon
        
    }
}

return this