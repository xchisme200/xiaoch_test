import java.io.*;
import java.util.List;
import java.lang.reflect.Method;
public class test extends ClassLoader {
    public static void main(String[] args) throws Exception{

        try {
            //new test().findClass("Hello").newInstance().getMethod("hello");
            String className ="Hello";
            String methodName="hello";
            // 加载相应的类
            Class<?> clazz = new test().loadClass(className);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            // 调用实例方法
            Method method = clazz.getMethod(methodName);
            method.invoke(instance);
        } catch (ClassFormatError e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    protected Class<?> findClass(String name) throws ClassFormatError {
        File file = new File("C:\\Users\\sscc\\IdeaProjects\\test\\src\\Hello.xlass");
        int length = (int) file.length();


        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        byte[] bytes = new byte[length];
        try {
            in.read(bytes, 0, length);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        bytes = myConvert(bytes);


        return defineClass(name, bytes, 0, bytes.length);

    }

    public byte[] myConvert(byte[] bytes) {
        int i = 0;
        for (i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - (int) bytes[i]);
        }
        return bytes;
    }
}
