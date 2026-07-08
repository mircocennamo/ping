pipeline {

    agent any


    environment {
        QUAY_URL  = "quay.company.it"
        QUAY_ORG  = "interno"
        APP_NAME  = "ping-service"

        IMAGE_NAME = "${QUAY_URL}/${QUAY_ORG}/${APP_NAME}"
    }


    stages {

        stage('Build') {
            steps {
                sh 'mvn clean package'
                sh 'mvn spring-boot:build-image'
            }
        }

        stage('Version') {
            steps {
                script {

                    env.VERSION = sh(
                            script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout',
                            returnStdout: true
                    ).trim()

                    echo "Project version : ${env.VERSION}"
                }
            }
        }

        stage('Git Metadata') {
            steps {
                script {

                    env.GIT_SHA = sh(
                            script: 'git rev-parse --short HEAD',
                            returnStdout: true
                    ).trim()

                    env.GIT_BRANCH = sh(
                            script: 'git rev-parse --abbrev-ref HEAD',
                            returnStdout: true
                    ).trim()

                    echo "Git branch : ${env.GIT_BRANCH}"
                    echo "Git commit : ${env.GIT_SHA}"
                }
            }
        }

        stage('Prepare Tags') {
            steps {
                script {

                    if (env.VERSION.contains('SNAPSHOT')) {

                        env.IS_SNAPSHOT = "true"
                        env.IMAGE_TAG = "snapshot-${env.GIT_SHA}"
                        env.PUBLISH_LATEST = "false"

                    } else {

                        env.IS_SNAPSHOT = "false"
                        env.IMAGE_TAG = env.VERSION

                        if (env.GIT_BRANCH == 'main') {
                            env.PUBLISH_LATEST = "true"
                        } else {
                            env.PUBLISH_LATEST = "false"
                        }
                    }

                    echo "Image tag      : ${env.IMAGE_TAG}"
                    echo "Publish latest : ${env.PUBLISH_LATEST}"
                }
            }
        }

        stage('Container Build') {
            steps {

                sh """
                    podman build \
                      -t ${IMAGE_NAME}:${IMAGE_TAG} .
                """

                script {

                    if (env.PUBLISH_LATEST == 'true') {

                        sh """
                            podman tag \
                              ${IMAGE_NAME}:${IMAGE_TAG} \
                              ${IMAGE_NAME}:latest
                        """
                    }
                }
            }
        }

        stage('Push Quay') {
            steps {

                withCredentials([
                        usernamePassword(
                                credentialsId: 'quay-robot',
                                usernameVariable: 'QUAY_USER',
                                passwordVariable: 'QUAY_TOKEN'
                        )
                ]) {

                    sh """
                        podman login \\
                        ${QUAY_URL} \\
                        -u $QUAY_USER \\
                        -p $QUAY_TOKEN


                        podman push ${IMAGE_NAME}:${IMAGE_TAG}
                    """

                    script {

                        if (env.PUBLISH_LATEST == 'true') {

                            sh """
                                podman push ${IMAGE_NAME}:latest
                            """
                        }
                    }
                }
            }
        }


    }

    post {

        always {

            junit(
                    allowEmptyResults: true,
                    testResults: '**/target/surefire-reports/*.xml'
            )

            sh """
                podman rmi ${IMAGE_NAME}:${IMAGE_TAG} || true
            """

            script {

                if (env.PUBLISH_LATEST == 'true') {

                    sh """
                        podman rmi ${IMAGE_NAME}:latest || true
                    """
                }
            }
        }

        success {

            echo """
==================================================

BUILD COMPLETED

Branch:
${env.GIT_BRANCH}

Version:
${env.VERSION}

Image:
${IMAGE_NAME}:${IMAGE_TAG}

GitOps Repository Updated

ArgoCD will show:
OUT OF SYNC

Deployment remains MANUAL.

==================================================
"""
        }

        failure {
            echo "Pipeline failed."
        }
    }
}