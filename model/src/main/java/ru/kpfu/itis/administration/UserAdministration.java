package ru.kpfu.itis.administration;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;
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
                .field("type").enumeration(
                        element("A", "ADMIN"),
                        element("E", "ELDER"),
                        element("S", "STUDENT"))
                .build();
    }

    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder
                .screenName("Users Administration")
                .build();
    }

    public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("login").caption("Login")
                .field("name").caption("FIO")
                .field("group").caption("User Group")
//                .enumeration(element("A", "Admin"),
//                `        element("E", "Elder"),
//                        element("S", "Student"))
                .build();
    }
}
