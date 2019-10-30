package _12.codec.pojo;

import lombok.Data;

/**
 * @author huang
 * @version v1.0
 * @date 2019-10-21 9:41 PM
 */
@Data
public class NettyMessage {
    private Header header;
    private Object body;
}