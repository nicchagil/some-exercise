#/bin/bash

ps -ef | grep $1 | cut -c 9-15 | xargs kill -9
