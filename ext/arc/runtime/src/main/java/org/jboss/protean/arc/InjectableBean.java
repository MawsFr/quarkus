package org.jboss.protean.arc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Represents an injectable bean.
 *
 * @author Martin Kouba
 *
 * @param <T>
 */
public interface InjectableBean<T> extends Bean<T>, InjectableReferenceProvider<T> {

    /**
     *
     * @return the scope
     */
    default Class<? extends Annotation> getScope() {
        return Dependent.class;
    }

    /**
     *
     * @return the set of bean types
     */
    Set<Type> getTypes();

    /**
     *
     * @return the set of qualifiers
     */
    default Set<Annotation> getQualifiers() {
        return Qualifiers.DEFAULT_QUALIFIERS;
    }

    @Override
    default void destroy(T instance, CreationalContext<T> creationalContext) {
        creationalContext.release();
    }

    /**
     *
     * @return the declaring bean if the bean is a producer method/field, or {@code null}
     */
    default InjectableBean<?> getDeclaringBean() {
        return null;
    }

    @Override
    default String getName() {
        return null;
    }

    @Override
    default Set<Class<? extends Annotation>> getStereotypes() {
        return Collections.emptySet();
    }

    @Override
    default Set<InjectionPoint> getInjectionPoints() {
        return Collections.emptySet();
    }

    @Override
    default boolean isNullable() {
        return false;
    }

    @Override
    default boolean isAlternative() {
        return getAlternativePriority() != null;
    }

    /**
     *
     * @return the priority if the bean is an alternative, or {@code null}
     */
    default Integer getAlternativePriority() {
        return null;
    }

}
