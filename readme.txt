当业务逻辑复杂的时候，service层使用service的时候不能直接使用注入的方式，
应该通过在controller将需要的service通过参数传到需要使用该service层中。


在做本项目中遇到了哪些难点？解决的思路是什么？
    1.在使用springcloud架构完成项目时，封装redis的时候发现无法实现通用，因为JedisCluster只能接收String类型或者byte数组类型的key值
         并不符合架构的标准，最终可以把Object对象转换为字节数组来进行处理这个问题
         解决的思路：写一个方法，返回类型为字节数组。首先对需要传入redis中的值进行判断看是否是字节数组，如果是的话可以直接进行返回；
            如果不是，使用RedisSerializer中的serializer中的方法对值进行转换为字节数据的形式
    2.对不同种类的多文件进行上传
         解决思路：可以通过RequestParam注解通过不同的参数传递不同的类型的文件数组，从而在service层对不同数组的类型进行指定
    3.使用springCloud框架的时候，发现前端无法将文件通过feign传入后端
         解决思路：
            在消费者的controller层，通过PostMapping对路径进行映射，文件数组使用@RequestParam注解进行接收
            @PostMapping("/updatePrincipalById")
                public ResultData updatePrincipalById(@RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("idType") String idType, @RequestParam("idNumber") String idNumber,
                                                      @RequestParam("age")Integer age, @RequestParam("sex") Integer sex, @RequestParam("workYear") Integer workYear, @RequestParam("duty") String duty,
                                                      @RequestParam("title")String title, @RequestParam("mappingYear") Integer mappingYear, @RequestParam("major") String major, @RequestParam("userId") Long userId,
                                                      @RequestParam("file") MultipartFile[] file,@RequestParam("id")Long id){
                    return iProjectService.updatePrincipalById(type,name,idType,idNumber,age,sex,workYear,duty,title,mappingYear,major,userId,file,id);
                }
            在api的service层，方法上使用PostMapping注解同时对注解中的consumes指定multipart/form-data，produces指定application/json;charset=UTF-8，文件数组使用@RequstPart进行接收
            @PostMapping(value = "/updatePrincipalById",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = {"application/json;charset=UTF-8"})
                ResultData updatePrincipalById(@RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("idType") String idType, @RequestParam("idNumber") String idNumber,
                                                      @RequestParam("age")Integer age, @RequestParam("sex") Integer sex, @RequestParam("workYear") Integer workYear, @RequestParam("duty") String duty,
                                                      @RequestParam("title")String title, @RequestParam("mappingYear") Integer mappingYear, @RequestParam("major") String major, @RequestParam("userId") Long userId,
                                                      @RequestPart(value = "file",required = false) MultipartFile[] file,@RequestParam("id")Long id);
            在生产者的controller层，通过PostMapping对路径进行映射，文件数组使用@RequestParam注解进行接收
            @PostMapping("/insertPrincipalByUserId")
                public ResultData insertPrincipalByUserId(@RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("idType") String idType, @RequestParam("idNumber") String idNumber,
                                                      @RequestParam("age")Integer age, @RequestParam("sex") Integer sex, @RequestParam("workYear") Integer workYear, @RequestParam("duty") String duty,
                                                      @RequestParam("title")String title, @RequestParam("mappingYear") Integer mappingYear, @RequestParam("major") String major, @RequestParam("userId") Long userId,
                                                      @RequestParam("file") MultipartFile[] file){}

    路由中可能问道的问题
    4.在springcloud项目中加入过滤器后，无法获取请求路径中的参数
        因为zuul中的过滤器底层是通过servlet实现的，不知道我是不是经验不够没有理解开发者的意思，发现request是全局对象并不是某个线程对象，所以获取不到
        请求路径中的参数的值，所以我自己封装了一个实体类用来获取请求路径中的参数。我的思路是：通过httpServletRequest对象获取到请求路径中的参数和对应的值
        之后将路径中的请求参数和值通过StringBuilder对象进行拼接成一个字符串，过滤器中获取的string字符串，而且当获取到的字符串为空时是跳转到登录界面的路径

    5.在实际开发中zuul中并不会使用到eureka，所以要通过ribbon进行负载均衡处理简单的并发，怎么实现
        在配置文件中需要通过指定提供服务的地址，但是指定之后存在一个问题，当进行访问的时候会报错，这是因为路径存在内网中，需要用到内网穿透技术
        想要内网穿透有三种方式：
        花生壳，但是花生壳是收费的所以为了节约成本一般很少用。
        ngrok，这个对于网络的要求很高，考虑到网络存在一定的波动问题，所以并不适用
        NATAPP,这个底层就是ngrok，但是与ngrok不同的是这个对于网络的要求并不是很高所以公司一般都是这个

    6.对于登录操作在zuul的过滤器中是怎么实现的
        通过判断请求路径中是否包含doLogin，https或http，请求的方式是否为POST方式，之后通过请求路径中是否携带对象参数来进行判断过滤器是否启用的。
        如果携带对象参数说明是登录操作不启用过滤器，若不是需要启用过滤器进行过滤。在获取对象参数的时候我自己封装了一个类来实现的，思路是：通过上下文对象
        RequestContext中的isChunkedRequestBody方法来判断前端传入后端的对象是否被@RequestBody（将前端通过二进制流传入后端的的数据使用反序列化的方法
        转为spring认识的实体类的对象）注解所标注，如果被标注了，直接返回json对象；若没有，则自己进行手动转化（首先将二进制流获取到，再将二进制流转为字符串
        最后通过JSON中的parseObject方法将字符串转为实体对象返回）。过滤器中获取到的是一个json对象

    7.过滤器中判断为登录后，不启用过滤器此时路径并不会自动地发生跳转需要自己写一个类来实现跳转的功能。思路是：将获取到的json对象转为二进制的数组，然后将httpServletRequest
    对象、转换后的二进制数组放入上下文对象RequestContext的request中，从而实现了跳转。当跳转路径之后需要从httpServletRequest中获取到请求的参数，这个时候通过
    自己写的类来获取思路是：获取到前端传来的二进制流，将二进制流转为string字符串，之后通过自定义的字符串转对象的类中的转map方法将string字符串转为map对象
    之后通过feign来跳转到服务提供者进行登录操作。
    登录中的业务是：首先判断从前端传来的map是否为空，若为空则直接返回false，否则判断map中是否有token并且token的值不为null，若正确则证明该用户是点击了返回键进行返回后再次进入了登录界面
    或通过地址第二次进行登录，则需要将map中的token移除掉，并且判断用户是否存在数据库中，若存在说明登录成功同时指定一下token值，对用户进行更新操作。


    讲一下项目的流程
    我的这个项目做的是首先通过地址能够进入登录界面，登录界面有登录和注册的功能，登录进去之后有主页面

