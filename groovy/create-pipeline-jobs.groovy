def checkOutPipelineCode(env) {
    stage ( "Checkout pipeline code repository" ) { 
        def GITBRANCH = env.PIPELINE_REPO_BRANCH == null ? "master" : env.PIPELINE_REPO_BRANCH
        env.PIPELINE_REPO_NAME = (env.PIPELINE_REPO_URL).substring((env.PIPELINE_REPO_URL).lastIndexOf("/")+1)
        dir ( "${WORKSPACE}/${env.PIPELINE_REPO_NAME}" ) {
           echo "credentialsId: ${env.GIT_TOKEN}, url: ${env.PIPELINE_REPO_URL} , branch: ${GITBRANCH}"
           git credentialsId: "${env.GIT_TOKEN}", url: "${env.PIPELINE_REPO_URL}" , branch: "${GITBRANCH}"
        }
    }
}
