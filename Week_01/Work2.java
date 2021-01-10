/*
 * Project: geek
 *
 * File Created at 2021年01月10日
 *
 * Copyright 2018 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Type Work2
 * @Desc Work2
 * @author hejiahuan
 * @date 2021年01月10日 10:07
 * @version
 */
public class Work2 extends ClassLoader{

    
    public static void main(String[] args){
        Work2 work = new Work2();
        try {
            Class helloClass = work.findClass("Hello");
            try {
               Object inst =  helloClass.newInstance();
                try {
                    Method m = helloClass.getDeclaredMethod("hello");
                    try {
                        m.invoke(inst);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path p = Paths.get("Hello.xlass");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            Files.copy(p,os);

        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = os.toByteArray();

        for(int i = 0 ;i< bytes.length;i++){
            bytes[i] = (byte)(255-bytes[i]);
        }
        return defineClass(name,bytes,0,bytes.length);
    }
}
/**
 * Revision history
 * -------------------------------------------------------------------------
 *
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2021年01月10日 10:07 hejiahuan create
 */
