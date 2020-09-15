package org.vector.crud.util;

import java.security.MessageDigest;

public class Sha1HashUtil {

    public static String toSha1Hash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer("");
            int i;
            for (int j = 0; j < digest.length; j++) {
                i = digest[j];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String s = Sha1HashUtil.toSha1Hash("111111");   //111111,123456
        System.out.println(s);
    }

}
