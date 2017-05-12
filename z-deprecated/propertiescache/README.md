propertiescache
===================

Just cache the properties file in the project for easy to use.

将项目中properties文件在项目启动时缓存起来，待使用时方面获取。
1、properties文件将以“文件名”作为key值缓存；
2、properties文件内的键值对不变。

调试方法：
浏览器中输入相应请求地址，查看控制台是否正确打印对应的value值。
http://localhost:xxxx/propertiescache/DebugPropertiesCacheServlet?pfile=文件名&key=键值
