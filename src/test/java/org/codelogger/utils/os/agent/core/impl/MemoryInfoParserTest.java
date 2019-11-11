package org.codelogger.utils.os.agent.core.impl;

import org.codelogger.utils.os.agent.vo.MemoryInfo;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author defei
 * @date 2019-11-10.
 */
public class MemoryInfoParserTest {

    private MemoryInfoFetcher memoryInfoFetcher = new MemoryInfoFetcher();
    @Test
    public void parse() {

        String memoryInfoOfProc = "MemTotal:       16267564 kB\n" +
          "MemFree:         3012648 kB\n" +
          "MemAvailable:    4719184 kB\n" +
          "Buffers:               0 kB\n" +
          "Cached:          1743204 kB\n" +
          "SwapCached:         4512 kB\n" +
          "Active:         10040476 kB\n" +
          "Inactive:        2384056 kB\n" +
          "Active(anon):    9292880 kB\n" +
          "Inactive(anon):  1665568 kB\n" +
          "Active(file):     747596 kB\n" +
          "Inactive(file):   718488 kB\n" +
          "Unevictable:           0 kB\n" +
          "Mlocked:               0 kB\n" +
          "SwapTotal:       2097148 kB\n" +
          "SwapFree:         806088 kB\n" +
          "Dirty:               108 kB\n" +
          "Writeback:             0 kB\n" +
          "AnonPages:      10676784 kB\n" +
          "Mapped:           102900 kB\n" +
          "Shmem:            277120 kB\n" +
          "Slab:             622624 kB\n" +
          "SReclaimable:     578792 kB\n" +
          "SUnreclaim:        43832 kB\n" +
          "KernelStack:       12016 kB\n" +
          "PageTables:        37480 kB\n" +
          "NFS_Unstable:          0 kB\n" +
          "Bounce:                0 kB\n" +
          "WritebackTmp:          0 kB\n" +
          "CommitLimit:    10230928 kB\n" +
          "Committed_AS:   15731948 kB\n" +
          "VmallocTotal:   34359738367 kB\n" +
          "VmallocUsed:      171608 kB\n" +
          "VmallocChunk:   34359341052 kB\n" +
          "HardwareCorrupted:     0 kB\n" +
          "AnonHugePages:   7845888 kB\n" +
          "HugePages_Total:       0\n" +
          "HugePages_Free:        0\n" +
          "HugePages_Rsvd:        0\n" +
          "HugePages_Surp:        0\n" +
          "Hugepagesize:       2048 kB\n" +
          "DirectMap4k:       90048 kB\n" +
          "DirectMap2M:    16687104 kB";

        MemoryInfo memoryInfo = memoryInfoFetcher.parse(memoryInfoOfProc);
        assertNotNull(memoryInfo);
        assertEquals(16267564, memoryInfo.getMemoryTotal());
    }
}