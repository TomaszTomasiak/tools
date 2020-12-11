package com.dao;
import com.domain.ToolsGroup;
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
public class ToolsGroupDaoTestSuite {

    @Autowired
    ToolsGroupRepository repository;

    private ToolsGroup toolsGroup;

    @Before
    public void init() {
        toolsGroup = new ToolsGroup();
        toolsGroup.setId(1);
        toolsGroup.setName("budowlane");
    }

    @Test
    public void testGroupDaoSave() {
        //Given
        //When
        repository.save(toolsGroup);

        //Then
        assertTrue(repository.count() > 0);
        assertFalse(repository.findAll().size() == 0);
        assertEquals("budowlane", toolsGroup.getName());

        //CleanUp
        repository.deleteAll();
    }

    @Test
    public void testFindAllGroups() {
        //Given
        //When
        repository.save(toolsGroup);
        //Then
        assertTrue(repository.findAll().size() > 0);

        //CleanUp
        repository.deleteAll();
    }

    @Test
    public void testRemoveGroup() {

        //Given
        //When
        repository.save(toolsGroup);
        long id = toolsGroup.getId();
        int number = repository.findAll().size();
        repository.deleteById(id);

        //Then
        assertEquals(repository.findAll().size(), number - 1);

        //CleanUp
        repository.deleteAll();
    }

    @Test
    public void testFindGroupById() {
        //Given
        //When
        repository.save(toolsGroup);
        long id = toolsGroup.getId();
        //Then
        assertTrue(repository.findAll().size() > 0);
        assertEquals(1, id);
    }
}
