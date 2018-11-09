#!/usr/bin/env bash

cd src
jflex jflex.flex
java -jar /Users/fede/sandbox/matedis-TPE-master/matedis-TPE-master/src/java-cup-11b.jar parser.cup