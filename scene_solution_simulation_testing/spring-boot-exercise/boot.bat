
rem 本目录需创建./gc_logs和./heap_dump两个文件夹

rem 设置当前时间的字符串
set current_time_str=%date:~0,4%-%date:~5,2%-%Date:~8,2%-%Time:~0,2%-%time:~3,2%-%time:~6,2%

rem JVM启动参数
rem 简单描述：
rem -XX:MaxGCPauseMillis，GC停顿时间的目标，默认是200
rem -XX:InitiatingHeapOccupancyPercent，内存使用达到整个堆的占比时开始GC，默认是45
rem -XX:NewRatio，新生代与老年代的比例，默认是2
rem -XX:SurvivorRatio，伊甸区与幸存区的比例，默认是8
rem -XX:MaxTenuringThreshold，一个存活对象需经历多少次GC才能晋升到老年代区，默认是15
set JAVA_OPTS=-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:./gc_logs/gc-%current_time_str%.log
set JAVA_OPTS=%JAVA_OPTS% -server -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:InitiatingHeapOccupancyPercent=45 -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
set JAVA_OPTS=%JAVA_OPTS% -Xmx512m -Xms512m -Xss256k
set JAVA_OPTS=%JAVA_OPTS% -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heap_dump/

rem 启动JVM
java %JAVA_OPTS% -jar spring-boot-exercise-0.0.1-SNAPSHOT.jar