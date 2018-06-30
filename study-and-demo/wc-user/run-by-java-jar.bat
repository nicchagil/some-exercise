set dateTimeStr=%date:~0,4%-%date:~5,2%-%date:~8,2%T%time:~0,2%:%time:~3,2%:%time:~6,2%

call mvn clean package -Dmaven.test.skip=true
java -jar -Xloggc:gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:./gc.log -XX:+UseG1GC -Xms1024m -Xmx1024m -Xss1024k -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./dump/ ./target/wc-user-0.0.1-SNAPSHOT.jar