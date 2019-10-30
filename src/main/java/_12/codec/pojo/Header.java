package _12.codec.pojo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huang
 * @version v1.0
 * @date 2019-10-21 9:47 PM
 */
@Data
public class Header {

    private int crcCode = 0xabef0101;
    private int length;
    private long sessionID;
    private byte type;
    private byte priority;
    private Map<String, Object> attachment = new HashMap<>();

}