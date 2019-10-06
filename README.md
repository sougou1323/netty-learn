# netty学习笔记

参考书籍：《netty权威指南》（第二版） 

- 第三章： 时间服务器
    
- 第四章： 粘包和拆包
    - LineBasedFrameDecoder 以换行符为结束标志的解码器
    - StringDecoder 将接收到的对象转换成字符串

- 第五章： 分隔符和定长解码器
    - DelimiterBasedFrameDecoder 以分隔符做结束标志的消息的解码
    - FixedLengthFrameDecoder 对定长消息的解码
    
- 