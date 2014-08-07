package com.sf.foodtruck.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public final class TestUtils {

    
    /**
     * Retrieve the contents of the resource as a string.
     * 
     * @param name Resource name
     * @return Resource contents as a string
     * @throws IOException Error reading the resource
     */
    public static String getResourceAsString(final String name) throws IOException {
        final byte[] result = getResourceAsBytes(name);
        System.out.println(new String(result, "UTF-8"));
        return new String(result, "UTF-8");
    }
        
    private static byte[] getResourceAsBytes(final String name) throws IOException {
        InputStream is = null;
        try {
            is = openResource(name);
            final byte[] tmp = new byte[is.available()];
            final int nb = is.read(tmp);
//            assertThat(tmp.length, equalTo(nb));
            return tmp;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    
    /**
     * Open a resource using the current threads classloader.
     * 
     * @param name Resource name
     * @return Inputput stream
     */
    private static InputStream openResource(final String name) {
        final InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);        
        return is;
    }
}
