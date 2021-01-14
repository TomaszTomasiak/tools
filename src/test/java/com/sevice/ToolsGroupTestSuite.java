package com.sevice;

import com.domain.ToolsGroup;
import com.service.ToolsGroupServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolsGroupTestSuite {

    @Autowired
    private ToolsGroupServiceImpl service;

    private ToolsGroup toolsGroup = ToolsGroup.builder()
            .id(15)
            .name("budowlane")
            .build();


}
