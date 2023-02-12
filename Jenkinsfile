def gv

pipeline{
    agent any

// if you want to push a new version of your app, just upgrade the tag and allocate a different port of it.
environment {
    TAG = '1.0.0'
    PORT = '80'
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