package org.codelogger.utils.os.agent.core.impl;

import org.codelogger.utils.os.agent.vo.OperatingSystemInfo;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author defei
 * @date 2019-11-11.
 */
public class OperatingSystemInfoFetcherTest {

    private OperatingSystemInfoFetcher operatingSystemInfoFetcher = new OperatingSystemInfoFetcher();

    @Test
    public void parseHostnamectl() {

        String data = "   Static hostname: HonorIDC-201987328\n" +
          "         Icon name: computer-vm\n" +
          "           Chassis: vm\n" +
          "        Machine ID: 4e7f8f4868aa89cbfb5ca42c83e67124\n" +
          "           Boot ID: 59404d8d25d84e4786c0901f65621891\n" +
          "    Virtualization: kvm\n" +
          "  Operating System: Debian GNU/Linux 9 (stretch)\n" +
          "            Kernel: Linux 4.10.15-041015-generic\n" +
          "      Architecture: x86-64";

        OperatingSystemInfo operatingSystemInfo = operatingSystemInfoFetcher.parseHostnamectl(data);
        assertNotNull(operatingSystemInfo);
        assertEquals("Debian GNU/Linux 9 (stretch)", operatingSystemInfo.getOs());
        assertEquals("Linux 4.10.15-041015-generic", operatingSystemInfo.getKernel());
    }
}