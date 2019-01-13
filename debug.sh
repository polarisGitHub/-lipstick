#!/bin/sh

java -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n -jar build/libs/LIPSTICK-0.1.0.jar

