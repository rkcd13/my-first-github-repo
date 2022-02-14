#!groovy
import org.jenkinsci.plugins.workflow.support.steps.build.RunWrapper
Map  getTags(String repoURL,String gitToken)
{
    def gitTags = []
    withCredentials([[$class: 'UsernamePasswordMultiBinding',
                              credentialsId   : gitToken,
                              usernameVariable: 'GIT_USER',
                              passwordVariable: 'GIT_PASSWORD']]) {
          def gitURLwithoutHttps = repoURL.substring(8)
          gitTags = sh ( script: "git ls-remote --tags --sort=-v:refname https://\${GIT_USER}:\${GIT_PASSWORD}@${gitURLwithoutHttps}" , returnStdout: true)
    }
    return gitTags.readLines()
         .collect { it.split()[1].replaceAll('\\^\\{\\}', '').replaceAll('refs/\\w+/', '')  }
         .unique()   
}

Map  getBranches(String repoURL,String gitToken)
{
    def gitBranches = []
    withCredentials([[$class: 'UsernamePasswordMultiBinding',
                              credentialsId   : gitToken,
                              usernameVariable: 'GIT_USER',
                              passwordVariable: 'GIT_PASSWORD']]) {
          def gitURLwithoutHttps = repoURL.substring(8)
          gitBranches = sh ( script: "git ls-remote --heads --sort=v:refname https://\${GIT_USER}:\${GIT_PASSWORD}@${gitURLwithoutHttps}" , returnStdout: true)
    }
    return gitBranches.readLines()
         .collect { it.split()[1].replaceAll('\\^\\{\\}', '').replaceAll('refs/\\w+/', '')  }
         .unique()   
}

return this
