package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.model.jpa.UserPicture;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;

/**
 * Created by Win 8 on 18.06.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserPictureTest {

    @Autowired
    UserPictureService userPictureService;

    @Autowired
    UserService userService;

    private static final byte[] pictureDataBytes = new byte[]{(byte) 0xe0, 0x4f, (byte) 0xd0,
            0x20, (byte) 0xea, 0x3a, 0x69, 0x10, (byte) 0xa2, (byte) 0xd8, 0x08, 0x00, 0x2b,
            0x30, 0x30, (byte) 0x9d};

    @Test
    public void uploadUserPicture() throws SQLException {
        User user = userService.createUser("goce berberot","tsoka","desc","asd@asd.com","223-305");
        UserPicture userPicture = userPictureService.uploadUserPicture(user.id,pictureDataBytes,"png");

        Assert.assertNotNull(userPicture);
        Assert.assertEquals(userPicture.contentType, "png");
        Assert.assertEquals(userPicture.data,new SerialBlob(pictureDataBytes));
        Assert.assertEquals(userPicture.size,pictureDataBytes.length,0);
    }
}
