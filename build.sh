#!/usr/bin/env sh

clear;

print() {
  echo -e "[INFO] --- ${1}\n";
}

APP="target/jobsphere*SNAPSHOT.jar";
export JAVA_HOME="/usr/";

print "Building the App";
if [ -f "/usr/bin/javac" ]; then {
  print "Java is installed";
  if [ -f .env ]; then {
    if [ -f "mvnw" ]; then {
        print "Compiling app";
        sh mvnw clean package;
        if [ -f ${APP} ]; then {
            print "Successfully compiled";
            if [ -f "Dockerfile" ]; then {
              print "Building Container...";
              docker buildx build -t jobsphere-app .;
              docker run -p 6969:6969 jobsphere-app;
            } fi
        } fi
    } else {
      print "mvnw missing";
      print "quitting...";
      exit 0;
    } fi
  } else {
    print "config file missing";
    print "quitting...";
    exit 0;
  } fi
} else {
  print "didn't find java";
  print "quitting...";
  exit 0;
} fi