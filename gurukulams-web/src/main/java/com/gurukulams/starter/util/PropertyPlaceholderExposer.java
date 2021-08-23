package com.gurukulams.starter.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Exposer Properties.
 */
@Component
public class PropertyPlaceholderExposer
        implements Map<String, String>, BeanFactoryAware {
    /**
     * beanFactory.
     */
    private ConfigurableBeanFactory beanFactory;

    /**
     * Overside .
     * @param aBeanFactory
     */
    @Override
    public void setBeanFactory(final BeanFactory aBeanFactory) {
        this.beanFactory = (ConfigurableBeanFactory) aBeanFactory;
    }

    /**
     * Resolve Properties.
     * @param name
     * @return rv
     */
    protected String resolveProperty(final String name) {
        String rv = beanFactory.resolveEmbeddedValue("${" + name + "}");

        return rv;
    }

    /**
     * Comment.
     * @param key
     * @return p
     */
    @Override
    public String get(final Object key) {
        return resolveProperty(key.toString());
    }

    /**
     * Comment.
     * @param key
     * @return p
     */
    @Override
    public boolean containsKey(final Object key) {
        try {
            resolveProperty(key.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * DDDDD.
     * @return p
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Comment.
     * @return p
     */
    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Comment.
     * @return p
     */
    @Override
    public Set<Entry<String, String>> entrySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Comment .
     * @return p
     */
    @Override
    public Collection<String> values() {
        throw new UnsupportedOperationException();
    }

    /**
     * Comment.
     * @return p
     */
    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    /**
     * Comment.
     * @return p
     */
    @Override
    public boolean containsValue(final Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Comment.
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /**
     * Comment.
     * @return p
     */
    @Override
    public String put(final String key, final String value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Comment.
     * @return p
     */
    @Override
    public String remove(final Object key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Comment.
     */
    @Override
    public void putAll(final Map<? extends String, ? extends String> t) {
        throw new UnsupportedOperationException();
    }
}
