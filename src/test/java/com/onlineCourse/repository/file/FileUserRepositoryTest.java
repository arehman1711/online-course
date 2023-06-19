package com.onlineCourse.repository.file;

import com.onlineCourse.entities.User;
import com.onlineCourse.repository.file.FileUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static com.onlineCourse.utils.Constants.ROLE_USER;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class FileUserRepositoryTest {

    @Autowired
    private FileUserRepository fileUserRepository;
    @Before
    public void init() {
        ReflectionTestUtils.setField(fileUserRepository, "fileRepoPath", "file-repo/" );
    }

    @Test
    public void save_success() {
        User user = User.builder().id(10).name("User 10").role(ROLE_USER).password("abcd").email("test@test.com").build();
        User fileUser = fileUserRepository.save(user);
        Assert.assertNotNull(fileUser);
        Assert.assertEquals("User 10", fileUser.getName());
        Assert.assertEquals(user, fileUser);
    }

    @Test
    public void readUser_success() {
    }

    @Test
    public void update_success() {
        User user = User.builder().id(10).name("Updated Test 10").role(ROLE_USER).password("abcd").email("test@test.com").build();

        //Assert.assertEquals("Updated Test 10", fileUser.getName());
    }
}