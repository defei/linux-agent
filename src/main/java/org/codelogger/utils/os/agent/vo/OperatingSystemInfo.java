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
public class OperatingSystemInfo {

    /**
     * 内核
     */
    private String kernel;

    /**
     * 操作系统
     */
    private String os;

}
