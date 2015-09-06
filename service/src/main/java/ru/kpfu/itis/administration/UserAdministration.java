package ru.kpfu.itis.administration;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.*;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;
import ru.kpfu.itis.administration.listener.UserEventListener;
import ru.kpfu.itis.model.User;

import static org.lightadmin.api.config.utils.EnumElement.element;

/**
 * Created by ermolaev.a on 05.08.2015.
 */
@SuppressWarnings("unused")
public class UserAdministration extends AdministrationConfiguration<User> {
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.nameField("name")
                .singularName("User")
                .pluralName("Users")
                .repositoryEventListener(UserEventListener.class)
                .build();
    }

    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder
                .screenName("Users")
                .build();
    }

    public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("login").caption("Login")
                .field("name").caption("Name")
                .field("academicGroup").caption("Groups")
                .field("entranceYear").caption("entrance year")
                .field("role").caption("RoleEnum")
                .build();
    }

    public FieldSetConfigurationUnit formView(final PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("login").caption("Login")
                .field("name").caption("Name")
                .field("academicGroup").caption("Groups")
                .field("entranceYear").caption("Entrance year")
                .field("role").caption("RoleEnum").enumeration(
                        element("STUDENT", "Student"),
                        element("ADMIN", "Admin")
                )
                .field("password").caption("Password (Please enter a password < 32 characters) ")
                .field("salt").caption("Salt (Please don't touch this field)")
                .build();
    }

    public FieldSetConfigurationUnit showView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("login").caption("Login")
                .field("name").caption("Name")
                .field("academicGroup").caption("Groups")
                .field("entranceYear").caption("entrance year")
                .field("role").caption("RoleEnum")
                .build();
    }

    public FieldSetConfigurationUnit quickView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("login").caption("Login")
                .field("name").caption("Name")
                .field("entranceYear").caption("entrance year")
                .field("role").caption("RoleEnum")
                .build();
    }


}
