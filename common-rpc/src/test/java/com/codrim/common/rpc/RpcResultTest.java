package com.codrim.common.rpc;

import com.codrim.common.utils.enums.ResultCode;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

/**
 *
 * Created by Liang.Zhuge on 02/05/2017.
 */
public class RpcResultTest {

    @Test
    public void testIsSuccess() throws Exception {
        final RpcResult<Serializable> serializableRpcResult = new RpcResult<>();
        serializableRpcResult.setResult(ResultCode.ERROR);
        Assert.assertFalse(serializableRpcResult.isSuccess());
        serializableRpcResult.setResult(ResultCode.SUCCESS);
        Assert.assertTrue(serializableRpcResult.isSuccess());
    }
}
