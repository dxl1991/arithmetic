package spring;

import spring.annotation.AutoWired;
import spring.annotation.Controller;
import spring.annotation.RequestMapping;
import spring.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class MyDispatcherServlet extends HttpServlet {
    private Properties properties = new Properties();
    private List<String> classNames = new ArrayList<>();
    private Map<String,Object> ioc = new ConcurrentHashMap<>();
    private Map<String,Method> handlerMap = new ConcurrentHashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            doDisPatch(req,resp);
        }catch (Exception e){
            resp.getWriter().print("500");
        }
    }

    private void doDisPatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");
        if(!handlerMap.containsKey(url)){
            resp.getWriter().print("404 Not Found");
            return;
        }
        Map<String, String[]> parameterMap = req.getParameterMap();
        Method method = handlerMap.get(url);
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        handlerMap.get(url).invoke(ioc.get(beanName),new Object[]{req,resp,parameterMap.get("name")[0]});
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1、加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        
        //2、扫描相关的类
        doScanner(properties.getProperty("scanPackage"));
        
        //3、实例化相关的类
        doInstance();
        
        //4、完成依赖注入
        doAutoWired();
        
        //5、初始化HandlerMapping
        doInitHandlerMapping();
        System.out.println("my spring framework init");
    }

    private void doInitHandlerMapping() {
        for(Object bean : ioc.values()){
            Class clazz = bean.getClass();
            if(!clazz.isAnnotationPresent(Controller.class)){
                continue;
            }
            String baseUrl = "";
            if(clazz.isAnnotationPresent(RequestMapping.class)){
                RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                baseUrl = requestMapping.value();
            }
            for(Method method : clazz.getMethods()){
                if(!method.isAnnotationPresent(RequestMapping.class)){
                    continue;
                }
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                //连续多个/替换成一个/
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+","/");
                handlerMap.put(url,method);
            }
        }
    }

    private void doAutoWired() {
        for(Object bean : ioc.values()){
            for(Field field : bean.getClass().getDeclaredFields()){
                if(field.getAnnotation(AutoWired.class) != null){
                    AutoWired autoWired = field.getAnnotation(AutoWired.class);
                    String beanName = autoWired.value().trim();
                    if("".equals(beanName)){
                        beanName = field.getType().getName();
                    }
                    Object value = ioc.get(beanName);
                    field.setAccessible(true);
                    try {
                        field.set(bean,value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void doInstance() {
        if(classNames.isEmpty()){
            return;
        }
        for(String className : classNames){
            try {
                Class clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(Service.class)){
                    Service service = (Service) clazz.getAnnotation(Service.class);
                    String beanName = service.value();
                    if("".equals(beanName)){
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);
                    for(Class i : clazz.getInterfaces()){
                        if(ioc.containsKey(i.getSimpleName())){
                            throw new RuntimeException("The bean is exist!!");
                        }
                        ioc.put(i.getSimpleName(),instance);
                    }
                }else if(clazz.isAnnotationPresent(Controller.class)){
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName,clazz.newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String toLowerFirstCase(String name){
        char[] chars = name.toCharArray();
        chars[0] += 32;
        return new String(chars);
    }

    private void doScanner(String scanPackage) {
        URL resource = getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("[.]", "/"));
        File file = new File(resource.getFile());
        for(File f : file.listFiles()){
            if(f.isDirectory()){
                doScanner(scanPackage + "." + f.getName());
            }else {
                if(f.getName().endsWith(".class")){
                    String className = scanPackage + "." + f.getName().replaceAll(".class","");
                    classNames.add(className);
                }
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
