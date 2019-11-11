package org.codelogger.utils.os.agent.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author defei
 * @date 2019-11-10.
 */
@Getter
@Builder
@ToString
public class CpuInfo {

    /**
     * 处理器信息
     */
    private String cpu;

    /**
     * 处理器核心数量
     */
    private int cores;

    /**
     * 处理器核心数量
     */
    private int processors;


}
