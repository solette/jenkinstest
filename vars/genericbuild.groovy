// if node ('') use any slave DOTNET
// bat used in windows jenkins, for unix use sh

def call(Map config=[:]) {

node ('') {

		stage('SCM') {
			echo 'Building...'
            git branch: '${branch}', url: 'https://github.com/solette/jenkinstest.git'
        }

        stage('Build') {
			echo 'Building...'
            bat '''dotnet --version''' 
            bat '''dotnet build ConsoleApp1'''
            echo 'Building for develop...'
			releasenotes(changes: "true");
        }

        stage('test') {
			echo 'Testing...'
		}

        stage('Deploy') {
			echo 'Deploying...'
        }         
 
	}

}
