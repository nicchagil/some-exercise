:: 关闭回显，只显示结果
@echo off
:: 设置执行文件名为标题
title %0

:: 在pom.xml所在目录下执行mvn -f pom.xml dependency:copy-dependencies，下载依赖JAR包
:: 拷贝此脚本到class目录下，执行所调用的Java类
java -cp "..\dependency\*;.\classes;%CLASSPATH%" com.nicchagil.ioexercise.nio.server.NIOServer

:: 执行完等待输入
pause
