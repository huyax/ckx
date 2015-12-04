/**
 *
 */
package com.ckx.lang.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @Copyright: 本内容仅限于重庆爱赢科技有限公司内部使用，禁止转发.
 * @Author: wangjifa 2014-3-11 上午10:25:50
 * @Version: 1.0
 * @Desc: <p>
 * </p>
 */
public class IOUtils {

    /**
     * @param in
     * @param out
     * @throws IOException
     */
    public static void piping(InputStream in, OutputStream out) throws IOException {
        piping(in, out, new byte[4 * 1024]);
    }

    /**
     * @param in
     * @param out
     * @param buf
     * @throws IOException
     */
    public static void piping(InputStream in, OutputStream out, byte[] buf) throws IOException {
        int bytesRead = 0;

        while ((bytesRead = in.read(buf)) != -1) {
            out.write(buf, 0, bytesRead);
            out.flush();
        }
    }

    /**
     * @param closeables
     * @throws IOException
     */
    public static void free(Closeable... closeables) throws IOException {

        List<Throwable> throwables = new LinkedList<Throwable>();
        for (Closeable closeable : closeables) {

            try {

                if (null != closeable) {
                    closeable.close();
                }
            } catch (IOException e) {
                throwables.add(e);
            }
        }

    }

    /**
     * @param closeables
     */
    public static void freeQuietly(Closeable... closeables) {
        try {

            free(closeables);
        } catch (Exception e) {
            // ingore this exception
        }
    }

    private IOUtils() {
    }
}
