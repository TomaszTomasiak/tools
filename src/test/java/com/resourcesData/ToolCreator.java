package com.resourcesData;


import com.domain.*;

public class ToolCreator {
    public static long ID = 1L;
    public static String NAME = "wiertarka";
    public static long PRODUCER_ID = 2L;
    public static long MODEL_ID = 3L;
    public static ToolsGroup GROUP = new ToolsGroup();
    public static Location LOCATION = new Location();

    public static long UPDATED_ID = 1L;
    public static String UPDATED_NAME = "młot pneumatyczny";
    public static String UPDATED_PRODUCER = "CAT";
    public static String UPDATED_MODEL = "CP4222";
    public static ToolsGroup UPDATED_GROUP = new ToolsGroup();
    public static Location UPDATED_LOCATION = new Location();

    public static Tool toolCreator() {
        return Tool.builder()
                //.id(ID)
                .name(NAME                )
                .producer(new Producer())
                .model(new Model())
                .group(GROUP)
                .location(LOCATION)
                .build();
    }

    public static Tool updatedToolCreator() {
        return Tool.builder()
                //.id(UPDATED_ID)
                .name(UPDATED_NAME                )
                .producer(new Producer())
                .model(new Model())
                .group(UPDATED_GROUP)
                .location(UPDATED_LOCATION)
                .build();
    }
}
