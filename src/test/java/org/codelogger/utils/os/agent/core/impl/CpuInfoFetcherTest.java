package org.codelogger.utils.os.agent.core.impl;

import org.codelogger.utils.os.agent.vo.CpuInfo;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author defei
 * @date 2019-11-10.
 */
public class CpuInfoFetcherTest {

    private CpuInfoFetcher cpuInfoFetcher = new CpuInfoFetcher();

    @Test
    public void parse() {

        String cpuInfoOfProc = "processor\t: 0\n" +
          "vendor_id\t: GenuineIntel\n" +
          "cpu family\t: 6\n" +
          "model\t\t: 62\n" +
          "model name\t: Intel(R) Xeon(R) CPU E7-4830 v2 @ 2.20GHz\n" +
          "stepping\t: 7\n" +
          "microcode\t: 0x70d\n" +
          "cpu MHz\t\t: 2198.859\n" +
          "cache size\t: 20480 KB\n" +
          "physical id\t: 0\n" +
          "siblings\t: 4\n" +
          "core id\t\t: 0\n" +
          "cpu cores\t: 4\n" +
          "apicid\t\t: 0\n" +
          "initial apicid\t: 0\n" +
          "fpu\t\t: yes\n" +
          "fpu_exception\t: yes\n" +
          "cpuid level\t: 13\n" +
          "wp\t\t: yes\n" +
          "flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts mmx fxsr sse sse2 ss ht syscall nx rdtscp lm constant_tsc arch_perfmon pebs bts nopl xtopology tsc_reliable nonstop_tsc aperfmperf eagerfpu pni pclmulqdq ssse3 cx16 pcid sse4_1 sse4_2 x2apic popcnt aes xsave avx f16c rdrand hypervisor lahf_lm fsgsbase smep xsaveopt dtherm ida arat pln pts\n" +
          "bogomips\t: 4400.00\n" +
          "clflush size\t: 64\n" +
          "cache_alignment\t: 64\n" +
          "address sizes\t: 40 bits physical, 48 bits virtual\n" +
          "power management:\n" +
          "\n" +
          "processor\t: 1\n" +
          "vendor_id\t: GenuineIntel\n" +
          "cpu family\t: 6\n" +
          "model\t\t: 62\n" +
          "model name\t: Intel(R) Xeon(R) CPU E7-4830 v2 @ 2.20GHz\n" +
          "stepping\t: 7\n" +
          "microcode\t: 0x70d\n" +
          "cpu MHz\t\t: 2198.859\n" +
          "cache size\t: 20480 KB\n" +
          "physical id\t: 0\n" +
          "siblings\t: 4\n" +
          "core id\t\t: 1\n" +
          "cpu cores\t: 4\n" +
          "apicid\t\t: 1\n" +
          "initial apicid\t: 1\n" +
          "fpu\t\t: yes\n" +
          "fpu_exception\t: yes\n" +
          "cpuid level\t: 13\n" +
          "wp\t\t: yes\n" +
          "flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts mmx fxsr sse sse2 ss ht syscall nx rdtscp lm constant_tsc arch_perfmon pebs bts nopl xtopology tsc_reliable nonstop_tsc aperfmperf eagerfpu pni pclmulqdq ssse3 cx16 pcid sse4_1 sse4_2 x2apic popcnt aes xsave avx f16c rdrand hypervisor lahf_lm fsgsbase smep xsaveopt dtherm ida arat pln pts\n" +
          "bogomips\t: 4400.00\n" +
          "clflush size\t: 64\n" +
          "cache_alignment\t: 64\n" +
          "address sizes\t: 40 bits physical, 48 bits virtual\n" +
          "power management:\n" +
          "\n" +
          "processor\t: 2\n" +
          "vendor_id\t: GenuineIntel\n" +
          "cpu family\t: 6\n" +
          "model\t\t: 62\n" +
          "model name\t: Intel(R) Xeon(R) CPU E7-4830 v2 @ 2.20GHz\n" +
          "stepping\t: 7\n" +
          "microcode\t: 0x70d\n" +
          "cpu MHz\t\t: 2198.859\n" +
          "cache size\t: 20480 KB\n" +
          "physical id\t: 0\n" +
          "siblings\t: 4\n" +
          "core id\t\t: 2\n" +
          "cpu cores\t: 4\n" +
          "apicid\t\t: 2\n" +
          "initial apicid\t: 2\n" +
          "fpu\t\t: yes\n" +
          "fpu_exception\t: yes\n" +
          "cpuid level\t: 13\n" +
          "wp\t\t: yes\n" +
          "flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts mmx fxsr sse sse2 ss ht syscall nx rdtscp lm constant_tsc arch_perfmon pebs bts nopl xtopology tsc_reliable nonstop_tsc aperfmperf eagerfpu pni pclmulqdq ssse3 cx16 pcid sse4_1 sse4_2 x2apic popcnt aes xsave avx f16c rdrand hypervisor lahf_lm fsgsbase smep xsaveopt dtherm ida arat pln pts\n" +
          "bogomips\t: 4400.00\n" +
          "clflush size\t: 64\n" +
          "cache_alignment\t: 64\n" +
          "address sizes\t: 40 bits physical, 48 bits virtual\n" +
          "power management:\n" +
          "\n" +
          "processor\t: 3\n" +
          "vendor_id\t: GenuineIntel\n" +
          "cpu family\t: 6\n" +
          "model\t\t: 62\n" +
          "model name\t: Intel(R) Xeon(R) CPU E7-4830 v2 @ 2.20GHz\n" +
          "stepping\t: 7\n" +
          "microcode\t: 0x70d\n" +
          "cpu MHz\t\t: 2198.859\n" +
          "cache size\t: 20480 KB\n" +
          "physical id\t: 0\n" +
          "siblings\t: 4\n" +
          "core id\t\t: 3\n" +
          "cpu cores\t: 4\n" +
          "apicid\t\t: 3\n" +
          "initial apicid\t: 3\n" +
          "fpu\t\t: yes\n" +
          "fpu_exception\t: yes\n" +
          "cpuid level\t: 13\n" +
          "wp\t\t: yes\n" +
          "flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts mmx fxsr sse sse2 ss ht syscall nx rdtscp lm constant_tsc arch_perfmon pebs bts nopl xtopology tsc_reliable nonstop_tsc aperfmperf eagerfpu pni pclmulqdq ssse3 cx16 pcid sse4_1 sse4_2 x2apic popcnt aes xsave avx f16c rdrand hypervisor lahf_lm fsgsbase smep xsaveopt dtherm ida arat pln pts\n" +
          "bogomips\t: 4400.00\n" +
          "clflush size\t: 64\n" +
          "cache_alignment\t: 64\n" +
          "address sizes\t: 40 bits physical, 48 bits virtual\n" +
          "power management:\n" +
          "\n" +
          "processor\t: 4\n" +
          "vendor_id\t: GenuineIntel\n" +
          "cpu family\t: 6\n" +
          "model\t\t: 62\n" +
          "model name\t: Intel(R) Xeon(R) CPU E7-4830 v2 @ 2.20GHz\n" +
          "stepping\t: 7\n" +
          "microcode\t: 0x70d\n" +
          "cpu MHz\t\t: 2198.859\n" +
          "cache size\t: 20480 KB\n" +
          "physical id\t: 1\n" +
          "siblings\t: 4\n" +
          "core id\t\t: 0\n" +
          "cpu cores\t: 4\n" +
          "apicid\t\t: 4\n" +
          "initial apicid\t: 4\n" +
          "fpu\t\t: yes\n" +
          "fpu_exception\t: yes\n" +
          "cpuid level\t: 13\n" +
          "wp\t\t: yes\n" +
          "flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts mmx fxsr sse sse2 ss ht syscall nx rdtscp lm constant_tsc arch_perfmon pebs bts nopl xtopology tsc_reliable nonstop_tsc aperfmperf eagerfpu pni pclmulqdq ssse3 cx16 pcid sse4_1 sse4_2 x2apic popcnt aes xsave avx f16c rdrand hypervisor lahf_lm fsgsbase smep xsaveopt dtherm ida arat pln pts\n" +
          "bogomips\t: 4400.00\n" +
          "clflush size\t: 64\n" +
          "cache_alignment\t: 64\n" +
          "address sizes\t: 40 bits physical, 48 bits virtual\n" +
          "power management:\n" +
          "\n" +
          "processor\t: 5\n" +
          "vendor_id\t: GenuineIntel\n" +
          "cpu family\t: 6\n" +
          "model\t\t: 62\n" +
          "model name\t: Intel(R) Xeon(R) CPU E7-4830 v2 @ 2.20GHz\n" +
          "stepping\t: 7\n" +
          "microcode\t: 0x70d\n" +
          "cpu MHz\t\t: 2198.859\n" +
          "cache size\t: 20480 KB\n" +
          "physical id\t: 1\n" +
          "siblings\t: 4\n" +
          "core id\t\t: 1\n" +
          "cpu cores\t: 4\n" +
          "apicid\t\t: 5\n" +
          "initial apicid\t: 5\n" +
          "fpu\t\t: yes\n" +
          "fpu_exception\t: yes\n" +
          "cpuid level\t: 13\n" +
          "wp\t\t: yes\n" +
          "flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts mmx fxsr sse sse2 ss ht syscall nx rdtscp lm constant_tsc arch_perfmon pebs bts nopl xtopology tsc_reliable nonstop_tsc aperfmperf eagerfpu pni pclmulqdq ssse3 cx16 pcid sse4_1 sse4_2 x2apic popcnt aes xsave avx f16c rdrand hypervisor lahf_lm fsgsbase smep xsaveopt dtherm ida arat pln pts\n" +
          "bogomips\t: 4400.00\n" +
          "clflush size\t: 64\n" +
          "cache_alignment\t: 64\n" +
          "address sizes\t: 40 bits physical, 48 bits virtual\n" +
          "power management:\n" +
          "\n" +
          "processor\t: 6\n" +
          "vendor_id\t: GenuineIntel\n" +
          "cpu family\t: 6\n" +
          "model\t\t: 62\n" +
          "model name\t: Intel(R) Xeon(R) CPU E7-4830 v2 @ 2.20GHz\n" +
          "stepping\t: 7\n" +
          "microcode\t: 0x70d\n" +
          "cpu MHz\t\t: 2198.859\n" +
          "cache size\t: 20480 KB\n" +
          "physical id\t: 1\n" +
          "siblings\t: 4\n" +
          "core id\t\t: 2\n" +
          "cpu cores\t: 4\n" +
          "apicid\t\t: 6\n" +
          "initial apicid\t: 6\n" +
          "fpu\t\t: yes\n" +
          "fpu_exception\t: yes\n" +
          "cpuid level\t: 13\n" +
          "wp\t\t: yes\n" +
          "flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts mmx fxsr sse sse2 ss ht syscall nx rdtscp lm constant_tsc arch_perfmon pebs bts nopl xtopology tsc_reliable nonstop_tsc aperfmperf eagerfpu pni pclmulqdq ssse3 cx16 pcid sse4_1 sse4_2 x2apic popcnt aes xsave avx f16c rdrand hypervisor lahf_lm fsgsbase smep xsaveopt dtherm ida arat pln pts\n" +
          "bogomips\t: 4400.00\n" +
          "clflush size\t: 64\n" +
          "cache_alignment\t: 64\n" +
          "address sizes\t: 40 bits physical, 48 bits virtual\n" +
          "power management:\n" +
          "\n" +
          "processor\t: 7\n" +
          "vendor_id\t: GenuineIntel\n" +
          "cpu family\t: 6\n" +
          "model\t\t: 62\n" +
          "model name\t: Intel(R) Xeon(R) CPU E7-4830 v2 @ 2.20GHz\n" +
          "stepping\t: 7\n" +
          "microcode\t: 0x70d\n" +
          "cpu MHz\t\t: 2198.859\n" +
          "cache size\t: 20480 KB\n" +
          "physical id\t: 1\n" +
          "siblings\t: 4\n" +
          "core id\t\t: 3\n" +
          "cpu cores\t: 4\n" +
          "apicid\t\t: 7\n" +
          "initial apicid\t: 7\n" +
          "fpu\t\t: yes\n" +
          "fpu_exception\t: yes\n" +
          "cpuid level\t: 13\n" +
          "wp\t\t: yes\n" +
          "flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts mmx fxsr sse sse2 ss ht syscall nx rdtscp lm constant_tsc arch_perfmon pebs bts nopl xtopology tsc_reliable nonstop_tsc aperfmperf eagerfpu pni pclmulqdq ssse3 cx16 pcid sse4_1 sse4_2 x2apic popcnt aes xsave avx f16c rdrand hypervisor lahf_lm fsgsbase smep xsaveopt dtherm ida arat pln pts\n" +
          "bogomips\t: 4400.00\n" +
          "clflush size\t: 64\n" +
          "cache_alignment\t: 64\n" +
          "address sizes\t: 40 bits physical, 48 bits virtual\n" +
          "power management:";

        CpuInfo cpuInfo = cpuInfoFetcher.parse(cpuInfoOfProc);
        assertNotNull(cpuInfo);
        assertEquals("Intel(R) Xeon(R) CPU E7-4830 v2 @ 2.20GHz", cpuInfo.getCpu());
        assertEquals(4, cpuInfo.getCores());
        assertEquals(8, cpuInfo.getProcessors());
    }
}