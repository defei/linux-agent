package org.codelogger.utils.os.agent.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.Builder;
import lombok.Getter;
import org.codelogger.utils.IOUtils;

/**
 * @author defei
 * @date 2019-11-10.
 */
@Getter
@Builder
public class User {

    private String user;

    private String password;

    private String privateKey;

    private String privateKeyPassword;

    /**
     * {@linkplain User#privateKey}
     */
    public String getPrivateKey() {

        if (privateKey != null) {
            File file = new File(privateKey);
            if (file.exists()) {
                try {
                    byte[] bytes = IOUtils.getBytes(new FileInputStream(file));
                    privateKey = new String(bytes, StandardCharsets.UTF_8);
                } catch (IOException ignored) {
                }
            }
        }
        return privateKey;
    }
}
