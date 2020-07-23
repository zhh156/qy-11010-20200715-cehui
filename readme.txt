当业务逻辑复杂的时候，service层使用service的时候不能直接使用注入的方式，
应该通过在controller将需要的service通过参数传到需要使用该service层中。


在做本项目中遇到了哪些难点？解决的思路是什么？
    1.在使用springcloud架构完成项目时，封装redis的时候发现无法实现通用，因为JedisCluster只能接收String类型或者byte数组类型的key值
         并不符合架构的标准，最终可以把Object对象转换为字节数组来进行处理这个问题
         解决的思路：写一个方法，返回类型为字节数组。首先对需要传入redis中的值进行判断看是否是字节数组，如果是的话可以直接进行返回；
            如果不是，使用RedisSerializer中的serializer中的方法对值进行转换为字节数据的形式
    2.对不同种类的多文件进行上传
         解决思路：
    3.使用springCloud框架的时候，发现前端无法将文件通过feign传入后端
         解决思路：