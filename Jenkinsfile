def gv

pipeline{
    agent any

// if you want to push a new version of your app, just make changes in html, upgrade the tag and allocate a different port for it.
environment {
    TAG = '1.0.1'
    PORT = '80' // change this to 81 if you want to run multiple instances of your app.
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