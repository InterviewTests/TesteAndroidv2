package br.com.dpassos.bankandroid.infra;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.TreeMap;

import br.com.dpassos.bankandroid.statements.data.StatementRepository;

/**
 * Using this factory avoid objects be used in
 * system to other parts without factory. Cause
 * factory defines object product needs have a
 * private constructor.
 * In other words only this factory can construct that objects.
 */
public abstract class GenericFactory {

    static Map<String, Object> instances = new TreeMap<>();

    @SuppressWarnings("unchecked")
    protected <T> T newInstance(Class<T> clazz) {

        if(instances.containsKey(clazz.getCanonicalName())) return (T)instances.get(clazz.getCanonicalName());

        if(clazz.getDeclaredConstructors().length > 1) throw new IllegalArgumentException("Number of constructors needs be equal one");
        Constructor constructor = clazz.getDeclaredConstructors()[0];
        if(constructor.getModifiers() != Modifier.PRIVATE) throw new IllegalArgumentException("constructor needs be private");

        constructor.setAccessible(true);
        try {

            T instance = (T) constructor.newInstance();
            instances.put(clazz.getCanonicalName(), instance);

            return instance;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            constructor.setAccessible(false);
        }
        return null;
    }
}
