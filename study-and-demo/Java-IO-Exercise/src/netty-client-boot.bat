:: 关闭回显，只显示结果
@echo off
:: 设置执行文件名为标题
title %0

:: 在pom.xml所在目录下执行mvn -f pom.xml dependency:copy-dependencies，下载依赖JAR包
java -cp "..\dependency\*;.\classes;%CLASSPATH%" com.nicchagil.nettyexercise.client.MyClient

:: 执行完等待输入
pause