package python;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author dengxinlong
 * @date 2020/8/24 11:27
 * @version 1.0
 */
public class TestPy {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        test1();
        System.out.println(test4());
        System.out.println(System.currentTimeMillis() - start);
    }

    public static String test4(){
        BufferedReader bReader = null;
        InputStreamReader sReader = null;
        try {
            Process p = Runtime.getRuntime().exec("python D:\\python\\testDll.py aaa");

            /* 为"错误输出流"单独开一个线程读取之,否则会造成标准输出流的阻塞 */
            Thread t = new Thread(new InputStreamRunnable(p.getErrorStream(), "ErrorStream"));
            t.start();

            /* "标准输出流"就在当前方法中读取 */
            BufferedInputStream bis = new BufferedInputStream(p.getInputStream());

            sReader = new InputStreamReader(bis, "utf-8");
            bReader = new BufferedReader(sReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            bReader.close();
            sReader.close();
            bis.close();
            p.destroy();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "result: error";
        } finally {
        }

    }
    //这种方法快，但是只能通过print拿到函数结果，并且是字符串的

    public static void test3(){
        Process proc;
        try {
//            String[] ars = new String[] {"python", "D:\\python\\testDll.py","my name is "};
            String[] ars = new String[] {"python", "testDll.py","my name is "};
            proc = Runtime.getRuntime().exec(ars);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor(); //等子进程结算
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test2(){

        Properties props = new Properties();
        props.put("python.console.encoding", "UTF-8"); // Used to prevent: console: Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
        props.put("python.security.respectJavaAccessibility", "false"); //don't respect java accessibility, so that we can access protected members on subclasses
        props.put("python.import.site","false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("import sys");
        interpreter.exec("sys.path.append('D:\\ruanjian\\python-3.8.5-amd64\\Lib')");
        interpreter.exec("sys.path.append('D:\\ruanjian\\python-3.8.5-amd64\\Lib\\site-packages')");
        interpreter.exec("print(sys.path)");
        interpreter.exec("import clr");
//        interpreter.exec("clr.AddReference('JNATest')");
//        interpreter.exec("from JNATest import *");
//        interpreter.exec("instance = JNA()");
//        interpreter.exec("print(instance.getName('aaa'))");
    }

    public static void test1(){

        Properties props = new Properties();
        props.put("python.console.encoding", "UTF-8"); // Used to prevent: console: Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
        props.put("python.security.respectJavaAccessibility", "false"); //don't respect java accessibility, so that we can access protected members on subclasses
        props.put("python.import.site","false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("import platform");
        interpreter.exec("print(platform.python_version())");
        PySystemState sys = interpreter.getSystemState();
//        sys.path.add("D:\\ruanjian\\python2.7.14\\Lib\\site-packages");
        interpreter.execfile("D:\\python\\testDll.py");
//        interpreter.execfile("D:\\python\\add.py");

        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        int a = 5, b = 10;
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj = pyFunction.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("the answer is: " + pyobj);
    }

    static class InputStreamRunnable implements Runnable {
        BufferedReader bReader = null;
        String type = null;

        public InputStreamRunnable(InputStream is, String _type) {
            try {
                bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "UTF-8"));
                type = _type;
            } catch (Exception ex) {
            }
        }

        @SuppressWarnings("unused")
        public void run() {
            String line;
            int lineNum = 0;

            try {
                while ((line = bReader.readLine()) != null) {
                    lineNum++;
                    // Thread.sleep(200);
                }
                bReader.close();
            } catch (Exception ex) {
            }
        }
    }
}
