pipeline {
    agent {
        label 'agent-dso'
    }

    environment {
        AWS_DEFAULT_REGION = 'ap-southeast-3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/release/prod']],
                    userRemoteConfigs: [[
                        url: 'https://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@github.com/Jobseeker-company/hrms-api-organization.git'
                    ]]
                ])
            }
        }

        stage('Load Configuration') {
            steps {
                configFileProvider([
                    configFile(fileId: 'HRMS_Organization_Serverless', variable: 'SERVERLESS_CONFIG'),
                    configFile(fileId: 'HRMS_Organization_Env_Prod', variable: 'PARAMETERS_CONFIG')
                ]) {
                    sh '''
                        cp $SERVERLESS_CONFIG HRMS_Organization_Serverless
                        cp $PARAMETERS_CONFIG HRMS_Organization_Env_Prod
                    '''
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SAM Build DSO') {
            steps {
                sh 'sam build --template-file HRMS_Organization_Serverless'
            }
        }

        stage('SAM Deploy DSO') {
            steps {
                sh '''
                    PARAMS=$(jq -r 'to_entries | map(.key + "=" + (.value | tostring)) | join(" ")' HRMS_Organization_Env_Prod)
                    sam deploy \
                    --template-file .aws-sam/build/template.yaml \
                    --stack-name hrms-organization-function \
                    --s3-bucket hrms-organization-source \
                    --parameter-overrides "$PARAMS" \
                    --capabilities CAPABILITY_IAM CAPABILITY_AUTO_EXPAND CAPABILITY_NAMED_IAM \
                    --resolve-image-repos \
                    --no-fail-on-empty-changeset
                '''
            }
        }
        
    }
    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build and deployment completed successfully!'
        }
        failure {
            echo 'Build or deployment failed!'
        }
    }
}
