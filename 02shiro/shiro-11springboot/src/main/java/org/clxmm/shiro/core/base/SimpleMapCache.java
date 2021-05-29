package org.clxmm.shiro.core.base;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.clxmm.shiro.utils.EmptyUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-25 8:45 下午
 */
public class SimpleMapCache implements Cache<Object, Object>, Serializable {

    private final Map<Object, Object> attributes;

    private final String name;

    public SimpleMapCache(String name, Map<Object, Object> backingMap) {
        if (name == null) {
            throw new IllegalArgumentException("Cache name cannot be null.");
        }
        if (backingMap == null) {
            throw new IllegalArgumentException("Backing map cannot be null.");
        } else {
            this.name = name;
            attributes = backingMap;
        }
    }

    @Override
    public Object get(Object key) throws CacheException {
        return attributes.get(key);
    }

    @Override
    public Object put(Object key, Object value) throws CacheException {
        return attributes.put(key, value);
    }

    @Override
    public Object remove(Object key) throws CacheException {
        return attributes.remove(key);
    }

    @Override
    public void clear() throws CacheException {
        attributes.clear();
    }

    @Override
    public int size() {
        return attributes.size();
    }

    @Override
    public Set<Object> keys() {
        Set<Object> keys = attributes.keySet();
        if (!keys.isEmpty()) {
            return Collections.unmodifiableSet(keys);
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public Collection<Object> values() {
        Collection<Object> values = attributes.values();
        if (!EmptyUtil.isNullOrEmpty(values)) {
            return Collections.unmodifiableCollection(values);
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public String toString() {
        return "SimpleMapCache [attributes=" + attributes + ", name=" + name
                + ", keys()=" + keys() + ", size()=" + size() + ", values()="
                + values() + "]";
    }
}
