package com.resourcesData;


import com.domain.Tool;
import com.domain.ToolsGroup;

public class ToolCreator {
    public static long ID = 1L;
    public static String NAME = "wiertarka";
    public static String PRODUCER = "BOSH";
    public static String MODEL = "12LXC78";
    public static ToolsGroup GROUP = new ToolsGroup();

    public static long UPDATED_ID = 1L;
    public static String UPDATED_NAME = "młot pneumatyczny";
    public static String UPDATED_PRODUCER = "CAT";
    public static String UPDATED_MODEL = "CP4222";
    public static ToolsGroup UPDATED_GROUP = new ToolsGroup();

    public static Tool toolCreator() {
        return Tool.builder()
                //.id(ID)
                .name(NAME                )
                .producer(PRODUCER)
                .model(MODEL)
                .groupId(GROUP)
                .build();
    }

    public static Tool updatedToolCreator() {
        return Tool.builder()
                //.id(UPDATED_ID)
                .name(UPDATED_NAME                )
                .producer(UPDATED_PRODUCER)
                .model(UPDATED_MODEL)
                .groupId(UPDATED_GROUP)
                .build();
    }
}
