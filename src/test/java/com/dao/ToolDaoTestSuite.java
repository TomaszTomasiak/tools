package com.dao;
import com.domain.Tool;
import com.domain.ToolsGroup;
import com.repository.ToolRepository;
import com.repository.ToolsGroupRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolDaoTestSuite {
    @Autowired
    ToolsGroupRepository groupDao;

    @Autowired
    ToolRepository toolDao;

    private ToolsGroup toolsGroup;
    private Tool tool;

    @Before
    public void init() {
        toolsGroup = new ToolsGroup();
        toolsGroup.setName("budowlane");

        tool = new Tool();
        tool.setModel("XYZ123");
        tool.setName("wiertarka");
        tool.setProducer("Bosch");
    }

    @Test
    public void testToolDaoSave() {
        //Given
        //When
        groupDao.save(toolsGroup);
        tool.setGroupId(toolsGroup);
        toolDao.save(tool);

        //Then
        assertTrue(toolDao.count() > 0);
        assertFalse(toolDao.findAll().size() == 0);
        assertEquals("wiertarka", tool.getName());

        //CleanUp
        toolDao.deleteAll();
        groupDao.deleteAll();
    }

    @Test
    public void testFindAllTools() {
        //Given
        //When
        groupDao.save(toolsGroup);
        tool.setGroupId(toolsGroup);
        toolDao.save(tool);
        //Then
        assertTrue(toolDao.findAll().size() > 0);

        //CleanUp
        toolDao.deleteAll();
        groupDao.deleteAll();
    }

    @Test
    public void testRemoveTool() {

        //Given
        //When
        groupDao.save(toolsGroup);
        tool.setGroupId(toolsGroup);
        toolDao.save(tool);
        long id = tool.getId();
        int number = toolDao.findAll().size();
        toolDao.deleteById(id);

        //Then
        assertEquals(toolDao.findAll().size(), number - 1);

        //CleanUp
        toolDao.deleteAll();
        groupDao.deleteAll();
    }

    @Test
    public void testFindToolById() {
        //Given
        //When
        groupDao.save(toolsGroup);
        tool.setGroupId(toolsGroup);
        toolDao.save(tool);
        long id = tool.getId();
        //Then
        assertTrue(toolDao.findAll().size() > 0);
        assertEquals("XYZ123", tool.getModel());

        //CleanUp
        toolDao.deleteAll();
        groupDao.deleteAll();
    }
}
