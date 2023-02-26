def gv

pipeline{
    agent any

// if you want to push a new version of your app, just make changes in html, upgrade the tag.
environment {
    TAG = '1.0.1'
    SERVER_IP = "64.227.108.131"
    SERVER_USER = "root"
    REPO_NAME = "ankitraz/mywebsite"
    APP_NAME = "mywebsite"
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