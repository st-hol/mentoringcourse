#!/bin/sh

if [ -d "/media/sf_shared/" ]; then
    git --git-dir="/media/sf_shared/.git" pull
else
    git clone https://github.com/epammephungary/spring-boot-sample-web-ui.git /media/sf_shared/
fi

if lsof -Pi :8083 -sTCP:LISTEN -t >/dev/null ; then
    echo "I will kill process on port 8083!"
    kill -9 `lsof -t -i:8083`
fi

mvn -f /media/sf_shared/pom.xml spring-boot:run & /bin/bash