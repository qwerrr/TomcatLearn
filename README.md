
ex01
    简单的http服务器
ex02
    简单的servlet容器
ex03
    将连接部分抽成一个单独的connector组件
    connector包:连接器
    core包:核心
    startup:启动器
    
    HttpConnector - 
    HttpProcesser - 创建HttpRequest/HttpResponse, 并将结果请求头(第一行和请求头内的各行)解析出来, 
        判断应使用哪个processer处理请求, 并启动/调用处理方法
        ps:没有使用独立的线程,所以只能同时处理单个请求
    HttpRequest 
    HttpResponse
    DynamicProcesser - 处理servlet请求
    StaticResourceProcesser - 处理静态资源请求