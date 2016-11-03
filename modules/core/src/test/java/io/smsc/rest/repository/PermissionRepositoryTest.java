package io.smsc.rest.repository;

import io.smsc.model.Permission;
import io.smsc.rest.repository.permission.PermissionRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static io.smsc.PermissionTestData.*;

public class PermissionRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private PermissionRepository repository;

    @Test
    public void delete() throws Exception {
        repository.deleteById(PERMISSION_READ_ONLY_ID);
        PERMISSION_MODEL_MATCHER.assertCollectionEquals(Collections.singletonList(PERMISSION_READ_WRITE),repository.findAll());
    }

    @Test
    public void save() throws Exception {
        Permission newPermission = new Permission(null,"PERMISSION_UNLIMITED");
        Permission created = repository.save(newPermission);
        newPermission.setId(created.getId());
        PERMISSION_MODEL_MATCHER.assertEquals(newPermission,repository.findOne(newPermission.getId()));
    }

    @Test
    public void get() throws Exception {
        Permission permission = repository.findOne(PERMISSION_READ_ONLY_ID);
        PERMISSION_MODEL_MATCHER.assertEquals(PERMISSION_READ_ONLY,permission);
    }

    @Test
    public void getAll() throws Exception {
        Collection<Permission> permissions = repository.findAll();
        PERMISSION_MODEL_MATCHER.assertCollectionEquals(Arrays.asList(PERMISSION_READ_ONLY, PERMISSION_READ_WRITE), permissions);
    }

    @Test
    public void update() throws Exception{
        Permission updated = PERMISSION_READ_ONLY;
        updated.setName("PERMISSION_WITHOUT_ACCESS");
        repository.save(updated);
        PERMISSION_MODEL_MATCHER.assertEquals(updated, repository.findOne(PERMISSION_READ_ONLY_ID));
    }

}