def buildJar() {
	echo "building the application..."
    sh 'mvn package'
}

def buildImage() {
	echo "building the docker image..."
	withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
	    sh 'docker build -t fomar123/my-rep:jma-3.0 .'
	    sh 'echo $PASS | docker login -u $USER --password-stdin'
	    sh 'docker push fomar123/my-rep:jma-3.0'
	}
}

def deployApp() {
	echo 'echo "deploying the application..."'
}

return this 
