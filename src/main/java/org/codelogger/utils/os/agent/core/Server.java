package org.codelogger.utils.os.agent.core;

import com.jcabi.ssh.Shell;
import com.jcabi.ssh.Ssh;
import com.jcabi.ssh.SshByPassword;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.UnknownHostException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.codelogger.utils.StringUtils;
import org.codelogger.utils.os.agent.core.impl.CpuInfoFetcher;
import org.codelogger.utils.os.agent.core.impl.MemoryInfoFetcher;
import org.codelogger.utils.os.agent.core.impl.OperatingSystemInfoFetcher;
import org.codelogger.utils.os.agent.vo.CpuInfo;
import org.codelogger.utils.os.agent.vo.HostInfo;
import org.codelogger.utils.os.agent.vo.MemoryInfo;
import org.codelogger.utils.os.agent.vo.OperatingSystemInfo;

/**
 * @author defei
 * @date 2019-11-10.
 */
@Builder
@ToString(exclude = {"user", "operatingSystemInfoWeakReference", "cpuInfoWeakReference", "memoryInfoWeakReference"})
@Slf4j
public class Server {

    @Getter
    private String host;

    @Getter
    private int sshPort;

    private User user;

    private Shell ssh;

    private transient WeakReference<OperatingSystemInfo> operatingSystemInfoWeakReference;

    private transient WeakReference<CpuInfo> cpuInfoWeakReference;

    private transient WeakReference<MemoryInfo> memoryInfoWeakReference;

    /**
     * execute command
     * @param command command for execute
     * @return execute result
     * @throws IOException connect to server failed.
     */
    public String exec(String command) throws IOException {

        return new Shell.Plain(getShell()).exec(command);
    }

    public Shell getShell() {

        checkUserStatus();

        if (ssh == null) {
            synchronized (this) {
                if (ssh == null) {
                    try {
                        if (StringUtils.isNotBlank(user.getPassword())) {
                            ssh = new SshByPassword(host, sshPort == 0 ? 22 : sshPort, user.getUser(), user.getPassword());
                        } else if (StringUtils.isNotBlank(user.getPrivateKey())) {
                            ssh = new Ssh(host, sshPort == 0 ? 22 : sshPort, user.getUser(), user.getPrivateKey(), user.getPrivateKeyPassword());
                        } else {
                            throw new IllegalArgumentException("Miss password or private key of user");
                        }
                    } catch (UnknownHostException e) {
                        throw new IllegalStateException(e);
                    }
                }
            }
        }
        return ssh;
    }

    public HostInfo getHostInfo() {

        HostInfo.HostInfoBuilder builder = HostInfo.builder();
        builder.server(this);
        try {
            builder.operatingSystem(getOperatingSystem());
        } catch (Exception e) {
            if (log.isTraceEnabled()) {
                log.trace("Load operating system info failed.", e);
            }
        }
        try {
            builder.cpu(getCpuInfo());
        } catch (Exception e) {
            if (log.isTraceEnabled()) {
                log.trace("Load cpu info failed.", e);
            }
        }
        try {
            builder.memory(getMemoryInfo());
        } catch (Exception e) {
            if (log.isTraceEnabled()) {
                log.trace("Load memory info failed.", e);
            }
        }
        return builder.build();
    }

    public OperatingSystemInfo getOperatingSystem() {

        checkUserStatus();

        OperatingSystemInfo info = operatingSystemInfoWeakReference == null ? null : this.operatingSystemInfoWeakReference.get();
        if (operatingSystemInfoWeakReference == null) {
            synchronized (this) {
                info = operatingSystemInfoWeakReference == null ? null : this.operatingSystemInfoWeakReference.get();
                if (info == null) {
                    checkUserStatus();
                    info = new OperatingSystemInfoFetcher().getInfo(getShell());
                    this.operatingSystemInfoWeakReference = new WeakReference<>(info);
                }
            }
        }
        return info;
    }

    public CpuInfo getCpuInfo() {

        checkUserStatus();

        CpuInfo info = cpuInfoWeakReference == null ? null : this.cpuInfoWeakReference.get();
        if (info == null) {
            synchronized (this) {
                info = cpuInfoWeakReference == null ? null : this.cpuInfoWeakReference.get();
                if (info == null) {
                    checkUserStatus();
                    info = new CpuInfoFetcher().getInfo(getShell());
                    this.cpuInfoWeakReference = new WeakReference<>(info);
                }
            }
        }
        return info;
    }

    public MemoryInfo getMemoryInfo() {

        checkUserStatus();

        MemoryInfo info = memoryInfoWeakReference == null ? null : this.memoryInfoWeakReference.get();
        if (info == null) {
            synchronized (this) {
                info = memoryInfoWeakReference == null ? null : this.memoryInfoWeakReference.get();
                if (info == null) {
                    checkUserStatus();
                    info = new MemoryInfoFetcher().getInfo(getShell());
                    this.memoryInfoWeakReference = new WeakReference<>(info);
                }
            }
        }
        return info;
    }

    private void checkUserStatus() {

        if (user == null) {
            throw new IllegalStateException("User is empty.");
        }
    }
}
