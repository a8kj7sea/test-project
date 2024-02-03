package me.a8kj.rspvphyblood.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.a8kj.rspvphyblood.enums.ConfigurationSaveType;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigurtionMeta {
	String name();

	ConfigurationSaveType saveType() default ConfigurationSaveType.AUTO;

}