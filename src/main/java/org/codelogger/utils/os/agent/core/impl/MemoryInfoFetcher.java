package org.codelogger.utils.os.agent.core.impl;

import com.jcabi.ssh.Shell;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codelogger.utils.StringUtils;
import org.codelogger.utils.os.agent.vo.MemoryInfo;
import org.codelogger.utils.os.agent.core.IInfoFetcher;

/**
 * @author defei
 * @date 2019-11-10.
 */
public class MemoryInfoFetcher implements IInfoFetcher<MemoryInfo> {

    private Pattern MemTotal = Pattern.compile("memtotal[^:]*:[\\s]*(\\d+).*");

    /**
     * Get info by shell
     */
    @Override
    public MemoryInfo getInfo(Shell shell) {

        Shell.Plain plain = new Shell.Plain(shell);
        try {
            String memoryInfoOfProc = plain.exec("cat /proc/meminfo");
            return parse(memoryInfoOfProc);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected MemoryInfo parse(String memoryInfoOfProc) {

        if (StringUtils.isBlank(memoryInfoOfProc)) {
            throw new IllegalArgumentException("Memory info is null.");
        }
        int memoryTotal = getMemoryTotal(memoryInfoOfProc);

        return MemoryInfo.builder()
          .memoryTotal(memoryTotal)
          .build();
    }

    private int getMemoryTotal(String memoryInfoOfProc) {

        Matcher matcher = MemTotal.matcher(memoryInfoOfProc.toLowerCase());
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException ignored) {
            }
        }
        throw new RuntimeException("Unsupported linux core, parse memoryTotal total failed.");
    }
}
