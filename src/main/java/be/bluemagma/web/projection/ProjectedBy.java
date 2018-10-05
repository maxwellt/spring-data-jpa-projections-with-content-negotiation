package be.bluemagma.web.projection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that can be placed on any Controller method argument which will try to automatically
 * resolve to a projection.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ProjectedBy {

  /**
   * The projection class to use if the resolver fails to resolve a projection class
   */
  Class<?> def();
}