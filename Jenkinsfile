def gv

pipeline{
    agent any

    parameters{
        choice(name: 'Version', choices: ['1.0', '1.1', '1.2', '1.3', '1.4'], description: 'Version of the application')
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