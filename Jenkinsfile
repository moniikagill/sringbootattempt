#! groovy
@Library('pipelineLib@release-1.1.13') _
pipeline {
 agent {
 label "any" //"windows"
 }
 options {
 skipDefaultCheckout()
 }
 stages {
 stage('Clean and Checkout') {
 steps {
 cleanWs()
 // Replace 'checkout scm' line with the below line to checkout a branch instead of 'master':
 // git branch: '<branchname>', url: 'https://bitbucket.nsp.ird.govt.nz/scm/intws/dns-query-test.git'
 checkout scm
 }
 }
 stage('Test') {
 steps {
 script {
 runSpringCucumberTests(this, "harnessEncryptionKey", springConfigName, springProfile,
 cucumberTags, customProperties)
 }
 }
 }
 }
 post {
 always {
 script {
 archiveArtifacts artifacts: 'build/cucumber-reports/cucumber-results-agg-test-results.html'
 junit '**/build/cucumber-reports/*.xml'
 String emailRecipients = "mona.nannu"
 sendStatusNotification(emailRecipients, env.JOB_NAME, env.BUILD_NUMBER, currentBuild, "")
 }
 }
 }

}

// If specified the env.SPRINGCONFIGNAME parameter value selects the Spring config files to be used
String getSpringConfigName() {
 env.SPRINGCONFIGNAME ?: ""
}

// If specified the SPRINGPROFILE parameter value selects an active profile to be applied to the Spring Config files
String getSpringProfile() {
 env.SPRINGPROFILE ?: ""
}

// The custom system properties are added to the Spring config/profile properties and cucumber tags when ./gradle
// bootRun is invoked. The custom properties are initialized with properties from the Jenkins global config.
Map getCustomProperties() {
 // Global config file
 Map jenkinsGlobalConfig = getJenkinsGlobalConfig(env.JOB_BASE_NAME)
 echo "Applying the following system properties from jenkins-global-config: $jenkinsGlobalConfig"
 // Replace next line with jenkinsGlobalConfig + [myProp1 : <myProp1Val>, myProp2 : <myProp2Val>] to add more properties
 jenkinsGlobalConfig
}

// env.TAGS is a space-separated list of cucumber tags to be ANDed together. e.g: "@live @test"
// returns a tags option for use in the cucumber.options property e.g: "--tags @live --tags @test"
String getCucumberTags(){
 List tags = (env.TAGS ?: "" ).split()
 tags.collect{"--tags $it"}.join(" ")
}