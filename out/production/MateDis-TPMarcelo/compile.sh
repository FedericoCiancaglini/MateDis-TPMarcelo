#!/usr/bin/env bash

cd src
jflex jflex.flex
java -jar /Users/Florencia/projects/MateDis-TPMarcelo/src/java-cup-11b.jar parser.cup