package org.codelogger.utils.os.agent.core;

import com.jcabi.ssh.Shell;

/**
 * @author defei
 * @date 2019-11-10.
 */
public interface IInfoFetcher<I> {

    /**
     * Get info by shell
     */
    I getInfo(Shell shell);
}
