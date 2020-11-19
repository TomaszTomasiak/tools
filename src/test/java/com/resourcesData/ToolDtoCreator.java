package com.resourcesData;

import com.dto.ToolDto;

public class ToolDtoCreator {

    private static Long ID = 1L;
    private static String NAME = "wiertarka";
    private static String PRODUCER = "BOSH";
    private static String MODEL = "12LXC78";
    private static Long GROUP_ID = 2L;

    private static Long UPDATED_ID = 1L;
    private static String UPDATED_NAME = "młot pneumatyczny";
    private static String UPDATED_PRODUCER = "CAT";
    private static String UPDATED_MODEL = "CP4222";
    private static Long UPDATED_GROUP_ID = 3L;

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
