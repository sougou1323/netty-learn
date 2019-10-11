 
 protobuf 使用步骤
 
 - 1 编写proto文件，定义数据结构
 
 - 2 安装protoc
 
 - 3 运行以下脚本，生成对应java类
 
 ~~~ shell
 protoc --java_out=./src/main/java/ ./src/main/java/_8/SubscribeResp.proto
 ~~~
 
 - 4 导包 注意版本要和安装的protoc版本一致
 ~~~ maven
 <!-- https://mvnrepository.com/artifact/com.google.protobuf/protobuf-java -->
 <dependency>
     <groupId>com.google.protobuf</groupId>
     <artifactId>protobuf-java</artifactId>
     <version>{protobuf.version}</version>
 </dependency>
 ~~~
 
 - 5 开始使用