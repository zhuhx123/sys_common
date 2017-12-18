package com.ivymei.system.common.util;

import com.ivymei.framework.util.PropertiesUtil;
import com.ivymei.framework.util.StringUtil;

/**
 * i美统一拼接图片访问路径
 * 由于图片的链接存在“相对路径”，故这里统一拼接完整路径返回，如：
 * 路径为：meal/2016-09-13-16-34-11.png ==> http://image.ivymei.com/meal/2016-09-13-16-34-11.png
 * 其中域名由各个环境的配置决定
 * Created by show on 2017/9/25.
 */
public class ImeiAddImgUtil {

    private final static String notfundImg = "http://image.ivymei.com/notfund.jpg";

    private final static String imageDomain = PropertiesUtil.getStringValue("imei.image.domain");

    public static String generate(String pic) {
        String url = notfundImg;
        if (!StringUtil.isNullOrBlank(pic)) {
            if (pic.contains("http:") || pic.contains("ftp:")) {
                url = pic;
            } else {
                StringBuffer buf = new StringBuffer(imageDomain);
                buf.append("/");
                buf.append(pic);
                url = buf.toString();
            }
        }
        return url;
    }

}
