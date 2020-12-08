package com.resourcesData;

import com.dto.ToolDto;

public class ToolDtoCreator {

    public static long ID = 1L;
    public static String NAME = "wiertarka";
    public static String PRODUCER = "BOSH";
    public static String MODEL = "12LXC78";
    public static long GROUP_ID = 9L;

    public static long UPDATED_ID = 1L;
    public static String UPDATED_NAME = "młot pneumatyczny";
    public static String UPDATED_PRODUCER = "CAT";
    public static String UPDATED_MODEL = "CP4222";
    public static long UPDATED_GROUP_ID = 12L;

    public static ToolDto toolDtoCreator() {
        return ToolDto.builder()
                .id(ID)
                .name(NAME                )
                .producer(PRODUCER)
                .model(MODEL)
                .groupId(GROUP_ID)
                .build();
    }

    public static ToolDto updatedToolDtoCreator() {
        return ToolDto.builder()
                .id(UPDATED_ID)
                .name(UPDATED_NAME                )
                .producer(UPDATED_PRODUCER)
                .model(UPDATED_MODEL)
                .groupId(UPDATED_GROUP_ID)
                .build();
    }

}
