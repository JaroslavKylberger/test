package cz.kylberger.test.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Class authoring metadata
 */
@Documented
@Target(ElementType.TYPE)
public @interface ClassPreamble {
    String author();
    String date() default "N/A";
    int currentRevision() default 1;
    String lastModified() default "N/A";
    String lastModifiedBy() default "N/A";
    String[] reviewers() default {};
}
