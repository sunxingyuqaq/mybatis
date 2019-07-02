package com.study.boot.mybatis.utils;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.study.boot.mybatis.design.Door;
import com.study.boot.mybatis.design.singleton.Singleton1;
import com.study.boot.mybatis.design.singleton.Singleton2;
import com.study.boot.mybatis.design.singleton.Singleton3;
import com.study.boot.mybatis.type.WaterAnimal;
import org.junit.Test;
import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xingyu Sun
 * @date 2019/3/5 9:27
 */
public class Tests {

    @Test
    public void test() {
        boolean license = getLicense();
        System.out.println(license);
    }

    private boolean getLicense() {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("license/license.xml");
        License license = new License();
        try {
            Assert.notNull(stream, "msg");
            license.setLicense(stream);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void word2Pdf(String inPath, String out, int format) {
        if (getLicense()) {
            long start = System.currentTimeMillis();
            File file = new File(out);
            try (
                    FileOutputStream os = new FileOutputStream(file)
            ) {
                Document doc = new Document(inPath);
                doc.save(os, format);
                long end = System.currentTimeMillis();
                System.out.println("耗时：" + (end - start));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2() {
        word2Pdf("d:/word/test.doc", "d:/pdf/test.pdf", SaveFormat.PDF);
        word2Pdf("d:/word/test.doc", "d:/html/test.png", SaveFormat.PNG);
    }

    @Test
    public void type() throws NoSuchFieldException {
        Resolve resolve = new Resolve();
        Map<String, List<String>> map = new HashMap<>(16);
        Class classGenericType = Resolve.getClassGenericType(resolve.getMap().getClass(), 0);
        System.out.println(map);
        System.out.println(classGenericType);
        ResolvableType maps = ResolvableType.forField(Resolve.class.getDeclaredField("map"));
        ResolvableType[] generics = maps.getGenerics();
        System.out.println(Arrays.toString(generics));
        ResolvableType type = ResolvableType.forClass(WaterAnimal.class);
    }

    @Test
    public void single(){
        Singleton1 instance1 = Singleton1.getInstance();
        Singleton2 instance2 = Singleton2.getInstance();
        Singleton3 instance3 = Singleton3.getInstance();
        System.out.println(instance1+""+instance2+""+instance3);
    }

    @Test
    public void cls() throws Exception {
        Class<Door> aClass = Door.class;
        Constructor<Door> constructor = aClass.getConstructor(String.class);
        Door aClass2 = constructor.newInstance("11");
        System.out.println(aClass2.toString());
    }

    @Test
    public void test3() {
        //word2Pdf("d:/word/test.doc", "d:/word/test.pdf", SaveFormat.PDF);
        word2Pdf("d:/word/test.doc", "d:/word/test.jpg", SaveFormat.JPEG);
    }

}
