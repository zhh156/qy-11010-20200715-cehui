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

    4.在springcloud项目中添加zuul之后，需要对请求的路径进行过滤，发现在进行获取路径中的参数时无法获取
        解决思路：
            编写了一个工具类通过HttpServletRequest对请求的路径中的参数进行一个个拿出来拼接到StringBuilder中，其中使用StringBuilder是为了提高速度
