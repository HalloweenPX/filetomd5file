package control;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class MD5Checksum {

    public String getMD5Checksum(String filePath) throws Exception {
        InputStream fis = new FileInputStream(filePath);
        MessageDigest md = MessageDigest.getInstance("MD5");
        DigestInputStream dis = new DigestInputStream(fis, md);
        byte[] buffer = new byte[1024];
        while (dis.read(buffer) != -1) {
        }

        byte[] digest = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        fis.close();
        dis.close();
        return sb.toString();
    }
}
