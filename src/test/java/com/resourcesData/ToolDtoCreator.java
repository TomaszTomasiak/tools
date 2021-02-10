package com.resourcesData;

import com.dto.ToolDto;

public class ToolDtoCreator {

    public static long ID = 1L;
    public static String NAME = "wiertarka";
    public static long PRODUCER_ID = 2L;
    public static long MODEL_ID = 3L;
    public static long GROUP_ID = 9L;
    public static long LOCATION_ID = 2L;

    public static long UPDATED_ID = 1L;
    public static String UPDATED_NAME = "młot pneumatyczny";
    public static long UPDATED_PRODUCER_ID = 7L;
    public static long UPDATED_MODEL_ID = 19L;
    public static long UPDATED_GROUP_ID = 12L;
    public static long UPDATED_LOCATION_ID = 3L;

    public static ToolDto toolDtoCreator() {
        return ToolDto.builder()
                .id(ID)
                .name(NAME                )
                .producerId(PRODUCER_ID)
                .modelId(MODEL_ID)
                .groupId(GROUP_ID)
                .locationId(LOCATION_ID)
                .build();
    }

    public static ToolDto updatedToolDtoCreator() {
        return ToolDto.builder()
                .id(UPDATED_ID)
                .name(UPDATED_NAME                )
                .producerId(UPDATED_PRODUCER_ID)
                .modelId(UPDATED_MODEL_ID)
                .groupId(UPDATED_GROUP_ID)
                .locationId(UPDATED_LOCATION_ID)
                .build();
    }

}
