package org.codelogger.utils.os.agent.core.impl;

import com.jcabi.ssh.Shell;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codelogger.utils.StringUtils;
import org.codelogger.utils.os.agent.vo.CpuInfo;
import org.codelogger.utils.os.agent.core.IInfoFetcher;

/**
 * @author defei
 * @date 2019-11-10.
 */
public class CpuInfoFetcher implements IInfoFetcher<CpuInfo> {

    private Pattern cpuName = Pattern.compile("model\\s+name[^:]+:(.*)");

    private Pattern corsCount = Pattern.compile("cpu\\s+cores[^:]+:(.*)");

    private Pattern processorCount = Pattern.compile("processor[^:]+:(.*)");

    /**
     * Get info by shell
     */
    @Override
    public CpuInfo getInfo(Shell shell) {

        Shell.Plain plain = new Shell.Plain(shell);
        try {
            String cpuInfoOfProc = plain.exec("cat /proc/cpuinfo");
            return parse(cpuInfoOfProc);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected CpuInfo parse(String cpuInfoOfProc){

        if(StringUtils.isBlank(cpuInfoOfProc)){
            throw new IllegalArgumentException("Cpu info is null.");
        }
        String cpuName = getCpuName(cpuInfoOfProc);
        int cores = getCores(cpuInfoOfProc);
        int processors = getCpuProcessors(cpuInfoOfProc);

        return CpuInfo.builder()
          .cpu(cpuName)
          .cores(cores)
          .processors(processors)
          .build();
    }

    private String getCpuName(String cpuInfoOfProc) {

        Matcher matcher = cpuName.matcher(cpuInfoOfProc);
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            throw new RuntimeException("Unsupported linux core, parse cpu name failed.");
        }
    }

    private int getCores(String cpuInfoOfProc) {

        Matcher matcher = corsCount.matcher(cpuInfoOfProc);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1).trim());
        } else {
            throw new RuntimeException("Unsupported linux core, parse cpu cors failed.");
        }
    }

    private int getCpuProcessors(String cpuInfoOfProc) {

        Matcher matcher = processorCount.matcher(cpuInfoOfProc);
        String processorCountString = "";
        while (matcher.find()) {
            processorCountString = matcher.group(1);
        }
        try {
            return Integer.parseInt(processorCountString.trim()) + 1;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Unsupported linux core, parse cpu processors failed.");
        }
    }
}
