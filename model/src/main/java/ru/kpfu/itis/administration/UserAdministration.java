package ru.kpfu.itis.administration;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.*;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;
import ru.kpfu.itis.model.User;

/**
 * Created by ermolaev.a on 05.08.2015.
 */
@SuppressWarnings("unused")
public class UserAdministration extends AdministrationConfiguration<User> {
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.nameField("name")
                .singularName("User")
                .pluralName("Users")
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
                .build();
    }

    public FieldSetConfigurationUnit formView(final PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("login").caption("Login")
                .field("name").caption("Name")
                .field("academicGroup").caption("Groups")
                .build();
    }

    public FieldSetConfigurationUnit showView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("login").caption("Login")
                .field("name").caption("Name")
                .field("academicGroup").caption("Groups")

//                .field("users").caption("Users")
                .build();
    }

    public FieldSetConfigurationUnit quickView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("login").caption("Login")
                .field("name").caption("Name")
//                .field("users").caption("Users")
                .build();
    }
}
