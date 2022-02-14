#!groovy
// vars/global.groovy
import groovy.transform.Field
@Field String STEP_NAME = getClass().getName() //sample invoke method

@Field String JENKINS_ARTIFACTORY_HOST_KEY = "Artifactory prod"
@Field String DOCKER_ARTIFACTORY_HOST = "docker.artifactory.dhl.com"
@Field String ARTIFACTORY_HOST = "artifactory.dhl.com"
@Field String JENKINS_ARTIFACTORY_CREDENTIAL = "rkcd13_token"
