package com.jobseeker.hrms.organization.mapper.basic;

import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

import java.lang.reflect.Field;

@Mapper(componentModel = "spring", uses = {})
public interface ITestDynamic {

    //<editor-fold desc="To GeneralDataEmbed">
    @ObjectFactory
    default GeneralDataEmbed createGeneralDataEmbed() {
        return new GeneralDataEmbed();
    }

    // Generic mapping method using reflection
    default GeneralDataEmbed toGeneralDataEmbed(Object source) {
        if (source == null) {
            return null;
        }

        GeneralDataEmbed target = createGeneralDataEmbed();

        // Use reflection to get fields from source
        Class<?> sourceClass = source.getClass();
        try {
            // Try to map _id field
            try {
                Field idField = sourceClass.getDeclaredField("_id");
                idField.setAccessible(true);
                Object idValue = idField.get(source);
                target.set_id(idValue != null ? idValue.toString() : null);
            } catch (NoSuchFieldException ignored) {
//                throw new NoSuchFieldException("Error on mapping to GeneralData");
            }

            // Try to map name field
            try {
                Field nameField = sourceClass.getDeclaredField("name");
                nameField.setAccessible(true);
                Object nameValue = nameField.get(source);
                target.setName(nameValue != null ? nameValue.toString() : null);
            } catch (NoSuchFieldException e) {
                // Name field doesn't exist
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing fields", e);
        }

        return target;
    }
    //</editor-fold>
}
