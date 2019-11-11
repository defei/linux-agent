package org.codelogger.utils.os.agent.core.impl;

import com.jcabi.ssh.Shell;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codelogger.utils.os.agent.vo.OperatingSystemInfo;
import org.codelogger.utils.os.agent.core.IInfoFetcher;

/**
 * @author defei
 * @date 2019-11-10.
 */
public class OperatingSystemInfoFetcher implements IInfoFetcher<OperatingSystemInfo> {

    private Pattern name = Pattern.compile("(name|NAME)[^=]*=(.*)");

    private Pattern KERNEL_OF_HOSTNAMECTL = Pattern.compile("\\s*kernel\\s*");

    private Pattern OS_OF_HOSTNAMECTL = Pattern.compile("\\s*operating\\s*system\\s*");

    /**
     * Get info by shell
     */
    @Override
    public OperatingSystemInfo getInfo(Shell shell) {

        Shell.Plain plain = new Shell.Plain(shell);
        try {
            String hostnamectlResult = plain.exec("hostnamectl");
            return parseHostnamectl(hostnamectlResult);
        } catch (Exception e) {
            try {
                String kernel = plain.exec("uname -s -r").trim();
                String osInfo = plain.exec("cat /etc/os-release").trim();
                return OperatingSystemInfo.builder()
                  .kernel(kernel)
                  .os(getOsName(osInfo)).build();
            } catch (Exception ex) {
            }

        }
        throw new RuntimeException("Unsupported operate system.");
    }

    protected OperatingSystemInfo parseHostnamectl(String content) {

        String[] lines = content.split("\n");
        String kernel = null;
        String osInfo = null;
        for (String line : lines) {
            String[] keyAndValue = line.split(":");
            String lowerKey = keyAndValue[0].toLowerCase();
            if (OS_OF_HOSTNAMECTL.matcher(lowerKey).find()) {
                osInfo = keyAndValue[1].trim();
            } else if(KERNEL_OF_HOSTNAMECTL.matcher(lowerKey).find()) {
                kernel = keyAndValue[1].trim();
            }
        }

        return OperatingSystemInfo.builder().kernel(kernel).os(osInfo).build();
    }

    private String getOsName(String info) {

        Matcher matcher = name.matcher(info);
        if (matcher.find()) {
            try {
                return matcher.group(2).replaceAll("\"", "").trim();
            } catch (NumberFormatException ignored) {
            }
        }
        throw new RuntimeException("Unsupported linux core, parse memoryTotal total failed.");
    }
}
