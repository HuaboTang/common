package com.vbobot.common.rpc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vbobot.common.utils.enums.ResultCode;

import java.io.Serializable;
import org.junit.jupiter.api.Test;

/**
 *
 * Created by Liang.Zhuge on 02/05/2017.
 */
public class RpcResultTest {

    @Test
    public void testIsSuccess() throws Exception {
        final RpcResult<Serializable> serializableRpcResult = new RpcResult<>();
        serializableRpcResult.setResult(ResultCode.ERROR);
        assertFalse(serializableRpcResult.isSuccess());
        serializableRpcResult.setResult(ResultCode.SUCCESS);
        assertTrue(serializableRpcResult.isSuccess());
    }
}
