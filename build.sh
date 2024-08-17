#!/bin/bash
set -e

clear

print() {
  echo -e "[INFO] --- ${1}\n"
}

check_command() {
  command -v "$1" >/dev/null 2>&1 || { print "$1 is required but not installed."; exit 1; }
}

APP="target/jobsphere*SNAPSHOT.jar"
export JAVA_HOME="/usr/"

print "Checking required tools"
check_command "java"
check_command "docker"
check_command "sh"

print "Building the App"
if [ -f ".env" ] && [ -f "mvnw" ]; then
    print "Compiling app"
    sh mvnw clean package
    if [ -f "${APP}" ]; then
        print "Successfully compiled"
        if [ -f "Dockerfile" ]; then
            print "Building Docker image"
            docker buildx build -t jobsphere-app .
            print "Build complete. To run the container, use: docker run -p 6969:6969 jobsphere-app"
        else
            print "Dockerfile is missing"
            exit 1
        fi
    else
        print "Jar file not found. Compilation may have failed."
        exit 1
    fi
else
    print ".env file or mvnw missing"
    exit 1
fi
