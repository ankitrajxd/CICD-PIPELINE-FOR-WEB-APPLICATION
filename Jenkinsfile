def gv

pipeline{
    agent any

// if you want to push a new version of your app, just make changes in html, upgrade the tag.
environment {
    TAG = '1.0.5' // tag for your image
    SERVER_IP = "137.184.23.94"  // ip of your remote server
    SERVER_USER = "root"  // username of your remote server
    REPO_NAME = "ankitraz/mywebsite"  // your dockerhub repo name
    APP_NAME = "mywebsite" // name of your app
}

    stages{
        stage("init"){
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
        }

        stage("Build"){
            steps{
                script{
                    gv.Build()
                }
            }
        }

        stage("Test"){
            steps{
                script{
                    gv.Test()
                    echo "Test by webhook from github"
                }
            }
        }

        stage("Deploy"){
            steps{
                script{
                    gv.Deploy()
                }
            }
        }
    }
}
