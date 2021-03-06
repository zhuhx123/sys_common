package com.ivymei.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

/**
 * 序列化工具类
 * @author xianyongjie
 *
 */
public class SerializeUtil {
	
	protected final static Logger LOG = Logger.getLogger(SerializeUtil.class);
	
	/**
     * 序列化
     * 
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        	LOG.error("序列化失败", e);
        }finally{
        	if(oos!=null){
        		try {
					oos.close();
				} catch (IOException e) {
				}
        	}
        }
        return null;
    }

    /**
     * 反序列化
     * 
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        	LOG.error("反序列化失败", e);
        }finally{
        	if(ois!=null){
        		try {
        			ois.close();
				} catch (IOException e) {
				}
        	}
        }
        return null;
    }
}
