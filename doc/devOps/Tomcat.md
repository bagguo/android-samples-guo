conf/server.xml  修改默认8080端口

404 file not found

1.部署
tomcat/conf/server.xml文件配置j2ee web目录
context标签位于host标签内
<!-- <Context path="/" docBase="/Users/guo/WorkSpace/ServerProject/1.j2ee/j2ee/web" debug="0" reloadable="false" /> -->

# use
```
To start tomcat now and restart at login:
  brew services start tomcat
  
Or, if you don't want/need a background service you can just run:
  /opt/homebrew/opt/tomcat/bin/catalina run
```