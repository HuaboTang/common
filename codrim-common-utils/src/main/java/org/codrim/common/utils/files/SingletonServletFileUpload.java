package org.codrim.common.utils.files;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonServletFileUpload {

    private static Logger log = LoggerFactory.getLogger(SingletonServletFileUpload.class);

    private static long DEFAULT_UPLOAD_SIZE_MAX = 4 * 1024 * 1024;
    public static ServletFileUpload servletFileUpload;

    public static ServletFileUpload getServletFileUpload() {
        if (servletFileUpload == null) {
            synchronized (SingletonServletFileUpload.class) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                //private @Value("${uploadSizeMax}") String UPLOAD_SIZE_MAX;
                //换做从配置文件中读取属性uploadSizeMax
                long UPLOAD_SIZE_MAX = 0l;
                servletFileUpload = new ServletFileUpload(factory);
                servletFileUpload.setHeaderEncoding("UTF-8");
                servletFileUpload.setFileSizeMax(UPLOAD_SIZE_MAX > 0 ? UPLOAD_SIZE_MAX : DEFAULT_UPLOAD_SIZE_MAX);
                log.debug("max allowed uploading file:{}M", servletFileUpload.getFileSizeMax()/1024/1024);
            }
        }
        return servletFileUpload;
    }
}
