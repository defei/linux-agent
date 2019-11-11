package org.codelogger.utils.os.agent.vo;

import lombok.Builder;
import lombok.Getter;
import org.codelogger.utils.os.agent.core.Server;

/**
 * @author defei
 * @date 2019-11-10.
 */
@Builder
public class HostInfo {

    /**
     * 主机
     */
    @Getter
    private Server server;

    /**
     * 操作系统
     */
    @Getter
    private OperatingSystemInfo operatingSystem;

    /**
     * cpu信息
     */
    @Getter
    private CpuInfo cpu;

    /**
     * 内存信息
     */
    @Getter
    private MemoryInfo memory;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Server: ").append(server.getHost()).append("\n");
        if (operatingSystem != null) {
            sb.append("OS: ").append(operatingSystem.getOs()).append("\n");
        }
        if (cpu != null) {
            sb.append("CPU: ").append(cpu.getCpu()).append(" ,(")
              .append(cpu.getCores()).append(" cores, ")
              .append(cpu.getProcessors()).append(" processors)").append("\n");
        }
        if (memory != null) {
            sb.append("MemoryTotal: ").append(memory.getMemoryTotalOfHumanFriendly());
        }

        return sb.toString();
    }
}
