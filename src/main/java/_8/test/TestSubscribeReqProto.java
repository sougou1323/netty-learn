package _8.test;

import com.google.protobuf.InvalidProtocolBufferException;
import protobuf.SubscribeReqProto;

/**
 * 描述:
 *
 * @author huang
 * @since 2019-10-11 4:20 PM
 */
public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {

        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(1)
                .setUserName("huang")
                .setProductName("netty book")
                .setAddress("nanjing yuhuatai beijing liulichang shenzhen hongshulin");

        return builder.build();
    }


    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("before encode : " + req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("after decode : " + req2.toString());
        System.out.println("equal : -->" + req2.equals(req));
    }

}