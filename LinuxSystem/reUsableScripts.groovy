def triggerEmail(){
emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                    recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],
                    subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
                    
}

def checkOutRepo(String repo, branch, String credentials){
    git repo,branch,credentials
}

def uploadToArtifactory(String serverName, String patternType, String targetPath){
 def server = Artifactory.server serverName
  def uploadSpec = """{
                    "files": [{
                       "pattern": "patternType",
                       "target": "targetPath"
                    }]
                 }"""
                 
  server.upload(uploadSpec)
}

/*def uploadArti(){
  def uploadSpec = """{
                    "files": [{
                       "pattern": "target/*.war",
                       "target": "example-repo-local/Devops301_${env.BUILD_NUMBER}/"
                    }]
                 }"""
}*/

return this
