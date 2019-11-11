package org.codelogger.utils.os.agent.vo;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author defei
 * @date 2019-11-10.
 */
@Builder
@ToString
public class MemoryInfo {

    private static final int KB_OF_1MB = 1024;

    private static final int KB_OF_1GB = KB_OF_1MB * 1024;

    /**
     * 物理总内存
     */
    @Getter
    private int memoryTotal;

    public String getMemoryTotalOfHumanFriendly() {

        if (memoryTotal > KB_OF_1GB) {
            return BigDecimal.valueOf(memoryTotal).divide(BigDecimal.valueOf(KB_OF_1GB), 2, BigDecimal.ROUND_HALF_UP) + " gB";
        } else if (memoryTotal > KB_OF_1MB) {
            return BigDecimal.valueOf(memoryTotal).divide(BigDecimal.valueOf(KB_OF_1MB), 2, BigDecimal.ROUND_HALF_UP) + " mB";
        } else {
            return memoryTotal + " kB";
        }
    }
}
